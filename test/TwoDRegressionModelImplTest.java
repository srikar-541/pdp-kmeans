import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import point.Point;
import point.TwoDPoint;
import regression.TwoDRegressionModelImpl;

/**
 * This class carries tests for testing the TwoDLinearRegressionModelImpl class.
 */
public class TwoDRegressionModelImplTest {

  TwoDRegressionModelImpl r = new TwoDRegressionModelImpl();

  @Test
  public void testOne() {
    List<Point> l = new ArrayList<>();
    l.add(new TwoDPoint(1,1));
    l.add(new TwoDPoint(2,2));
    l.add(new TwoDPoint(3,3));
    List<Double> result = r.fit(l);
    String s = "[-0.7071067811865475, 0.7071067811865476, -2.220446049250313E-16]";
    Assert.assertEquals(result.toString(), s);
  }

  @Test
  public void testTwo() {
    List<Point> l = new ArrayList<>();
    l.add(new TwoDPoint(2,7));
    l.add(new TwoDPoint(3,9));
    l.add(new TwoDPoint(12,27));
    l.add(new TwoDPoint(9,  21));
    List<Double> result = r.fit(l);
    Assert.assertEquals("[0.8944271909999159, -0.4472135954999579, 1.341640786499874]",
            result.toString());
  }

  @Test
  public void testThree() {
    List<Point> l = new ArrayList<>();
    l.add(new TwoDPoint(0,7));
    l.add(new TwoDPoint(0,9));
    l.add(new TwoDPoint(0,27));
    List<Double> result = r.fit(l);
    Assert.assertEquals("[1.0, -0.0, 0.0]", result.toString());
  }

  @Test
  public void testFour() {
    List<Point> l = new ArrayList<>();
    l.add(new TwoDPoint(7,0));
    l.add(new TwoDPoint(8,0));
    l.add(new TwoDPoint(9,0));
    List<Double> result = r.fit(l);
    Assert.assertEquals("[6.123233995736766E-17, 1.0, -4.898587196589413E-16]",
            result.toString());
  }

  @Test
  public void testFive() {
    List<Point> l = new ArrayList<>();
    l.add(new TwoDPoint(7,1));
    l.add(new TwoDPoint(9,2));
    l.add(new TwoDPoint(8,2));
    l.add(new TwoDPoint(100,94));
    l.add(new TwoDPoint(100,95));
    l.add(new TwoDPoint(100,97));
    List<Double> result = r.fit(l);
    Assert.assertEquals("[0.7134701650687854, -0.7006856096401011, -4.544136846169508]",
            result.toString());
  }

  @Test
  public void testSix() {
    List<Point> l = new ArrayList<>();
    l.add(new TwoDPoint(7,10));
    l.add(new TwoDPoint(9,4));
    l.add(new TwoDPoint(8,100));
    l.add(new TwoDPoint(0, 0));
    l.add(new TwoDPoint(100,200));
    List<Double> result = r.fit(l);
    Assert.assertEquals("[0.9120465529562205, -0.4100866801551546, 3.1346890004294394]",
            result.toString());
  }

  @Test
  public void testSeven() {
    List<Point> l = new ArrayList<>();
    l.add(new TwoDPoint(-400, -264.84));
    l.add(new TwoDPoint(-397.49, -260.71));
    l.add(new TwoDPoint(-395.20, -252.99));
    l.add(new TwoDPoint(-393.79, -279.26));
    l.add(new TwoDPoint(-389.96, -265.33));
    l.add(new TwoDPoint(-386.52, -267.09));
    l.add(new TwoDPoint(-384.67, -269.49));
    l.add(new TwoDPoint(-68.09, -20.22));
    l.add(new TwoDPoint(-66.00, 16.87));
    l.add(new TwoDPoint(-65.34, 13.26));
    l.add(new TwoDPoint(-63.47, 6.56));
    l.add(new TwoDPoint(-60.17, 15.49));
    l.add(new TwoDPoint(-60.06, 21.38));
    l.add(new TwoDPoint(-58.20, 4.95));
    l.add(new TwoDPoint(-245.88, -164.05));
    l.add(new TwoDPoint(-241.41, -152.00));
    l.add(new TwoDPoint(-238.15, -115.90));
    l.add(new TwoDPoint(-235.70, -116.75));
    l.add(new TwoDPoint(-227.36, -134.36));
    l.add(new TwoDPoint(-226.69, -122.40));

    List<Double> result = r.fit(l);
    Assert.assertEquals("[-0.6412200374671909, 0.7673570639217275, -47.59327263541469]",
            result.toString());
  }

  @Test
  public void testEight() {

    List<Point> l = new ArrayList<>();
    l.add(new TwoDPoint(36.80, -201.34));
    l.add(new TwoDPoint(32.39, 116.79));
    l.add(new TwoDPoint(263.14, -253.52));
    l.add(new TwoDPoint(394.66, -177.23));
    l.add(new TwoDPoint(211.72, -219.71));
    l.add(new TwoDPoint(145.13, 41.00));
    l.add(new TwoDPoint(384.74, -397.27));
    l.add(new TwoDPoint(242.55, -271.38));
    l.add(new TwoDPoint(350.66, -122.46));
    List<Double> result = r.fit(l);
    Assert.assertEquals("[0.7872406207087065, 0.6166459317194677, -78.59240369508757]",
            result.toString());
  }
}