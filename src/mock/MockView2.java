package mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import mvc.View;
import point.Point;
import point.TwoDPoint;

public class MockView2 implements View<Map<Point,List<Point>>> {

  private StringBuilder logger1;
  private StringBuilder logger2;
  private final int uniqueId;

  public MockView2(StringBuilder logger1, StringBuilder logger2, int uniqueId) {
    this.logger1 = logger1;
    this.logger2 = logger2;
    this.uniqueId = uniqueId;
  }


  /**
   * This method plots the data model and either displays or writes the plot as an image. The controller can call this
   * method and pass data to the view for plotting.
   *
   * @param clusters The model data that has to be plotted. It is generic and defined at the time of implementation.
   */
  @Override
  public void plot(Map<Point,List<Point>> clusters) {
    Collection<List<Point>> collection = clusters.values();
    List<List<TwoDPoint>> pointsLists = new ArrayList(collection);
    for (List<TwoDPoint> point : pointsLists) {
      for (Point p : point) {
        logger1.append(p.getX()+" "+p.getY()+"\n");
      }
    }
  }

  /**
   * This method prepares the view for plotting the model. All the The logic to set the plot such as setting dimensions
   * of the visual representation goes here.
   *
   * @param points The list of points that have to be plotted.
   */
  @Override
  public void setPlot(List<Point> points) {
    for (Point p : points) {
      logger2.append(p.getX()+" "+p.getY()+"\n");
    }
  }

  public String toString() {
    return uniqueId+"";
  }
}

