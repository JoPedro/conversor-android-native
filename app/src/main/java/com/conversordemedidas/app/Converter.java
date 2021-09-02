package com.conversordemedidas.app;

/*
* TIPOS = { "Moeda", "Temperatura", "Distância" };
* MOEDAS = { "Dólar", "Real", "Euro" };
* TEMPERATURAS = { "Celsius", "Kelvin", "Fahrenheit" };
* DISTÂNCIAS = { "Pés", "Metros", "Centímetros", "Polegadas", "Milhas", "Quilômetros" };
*/

public class Converter {
    public static double Conversao(double leftValue, String leftUnit, String rightUnit) {
        double resultado = 0.0;

        // SWITCH Temperatura
        switch(leftUnit) {
            case "Celsius":
                switch (rightUnit) {
                    case "Fahrenheit":
                        resultado = (9.0/5.0) * leftValue + 32;
                        break;
                    case "Kelvin":
                        resultado = leftValue + 273.15;
                        break;
                    default:
                        break;
                }
                break;
            case "Fahrenheit":
                switch (rightUnit) {
                    case "Celsius":
                        resultado = (5.0/9.0) * (leftValue - 32);
                        break;
                    case "Kelvin":
                        resultado = ((5.0/9.0) * (leftValue - 32)) + 273.15;
                        break;
                    default:
                        break;
                }
                break;
            case "Kelvin":
                switch (rightUnit) {
                    case "Celsius":
                        resultado = leftValue - 273.15;
                        break;
                    case "Fahrenheit":
                        resultado = (9.0/5.0) * (leftValue - 273.15) + 32;
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

        // SWITCH Moeda
        switch(leftUnit) {
            case "Celsius":
                switch (rightUnit) {
                    case "Fahrenheit":
                        resultado = (9.0/5.0) * leftValue + 32;
                        break;
                    case "Kelvin":
                        resultado = leftValue + 273.15;
                        break;
                    default:
                        break;
                }
                break;
            case "Fahrenheit":
                switch (rightUnit) {
                    case "Celsius":
                        resultado = (5.0/9.0) * (leftValue - 32);
                        break;
                    case "Kelvin":
                        resultado = ((5.0/9.0) * (leftValue - 32)) + 273.15;
                        break;
                    default:
                        break;
                }
                break;
            case "Kelvin":
                switch (rightUnit) {
                    case "Celsius":
                        resultado = leftValue - 273.15;
                        break;
                    case "Fahrenheit":
                        resultado = (9.0/5.0) * (leftValue - 273.15) + 32;
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

        return resultado;
    }
}
