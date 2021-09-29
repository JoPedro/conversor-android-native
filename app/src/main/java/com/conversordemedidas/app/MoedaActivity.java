package com.conversordemedidas.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class MoedaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moeda);

        Intent intent = getIntent();

        double leftValue = intent.getDoubleExtra(MainActivity.EXTRA_DOUBLE_LEFT_VALUE, 0.0);
        String leftUnit = intent.getStringExtra(MainActivity.EXTRA_STRING_LEFT_UNIT);
        String rightUnit = intent.getStringExtra(MainActivity.EXTRA_STRING_RIGHT_UNIT);

        TextView leftValueShowText = (TextView) findViewById(R.id.left_value_show);
        TextView rightValueShowText = (TextView) findViewById(R.id.right_value_show);
        TextView leftUnitShowText = (TextView) findViewById(R.id.left_unit_show);
        TextView rightUnitShowText = (TextView) findViewById(R.id.right_unit_show);

        EditText textViewTeste = (EditText) findViewById(R.id.editTextTextMultiLine);
        DecimalFormat df = new DecimalFormat(".00");

        new Thread(new Runnable() {
            StringBuffer buffer = new StringBuffer();
            JSONObject json;

            @Override
            public void run() {
                HttpURLConnection huc;
                BufferedReader reader;
                String linha = "";

                try {
                    // API: FastFOREX.io
                    URL url = new URL("https://api.fastforex.io/fetch-one?from=USD&to=BRL&api_key=9be3b13308-aea8f3ba2e-r06lvu");
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
                        String leftUnit = null;
                        String rightUnit = null;
                        double conversionRate = 0;

                        try {
                            leftUnit = json.getString("base");
                            conversionRate = Double.parseDouble(json.getJSONObject("result").getString("BRL"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        textViewTeste.setText(json.toString());

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