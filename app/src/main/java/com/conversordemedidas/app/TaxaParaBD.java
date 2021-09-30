package com.conversordemedidas.app;

import org.json.JSONException;
import org.json.JSONObject;

public class TaxaParaBD {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String base;
    private String convert_to;
    private double resultado;

    public void ConverterTaxaParaBD(JSONObject json, String rightUnit) throws JSONException {
        base = json.getString("base");
        convert_to = rightUnit;
        resultado = Double.parseDouble(json.getJSONObject("result").getString(rightUnit));
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getConvert_to() {
        return convert_to;
    }

    public void setConvert_to(String convert_to) {
        this.convert_to = convert_to;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }
}
