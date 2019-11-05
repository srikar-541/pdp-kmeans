package mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mvc.Model;
import point.Point;

/**
 * This class represents a Mock model for the purposes of testing the controller. It implements  the
 * Model interface. This class mimics a K Means Clustering Model class by taking a List of Double as
 * generic param.
 */
public class ClusterMockModel implements Model<Map<Point, List<Point>>> {

  private StringBuilder logger;
  private final int uniqueId;

  /**
   * Constructor for this mock class. Takes a StringBuilder object and an integer as a unique
   * Identifier. It creates a new object of this class.
   *
   * @param logger   StringBuilder to which input points are logged.
   * @param uniqueId of this class  instance.
   */
  public ClusterMockModel(StringBuilder logger, int uniqueId) {
    this.logger = logger;
    this.uniqueId = uniqueId;
  }

  /**
   * This method takes the  input list of points and logs every point by appending to the
   * StringBuilder class variable.
   *
   * @param points The list of points for which model has to be fit.
   * @return outputs a new  Map  of Point and List of Points.
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
