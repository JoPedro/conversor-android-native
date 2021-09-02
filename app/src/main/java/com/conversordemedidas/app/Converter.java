package com.conversordemedidas.app;

/*
* TIPOS = { "Moeda", "Temperatura", "Comprimento" };
* MOEDAS = { "Dólar", "Real", "Euro" };
* TEMPERATURAS = { "Celsius", "Kelvin", "Fahrenheit" };
* COMPRIMENTOS = { "Pés", "Metros", "Centímetros", "Polegadas" };
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
            case "Dólar":
                switch (rightUnit) {
                    case "Real":
                        resultado = leftValue * 5.17;
                        break;
                    case "Euro":
                        resultado = leftValue * 0.84;
                        break;
                    default:
                        break;
                }
                break;
            case "Euro":
                switch (rightUnit) {
                    case "Real":
                        resultado = leftValue * 6.14;
                        break;
                    case "Dólar":
                        resultado = leftValue * 1.18;
                        break;
                    default:
                        break;
                }
                break;
            case "Real":
                switch (rightUnit) {
                    case "Dólar":
                        resultado = leftValue * 0.19;
                        break;
                    case "Euro":
                        resultado = leftValue * 0.16;
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

        // SWITCH Comprimento
        // COMPRIMENTOS = { "Pés", "Metros", "Centímetros", "Polegadas" };
        switch(leftUnit) {
            case "Pés":
                switch (rightUnit) {
                    case "Metros":
                        resultado = leftValue / 3.281;
                        break;
                    case "Centímetros":
                        resultado = leftValue * 30.48;
                        break;
                    case "Polegadas":
                        resultado = leftValue * 12.00;
                        break;
                    default:
                        break;
                }
                break;
            case "Metros":
                switch (rightUnit) {
                    case "Pés":
                        resultado = leftValue * 3.281;
                        break;
                    case "Centímetros":
                        resultado = leftValue * 100.00;
                        break;
                    case "Polegadas":
                        resultado = leftValue * 39.37;
                        break;
                    default:
                        break;
                }
                break;
            case "Centímetros":
                switch (rightUnit) {
                    case "Pés":
                        resultado = leftValue / 30.48;
                        break;
                    case "Metros":
                        resultado = leftValue / 100.00;
                        break;
                    case "Polegadas":
                        resultado = leftValue * 2.54;
                        break;
                    default:
                        break;
                }
                break;
            case "Polegadas":
                switch (rightUnit) {
                    case "Pés":
                        resultado = leftValue / 12.00;
                        break;
                    case "Metros":
                        resultado = leftValue / 39.37;
                        break;
                    case "Centímetros":
                        resultado = leftValue / 2.54;
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
