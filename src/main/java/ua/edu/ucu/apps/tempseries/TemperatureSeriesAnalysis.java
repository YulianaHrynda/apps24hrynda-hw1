package ua.edu.ucu.apps.tempseries;

import java.util.Arrays;

public class TemperatureSeriesAnalysis {

    private double[] temperatureSeries;
    private int size;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[0];
        this.size = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temperatureSeries = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
        this.size = temperatureSeries.length;
    }

    public double average() {
        if (size == 0){
            throw new IllegalArgumentException("Temperature series is empty.");
        }

        double sum = 0;
        for (double value : temperatureSeries) {
            sum += value;
        }
        return sum / size;
    }

    public double deviation() {
        if (size == 0){
            throw new IllegalArgumentException("Temperature series is empty.");
        }

        double mean = average();
        double sumOfSquaredDiffs = 0;
        for (double value : temperatureSeries) {
            sumOfSquaredDiffs += Math.pow(value - mean, 2);
        }
        return Math.sqrt(sumOfSquaredDiffs / size);        
    }

    public double min() {
        if (size == 0){
            throw new IllegalArgumentException("Temperature series is empty.");
        }

        double minTemp = temperatureSeries[0];
        for (double value : temperatureSeries){
            if (value < minTemp){
                minTemp = value;
            }
        }

        return minTemp;
    }

    public double max() {
        if (size == 0){
            throw new IllegalArgumentException("Temperature series is empty.");
        }

        double maxTemp = 0;
        for (double value : temperatureSeries){
            if (value > maxTemp){
                maxTemp = value;
            }
        }

        return maxTemp;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0.0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (size == 0){
            throw new IllegalArgumentException("Temperature series is empty.");
        }
        
        double closest = temperatureSeries[0];
        for (double value : temperatureSeries){
            if (Math.abs(value - tempValue) < Math.abs(closest - tempValue) ||
                (Math.abs(value - tempValue) == Math.abs(closest - tempValue) && value > closest)) {
                closest = value;
            }
        }

        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        return Arrays.stream(temperatureSeries).filter(value -> value < tempValue).toArray();
    }

    public double[] findTempsGreaterThen(double tempValue) {
        return Arrays.stream(temperatureSeries).filter(value -> value >= tempValue).toArray();
    }

    public double[] findTempsInRange(double lowerBound, double upperBound) {
        return Arrays.stream(temperatureSeries).filter(value -> value >= lowerBound && value <= upperBound).toArray();
    }

    public void reset() {
        this.temperatureSeries = new double[0];
        this.size = 0;
    }

    public double[] sortTemps() {
        double[] tempCopy = Arrays.copyOf(temperatureSeries, size);

        Arrays.sort(tempCopy);
        return tempCopy;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (size == 0){
            throw new IllegalArgumentException("Temperature series is empty.");
        }

        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        int newSize = size + temps.length;
        double[] newTemperatureSeries = Arrays.copyOf(temperatureSeries, newSize);
        System.arraycopy(temps, 0, newTemperatureSeries, size, temps.length);
        temperatureSeries = newTemperatureSeries;
        size = newSize;
        return newSize;
    }
}
