package com.conversordemedidas.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_DOUBLE_LEFT_VALUE = "com.conversordemedidas.app.EXTRA_DOUBLE_LEFT_VALUE";
    public static final String EXTRA_STRING_LEFT_UNIT = "com.conversordemedidas.app.EXTRA_STRING_LEFT_UNIT";
    public static final String EXTRA_STRING_RIGHT_UNIT = "com.conversordemedidas.app.EXTRA_STRING_RIGHT_UNIT";
    public static final String EXTRA_STRING_TIPO = "com.conversordemedidas.app.EXTRA_STRING_TIPO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Construindo os Spinners
        Spinner dynamicSpinner = (Spinner) findViewById(R.id.dynamic_spinner);

        String[] items = new String[] { "Moeda", "Temperatura", "Comprimento" };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dynamicSpinner.setAdapter(adapter);

        Spinner leftSpinner = (Spinner) findViewById(R.id.left_spinner);
        Spinner rightSpinner = (Spinner) findViewById(R.id.right_spinner);

        // Opções de tipo
        String[] moedas = new String[] { "USD", "BRL", "EUR" };
        String[] temperaturas = new String[] { "Celsius", "Kelvin", "Fahrenheit" };
        String[] distancias = new String[] { "Pés", "Metros", "Centímetros", "Polegadas" };

        ArrayAdapter<String> adapterMoedas = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, moedas);
        adapterMoedas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapterTemperaturas = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, temperaturas);
        adapterTemperaturas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapterDistancias = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, distancias);
        adapterDistancias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Detecção de Evento no spinner de seleção de tipo
        final String[] tipoMedida = new String[] {""};
        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                tipoMedida[0] = (String) parent.getItemAtPosition(position);

                switch(tipoMedida[0]) {
                    case "Moeda":
                        leftSpinner.setAdapter(adapterMoedas);
                        rightSpinner.setAdapter(adapterMoedas);
                        break;
                    case "Temperatura":
                        leftSpinner.setAdapter(adapterTemperaturas);
                        rightSpinner.setAdapter(adapterTemperaturas);
                        break;
                    case "Comprimento":
                        leftSpinner.setAdapter(adapterDistancias);
                        rightSpinner.setAdapter(adapterDistancias);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        final String[] leftUnit = new String[] {""};
        leftSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                leftUnit[0] = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        final String[] rightUnit = new String[] {""};
        rightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                rightUnit[0] = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        Button botaoResultado = (Button) findViewById(R.id.botao_resultado);

        botaoResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText leftValueText = (EditText) findViewById(R.id.left_value);
                String leftValueString = leftValueText.getText().toString();
                if (TextUtils.isEmpty(leftValueString))
                    Toast.makeText(getApplicationContext(),"Adicione um valor para converter.",Toast.LENGTH_SHORT).show();
                else if (leftUnit[0] == rightUnit[0])
                    Toast.makeText(getApplicationContext(),"Selecione medidas diferentes.",Toast.LENGTH_SHORT).show();
                else
                    pushResult(leftUnit[0], rightUnit[0], tipoMedida[0]);
            }
        });
    }

    public void pushResult(String leftUnit, String rightUnit, String tipoMedida) {
        EditText leftValueText = (EditText) findViewById(R.id.left_value);
        double leftValue = Double.parseDouble(leftValueText.getText().toString());

        Intent intent;
        if (tipoMedida == "Moeda")
            intent = new Intent(this, MoedaActivity.class);
        else
            intent = new Intent(this, ResultadoActivity.class);

        intent.putExtra(EXTRA_DOUBLE_LEFT_VALUE, leftValue);
        intent.putExtra(EXTRA_STRING_LEFT_UNIT, leftUnit);
        intent.putExtra(EXTRA_STRING_RIGHT_UNIT, rightUnit);
        intent.putExtra(EXTRA_STRING_TIPO, tipoMedida);

        startActivity(intent);
    }
}