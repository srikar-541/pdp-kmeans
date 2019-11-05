import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import org.junit.Assert;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import kmeans.KMeansConfigurations;
import kmeans.TwoDPointClusterModelImpl;
import point.Point;
import point.TwoDPoint;

/**
 * This class contains tests for the KMeansClustering model.
 */
public class TwoDPointClusterModelImplTest {

  TwoDPointClusterModelImpl t;
  KMeansConfigurations config;
  List<Point> l = null;

  @Before
  public void setup() {
    l = new ArrayList<>();
  }

  @Test
  public void testOne() {
    config = KMeansConfigurations.getBuilder().clusterCount(1).build();
    t = new TwoDPointClusterModelImpl(config);
    Point po = new TwoDPoint(1, 1);
    l.add(po);
    l.add(new TwoDPoint(1.1, 1.1));
    l.add(new TwoDPoint(1.2, 1.2));
    l.add(new TwoDPoint(1.3, 1.3));
    Map<Point, List<Point>> k = t.fit(l);
    for (Map.Entry<Point, List<Point>> entry : k.entrySet()) {
      Point key = entry.getKey();
      Assert.assertEquals(key.getX(), 1.15, 0.0001);
      Assert.assertEquals(key.getY(), 1.15, 0.0001);
      List<Point> values = entry.getValue();
      Collections.sort(values, (o1, o2) -> (int) (o2.getY() - o1.getY()));
      List<Point> temp = new ArrayList<>(l);
      Collections.sort(temp, (o1, o2) -> (int) (o2.getY() - o1.getY()));
      for (int i = 0; i < temp.size(); i++) {
        Point p1 = temp.get(i);

        Point p2 = values.get(i);
        Assert.assertEquals(p1.getY(), p2.getY(), 0.00001);
        Assert.assertEquals(p1.getX(), p2.getX(), 0.00001);
      }
    }
  }

  @Test
  public void testTwo() {
    config = KMeansConfigurations.getBuilder().clusterCount(2).build();
    t = new TwoDPointClusterModelImpl(config);
    Point po = new TwoDPoint(1, 1);
    l.add(po);
    l.add(new TwoDPoint(10, 10));
    l.add(new TwoDPoint(20, 20));
    l.add(new TwoDPoint(30, 30));
    l.add(new TwoDPoint(-30, -30));
    l.add(new TwoDPoint(-20, -20));
    l.add(new TwoDPoint(-10, -10));
    l.add(new TwoDPoint(-1, -1));
    Map<Point, List<Point>> k = t.fit(l);
    for (Map.Entry<Point, List<Point>> entry : k.entrySet()) {
      Point key = entry.getKey();
      Assert.assertEquals(key.getX(), 0, 0.0001);
      Assert.assertEquals(key.getY(), 0, 0.0001);
      List<Point> values = entry.getValue();
      Collections.sort(values, (o1, o2) -> (int) (o2.getY() - o1.getY()));
      List<Point> temp = new ArrayList<>(l);
      Collections.sort(temp, (o1, o2) -> (int) (o2.getY() - o1.getY()));
      for (int i = 0; i < temp.size(); i++) {
        Point p1 = temp.get(i);

        Point p2 = values.get(i);
        Assert.assertEquals(p1.getY(), p2.getY(), 0.00001);
        Assert.assertEquals(p1.getX(), p2.getX(), 0.00001);
      }
    }
  }

  @Test
  public void testThree() {
    config = KMeansConfigurations.getBuilder().clusterCount(1).build();
    t = new TwoDPointClusterModelImpl(config);
    Point po = new TwoDPoint(1, 1);
    l.add(po);
    l.add(new TwoDPoint(11, 11));
    l.add(new TwoDPoint(111, 111));
    l.add(new TwoDPoint(1111, 1111));
    Map<Point, List<Point>> k = t.fit(l);
    for (Map.Entry<Point, List<Point>> entry : k.entrySet()) {
      Point key = entry.getKey();
      Assert.assertEquals(key.getX(), 308.5, 0.0001);
      Assert.assertEquals(key.getY(), 308.5, 0.0001);
      List<Point> values = entry.getValue();
      Collections.sort(values, (o1, o2) -> (int) (o2.getY() - o1.getY()));
      List<Point> temp = new ArrayList<>(l);
      Collections.sort(temp, (o1, o2) -> (int) (o2.getY() - o1.getY()));
      for (int i = 0; i < temp.size(); i++) {
        Point p1 = temp.get(i);

        Point p2 = values.get(i);
        Assert.assertEquals(p1.getY(), p2.getY(), 0.00001);
        Assert.assertEquals(p1.getX(), p2.getX(), 0.00001);
      }
    }
  }

  @Test
  public void testFour() {
    config = KMeansConfigurations.getBuilder().clusterCount(3).build();
    t = new TwoDPointClusterModelImpl(config);
    l.add(new TwoDPoint(10, 10));
    l.add(new TwoDPoint(20, 20));
    l.add(new TwoDPoint(30, 30));
    Collections.sort(l, (o1, o2) -> (int) (o1.getX() - o2.getX()));
    Map<Point, List<Point>> k = t.fit(l);
    int j = 0;
    for (Map.Entry<Point, List<Point>> entry : k.entrySet()) {
      Point key = entry.getKey();
      Point toCom = l.get(j);
      j++;
      Assert.assertEquals(key.getX(), toCom.getX(), 0.0001);
      Assert.assertEquals(key.getY(), toCom.getY(), 0.0001);
      List<Point> values = entry.getValue();
      Collections.sort(values, (o1, o2) -> (int) (o2.getY() - o1.getY()));
      List<Point> temp = new ArrayList<>();
      temp.add(toCom);
      Collections.sort(temp, (o1, o2) -> (int) (o2.getY() - o1.getY()));
      for (int i = 0; i < temp.size(); i++) {
        Point p1 = temp.get(i);
        Point p2 = values.get(i);
        Assert.assertEquals(p1.getY(), p2.getY(), 0.00001);
        Assert.assertEquals(p1.getX(), p2.getX(), 0.00001);
      }
    }
  }
}

