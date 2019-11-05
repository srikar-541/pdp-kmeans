package regression;

import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;

import mvc.View;
import point.Point;
import point.TwoDPoint;
import abstraction.AbstractTwoDView;

/**
 * This class represents a 2D implementation of the View for a LinearRegression model. This class
 * requires data to be of type List of Double to plot.
 */
public class TwoDRegressionViewImpl extends AbstractTwoDView implements View<List<Double>> {

  private int max_x;
  private int min_x;

  /**
   * This creates a new TwoDRegressionViewImpl. It creates a new Image Plotter object which is used
   * to plot the line.
   */
  public TwoDRegressionViewImpl() {
    super();
  }

  private void addPoints(List<Point> points) {
    List<TwoDPoint> twoDPoints = points.stream().
            map(a -> (TwoDPoint) a).collect(Collectors.toList());
    int min_x = Integer.MAX_VALUE;
    int max_x = Integer.MIN_VALUE;
    for (TwoDPoint point : twoDPoints) {
      min_x = Math.min(min_x, (int) Math.round(point.getX()));
      max_x = Math.max(max_x, (int) Math.round(point.getX()));
      plotter.addPoint((int) Math.round(point.getX()), (int) Math.round(point.getY()),
              Color.BLACK);
      this.max_x = max_x;
      this.min_x = min_x;
    }
  }

  @Override
  public void setPlot(List<Point> points) {
    addPoints(points);
    setHeightWidth(points);
  }

  @Override
  public void plot(List<Double> constants) {
    if (constants.size() == 0) {
      return;
    }
    int min_y = getY(this.min_x, constants);
    int max_y = getY(this.max_x, constants);
    plotter.addLine(this.min_x, min_y, this.max_x, max_y, Color.RED);
    write("program_output/linear.png");
  }

  private int getY(double x, List<Double> constants) {
    double a = constants.get(0);
    double b = constants.get(1);
    double c = constants.get(2);
    return (int) Math.round(0 - (c + a * x) / b);
  }
}
