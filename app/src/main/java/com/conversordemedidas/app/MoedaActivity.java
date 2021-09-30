package com.conversordemedidas.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;

public class MoedaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moeda);

        Intent intent = getIntent();

        double leftValue = intent.getDoubleExtra(MainActivity.EXTRA_DOUBLE_LEFT_VALUE, 0.0);

        TextView leftValueShowText = (TextView) findViewById(R.id.left_value_show);
        TextView rightValueShowText = (TextView) findViewById(R.id.right_value_show);
        TextView leftUnitShowText = (TextView) findViewById(R.id.left_unit_show);
        TextView rightUnitShowText = (TextView) findViewById(R.id.right_unit_show);

        String leftUnit = intent.getStringExtra(MainActivity.EXTRA_STRING_LEFT_UNIT);
        String rightUnit = intent.getStringExtra(MainActivity.EXTRA_STRING_RIGHT_UNIT);

        new Thread(new Runnable() {
            StringBuffer buffer = new StringBuffer();
            JSONObject json;
            final DecimalFormat df = new DecimalFormat(".00");

            @Override
            public void run() {
                HttpURLConnection huc;
                BufferedReader reader;
                String linha;

                String urlString = "https://api.fastforex.io/fetch-one?from=" + leftUnit + "&to=" + rightUnit + "&api_key=9be3b13308-aea8f3ba2e-r06lvu";

                try {
                    // API: FastFOREX.io
                    URL url = new URL(urlString);

                    huc = (HttpURLConnection) url.openConnection();
                    huc.setRequestProperty("Accept", "application/json");
                    huc.setRequestMethod("GET");
                    huc.connect();

                    InputStream inputStream;
                    if (huc.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST)
                        inputStream = huc.getInputStream();
                    else
                        inputStream = huc.getErrorStream();

                    reader = new BufferedReader(new InputStreamReader(inputStream));

                    while ((linha = reader.readLine()) != null) {
                        buffer.append(linha);
                    }

                    json = new JSONObject(String.valueOf(buffer));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        double conversionRate = 0;

                        TaxaParaBD forex = new TaxaParaBD();

                        try {
                            conversionRate = Double.parseDouble(json.getJSONObject("result").getString(rightUnit));
                            forex.ConverterTaxaParaBD(json, rightUnit);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        BD bd = new BD(getBaseContext());
                        if (bd.buscar(leftUnit, rightUnit)) {
                            System.out.println("Já existe");
                        }
                        else {
                            System.out.println("Adicionando taxa de conversão...");
                            bd.inserir(forex);
                        }

                        StringBuilder infoBD = new StringBuilder();
                        List<TaxaParaBD> lista = bd.buscar();
                        int i = 0;
                        while (i < lista.size()) {
                            infoBD.append("id: "+lista.get(i).getId()+", from: "+lista.get(i).getBase()+", to: "+lista.get(i).getConvert_to()+" = "+lista.get(i).getResultado()+"\n");
                            i++;
                        }

                        // DEBUG INFO
                        System.out.println(json.toString());
                        System.out.println(infoBD);

                        leftUnitShowText.setText(leftUnit);
                        rightUnitShowText.setText(rightUnit);

                        leftValueShowText.setText(df.format(leftValue));
                        rightValueShowText.setText(df.format(leftValue * conversionRate));
                    }
                });
            }
        }).start();
    }
}