package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class TemperatureSeriesAnalysisTest {
    private TemperatureSeriesAnalysis emptySeries;
    private TemperatureSeriesAnalysis simpleSeries;
    private TemperatureSeriesAnalysis longSeries;
    private TemperatureSeriesAnalysis sameElementsSeries;

    @Before
    public void setUp() {
        emptySeries = new TemperatureSeriesAnalysis();
        sameElementsSeries = new TemperatureSeriesAnalysis(new double[]{1.0, 1.0, 1.0, 1.0, 1.0});
        simpleSeries = new TemperatureSeriesAnalysis(new double[]{1.0, 2.0, 3.0, -2.0});
        longSeries = new TemperatureSeriesAnalysis(new double[]{2.0, 5.0, -1.0, 1.0, 5.0, -10.0});
    }

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        emptySeries.average();
    }



    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }
    

}
