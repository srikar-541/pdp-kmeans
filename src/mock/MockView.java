package mock;

import java.util.List;

import mvc.View;
import point.Point;

public class MockView implements View<List<Double>> {

  private StringBuilder logger1;
  private StringBuilder logger2;
  private final int uniqueId;

  public MockView(StringBuilder logger1, StringBuilder logger2, int uniqueId) {
    this.logger1 = logger1;
    this.logger2 = logger2;
    this.uniqueId = uniqueId;
  }


  /**
   * This method plots the data model and either displays or writes the plot as an image. The controller can call this
   * method and pass data to the view for plotting.
   *
   * @param constants The model data that has to be plotted. It is generic and defined at the time of implementation.
   */
  @Override
  public void plot(List<Double> constants) {
    for (Double d: constants) {
      logger1.append(d+" ");
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
