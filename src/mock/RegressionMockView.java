package mock;

import java.util.List;

import mvc.View;
import point.Point;

/**
 * This class represents a Mock view for the purposes of testing of the controller. It implements
 * the View interface. This class mimics a Regression View class by taking a List of Double  as
 * generic param.
 */
public class RegressionMockView implements View<List<Double>> {

  private StringBuilder logger1;
  private StringBuilder logger2;
  private final int uniqueId;

  /**
   * A  constructor for this class. Takes two StringBuilders as  parameters  which log  the inputs
   * to the public methods.
   *
   * @param logger1  StringBuilder used to log inputs to the plot  method.
   * @param logger2  StringBuilder used to log  inputs to the  setPlot  method.
   * @param uniqueId of this class  instance.
   */
  public RegressionMockView(StringBuilder logger1, StringBuilder logger2, int uniqueId) {
    this.logger1 = logger1;
    this.logger2 = logger2;
    this.uniqueId = uniqueId;
  }


  /**
   * This method logs the input fed  to it  to  the logger1 StringBuilder class variable.
   *
   * @param constants The model data that has to be plotted. It is generic and defined at the time
   *                  of implementation.
   */
  @Override
  public void plot(List<Double> constants) {
    for (Double d : constants) {
      logger1.append(d + " ");
    }
  }

  /**
   * This method logs  the input fed to  it  in the logger2 StringBuilder class variable.
   *
   * @param points The list of points that have to be plotted.
   */
  @Override
  public void setPlot(List<Point> points) {
    for (Point p : points) {
      logger2.append(p.getX() + " " + p.getY() + "\n");
    }
  }

  @Override
  public String toString() {
    return uniqueId + "";
  }
}
