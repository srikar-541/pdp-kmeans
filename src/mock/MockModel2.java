package mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mvc.Model;
import point.Point;

public class MockModel2 implements Model<Map<Point, List<Point>>> {

  private StringBuilder logger;
  private final int uniqueId;

  public MockModel2(StringBuilder logger, int uniqueId) {
    this.logger = logger;
    this.uniqueId = uniqueId;
  }

  /**
   * This function processes the points given to it by applying a fitting algorithm. The core logic of the data fitting
   * goes in this method. It gives output of generic type which is defined at the time of creating the model object.
   *
   * @param points The list of points for which model has to be fit.
   * @return Output is the data after fitting the data.
   */
  @Override
  public Map<Point, List<Point>> fit(List<Point> points) {
    for (Point point : points) {
      logger.append(point.getX() + " " + point.getY() + "\n");
    }
    return new HashMap<Point, List<Point>>();
  }

  @Override
  public String toString() {
    return this.uniqueId + "";
  }
}