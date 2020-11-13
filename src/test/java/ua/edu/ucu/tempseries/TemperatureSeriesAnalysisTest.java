package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {
    private TemperatureSeriesAnalysis emptyArray;
    private TemperatureSeriesAnalysis commonArray;

    @Before
    public void setUp() {
        emptyArray = new TemperatureSeriesAnalysis();
        commonArray = new TemperatureSeriesAnalysis(new double[]{-1.0, 1.0, 2.0, 3.0, 4.0, -2.0, -3.0, -4.0});
    }

    @Test
    public void testAverageWithOneElementArray() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;
        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testAverageWithCommonArray() {
        double expResult = 0.0;
        double actualResult = commonArray.average();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMinWithCommonArray() {
        double expResult = -4.0;
        double actualResult = commonArray.min();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMaxWithCommonArray() {
        double expResult = 4.0;
        double actualResult = commonArray.max();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZeroWithCommonArray() {
        double expResult = 1.0;
        double actualResult = commonArray.findTempClosestToZero();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValueWithCommonArray() {
        double expResult = 4.0;
        double actualResult = commonArray.findTempClosestToValue(13);
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThanWithCommonArray() {
        double[] expResult = {3.0, 4.0};
        double[] actualResult = commonArray.findTempsGreaterThen(2.0);
        assertArrayEquals(expResult, actualResult, 0.0001);
    }

    @Test
    public void testFindTempsLessThanWithCommonArray() {
        double[] expResult = {-3.0, -4.0};
        double[] actualResult = commonArray.findTempsLessThen(-2.0);
        assertArrayEquals(expResult, actualResult, 0.0001);
    }

    @Test
    public void testDeviationWithCommonArray() {
        double expResult = 2.7386127875258306;
        double actualResult = commonArray.deviation();

        assertEquals(expResult, actualResult, 0.0001);
    }

    @Test
    public void testAddTempsWithCommonArray() {
        double expResult = 3.0;
        double actualResult = commonArray.addTemps(1.0, 2.0);

        assertEquals(expResult, actualResult, 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        emptyArray.average();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinWithEmptyArray() {
        emptyArray.min();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxWithEmptyArray() {
        emptyArray.max();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToZeroWithEmptyArray() {
        emptyArray.findTempClosestToZero();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToValueWithEmptyArray() {
        emptyArray.findTempClosestToValue(13);
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;
        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test
    public void testCommonArrayWithSummaryStatistics() {
        String expResult = "0.0 2.7386127875258306 -4.0 4.0";
        String actualResult = commonArray.summaryStatistics().toString();
        assertEquals(expResult, actualResult);
    }

    @Test(expected = InputMismatchException.class)
    public void testMissMatchWithAddTemps() {
        commonArray.addTemps(-300.0);
    }

    @Test
    public void testAddTempsWithEnoughPlace() {
        commonArray.addTemps(1.0);
        double expResult = 3.0;
        double actualResult = commonArray.addTemps(2.0);
        assertEquals(expResult, actualResult, 0.0001);
    }
    @Test
    public void testSameModsFindClosestValue() {
        double expResult = 1.0;
        double actualResult = commonArray.findTempClosestToZero();
        assertEquals(expResult, actualResult, 0.0001);
    }

}
