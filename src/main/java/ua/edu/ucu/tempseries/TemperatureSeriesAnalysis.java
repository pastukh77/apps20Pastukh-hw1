package ua.edu.ucu.tempseries;

import java.lang.IllegalArgumentException;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] temperatureSeries;
    private int size;
    static final double MIN_VAL = -273;
    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[1];
        size = 0;

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temperatureSeries = temperatureSeries;
        checkInputMismatch();
        size = temperatureSeries.length;
    }

    private void checkEmpty() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
    }

    private void checkInputMismatch() {
        for (double temp: temperatureSeries) {
            if (temp < MIN_VAL){
                throw new InputMismatchException();
            }
        }
    }
    private double tempsSum() {
        double sum = 0;
        for (double temp: temperatureSeries) {
            sum += temp;
        }
        return sum;
    }
    public double average() {
        checkEmpty();
        return tempsSum() / size;
    }

    public double deviation() {
        checkEmpty();
        double tempAverage = average();
        double sum = 0;

        for (double temp: temperatureSeries) {
            sum += (temp - tempAverage) * (temp - tempAverage);
        }
        return Math.sqrt(sum / size);
    }

    public double min() {
        checkEmpty();
        double minTemp = temperatureSeries[0];
        for (double temp: temperatureSeries) {
            if (temp < minTemp){
                minTemp = temp;
            }
        }
        return minTemp;
    }

    public double max() {
        checkEmpty();
        double maxTemp = temperatureSeries[0];
        for (double temp: temperatureSeries) {
            if (temp > maxTemp){
                maxTemp = temp;
            }
        }
        return maxTemp;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        checkEmpty();
        double currentClosest = temperatureSeries[0];
        for (double temp: temperatureSeries) {
            if (Math.abs(temp - tempValue) <= (Math.abs(currentClosest - tempValue))) {
                if (Math.abs(currentClosest) == Math.abs(temp)) {
                    currentClosest = Math.max(currentClosest, temp);
                }
                else {
                    currentClosest = temp;
                }
            }
        }

        return currentClosest;
    }

    private double[] findTempsThen(double tempValue, String sign) {

        int counter = 0;
        int i = 0;
        for (double temp: temperatureSeries) {
            if (sign.equals(">")) {
                if (temp > tempValue) {
                    counter ++;
                }
            }
            else if (sign.equals("<")) {
                if (temp < tempValue) {
                    counter ++;
                }
            }
        }
        double[] result = new double[counter];
        for (double temp: temperatureSeries) {
            if (sign.equals(">")) {
                if (temp > tempValue) {
                    result[i] = temp;
                    i++;
                }
            }
            else if (sign.equals("<")) {
                if (temp < tempValue) {
                    result[i] = temp;
                    i++;
                }
            }
        }
        return result;
    }

    public double[] findTempsLessThen(double tempValue) {
        return findTempsThen(tempValue, "<");
    }

    public double[] findTempsGreaterThen(double tempValue) {
        return findTempsThen(tempValue, ">");
    }

    public TempSummaryStatistics summaryStatistics() {
        return new TempSummaryStatistics(this);
    }

    public double addTemps(double... temps) {
        if (temperatureSeries.length - size < temps.length) {
            double[] result = new double[temperatureSeries.length * 2];
            for (int i = 0; i < temperatureSeries.length + temps.length; i++) {
                if (i < temperatureSeries.length) {
                    result[i] = temperatureSeries[i];
                }
                else {
                    result[i] = temps[i-temperatureSeries.length];
                }
            }
            temperatureSeries = result;
        }
        else {
            int i = 0;
            while (i < temps.length) {
                temperatureSeries[size + i] = temps[i];
                i++;
            }
        }
        size += temps.length;
        checkInputMismatch();
        return tempsSum();
    }
}
