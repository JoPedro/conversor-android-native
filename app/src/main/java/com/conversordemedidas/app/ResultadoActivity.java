package com.conversordemedidas.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.conversordemedidas.app.Converter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;


public class ResultadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        Intent intent = getIntent();

        double leftValue = intent.getDoubleExtra(MainActivity.EXTRA_DOUBLE_LEFT_VALUE, 0.0);
        String leftUnit = intent.getStringExtra(MainActivity.EXTRA_STRING_LEFT_UNIT);
        String rightUnit = intent.getStringExtra(MainActivity.EXTRA_STRING_RIGHT_UNIT);

        TextView leftValueShowText = (TextView) findViewById(R.id.left_value_show);
        TextView rightValueShowText = (TextView) findViewById(R.id.right_value_show);
        TextView leftUnitShowText = (TextView) findViewById(R.id.left_unit_show);
        TextView rightUnitShowText = (TextView) findViewById(R.id.right_unit_show);

        String tipoMedida = intent.getStringExtra(MainActivity.EXTRA_STRING_TIPO);
        double resultado = Converter.Conversao(leftValue, leftUnit, rightUnit);
        DecimalFormat df = new DecimalFormat(".00");

        leftValueShowText.setText(df.format(leftValue));
        rightValueShowText.setText(df.format(resultado));
        leftUnitShowText.setText(leftUnit);
        rightUnitShowText.setText(rightUnit);

        ToggleButton toggleSwap = (ToggleButton) findViewById(R.id.toggle);
        toggleSwap.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    double troca = Converter.Conversao(resultado, rightUnit, leftUnit);

                    leftValueShowText.setText(df.format(resultado));
                    rightValueShowText.setText(df.format(troca));
                    leftUnitShowText.setText(rightUnit);
                    rightUnitShowText.setText(leftUnit);
                } else {
                    leftValueShowText.setText(df.format(leftValue));
                    rightValueShowText.setText(df.format(resultado));
                    leftUnitShowText.setText(leftUnit);
                    rightUnitShowText.setText(rightUnit);
                }
            }
        });
    }
}