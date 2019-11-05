package regression;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import mvc.Model;
import point.Point;
import point.TwoDPoint;

/**
 * This model is an implementation of the Model interface which takes in a generic argument.
 * This implementation requires the view to return data of type List of Double
 * after processing the 2D points.
 */
public class TwoDRegressionModelImpl implements Model<List<Double>> {

  /**
   * This method applies the regression algorithm on the given set of points.
   * It returns a set of constants which are regression constants used for plotting.
   * @param     points The list of points for which model has to be fit.
   * @return    List of constants which represent a line.
   */
  @Override
  public List<Double> fit(List<Point> points) {
    List<Double> constants = new ArrayList<>();
    TwoDPoint meanPoint = getMidPoint(points);

    List<Point> diff = points.stream().map((a) -> a.subtract(meanPoint)).
            collect(Collectors.toList());

    double sXY = diff.stream().mapToDouble(Point::multiplyDimensions).sum();
    TwoDPoint sumSquare = new TwoDPoint();

    diff = diff.stream().map(Point::square).collect(Collectors.toList());
    sumSquare = (TwoDPoint) diff.stream().reduce(sumSquare, Point::add);

    double sXX = sumSquare.getX();
    double sYY = sumSquare.getY();

    double t = getT(sXX,sYY,sXY);
    double a = Math.cos(t / 2);
    double b = Math.sin(t / 2);
    double c = 0 - a * meanPoint.getX() - b * meanPoint.getY();

    constants.add(a);
    constants.add(b);
    constants.add(c);

    return constants;
  }

  private double getT(double sXX, double sYY, double sXY) {
    double d = 2 * sXY / (sXX - sYY);
    double theta = Math.atan(d);

    double val1 = find(sXX, sYY, sXY, theta + Math.toRadians(180));
    double val2 = find(sXX, sYY, sXY, theta);
    double t = theta;
    if (val1 > val2) {
      t = theta + Math.toRadians(180);
    }

    return t;
  }

  private double find(double sXX, double sYY, double sXY, double theta) {
    return (sYY - sXX) * Math.cos(theta) - 2 * sXY * Math.sin(theta);
  }

  private TwoDPoint getMidPoint(List<Point> points) {
    TwoDPoint midPoint = new TwoDPoint();
    midPoint = points.stream().map(a -> (TwoDPoint) a).
            reduce(midPoint, TwoDPoint::add).divideBy(points.size());
    return midPoint;
  }
}
