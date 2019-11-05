

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import org.junit.Assert;

import java.util.List;
import java.util.Map;


import kmeans.KMeansConfigurations;
import kmeans.TwoDPointClusterModelImpl;
import point.Point;
import point.TwoDPoint;


public class TwoDPointClusterModelImplTest {

  TwoDPointClusterModelImpl t;
  KMeansConfigurations config;
  List<Point> l = null;

  @Before
  public void setup() {
    l = new ArrayList<Point>();
  }

  @Test
  public void testOne() {
    config = KMeansConfigurations.getBuilder().clusterCount(4).build();
    t = new TwoDPointClusterModelImpl(config);
    Point po = new TwoDPoint(1, 1);
    l.add(po);
    l.add(new TwoDPoint(3, 4));
    l.add(new TwoDPoint(4, 5));
    l.add(new TwoDPoint(5, 6));
    Map<Point, List<Point>> k = t.fit(l);
//    List<Point> result2 = k.values().stream()
//            .collect(Collectors.toList());
//    for (Point p : result2) {
//      System.out.println(p.getX()+"  "+p.getY());
//    }
    //System.out.println(fuck);


    List<Point> l1 = k.get(po);
//    System.out.println(l1);
    Assert.assertEquals("", "");
  }
}

