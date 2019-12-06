package kmeans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mvc.Model;
import point.Point;

/**
 * This class abstracts the common functionality of any TwoD ClusterModelImpl. It has a method which
 * fits  a  K-Means Clustering model to the data. It also has several helper  methods  which are
 * called  by  the  fit() method.
 */
abstract class PointClusterModel implements Model<Map<Point, List<Point>>> {

  private Map<Point, List<Point>> clusters;
  private Double previousClusteringError;
  private final KMeansConfigurations config;

  /**
   * This constructor abstracts the object creation of child classes by taking config as input while
   * creating any implementation of the cluster model.
   *
   * @param config The config object of the KMeans clustering which has fields like number of
   *               clusters, number of ransac iterations etc(Ref KMeansConfigurations class)
   */
  protected PointClusterModel(KMeansConfigurations config) {
    this.config = config;
  }

  @Override
  public Map<Point, List<Point>> fit(List<Point> points) {
    if (config.getClusterCount() > points.size()) {
      throw new IllegalArgumentException("Number of clusters greater than number of points.");
    }
    double ransacError = Double.POSITIVE_INFINITY;
    Map<Point, List<Point>> bestClustering = null;

    for (int j = 0; j < this.config.getRansac(); j++) {
      for (int i = 0; i < this.config.getIterations(); i++) {
        if (i == 0) {
          List<Point> centers = getKRandomCenters(points, this.config.getClusterCount());
          initializeClusters(centers);   /* Step 1 */
          this.previousClusteringError = Double.POSITIVE_INFINITY; /* Step 2 */
          assignCentersToPoints(points); /* Step 3 */
        } else {
          List<Point> newCenters = computeNewCenters(); /* Step 4 */
          double newError = computeError(newCenters); /* Step 5 */
          if ((newError - this.previousClusteringError) /
                  this.previousClusteringError >= this.config.getThreshold() / 100.0
                  || this.previousClusteringError == Double.POSITIVE_INFINITY) /* Step 6 */ {
            initializeClusters(newCenters);
            assignCentersToPoints(points); /* Step 3 */
            this.previousClusteringError = newError;
          } else {
            if (newError < ransacError) {
              ransacError = newError;
              bestClustering = this.clusters;
            }
          }
        }
      }
    }
    return bestClustering; /* report best clustering after ransac iterations */
  }

  private Point getNewCenter(Point point) {
    double minDistance = Double.POSITIVE_INFINITY;
    Point newCenter = null;
    double distance;
    for (Point center : clusters.keySet()) {
      distance = config.getDistanceCalculator().apply(center, point);
      if (distance < minDistance) {
        minDistance = distance;
        newCenter = center;
      }
    }
    return newCenter;
  }

  private double computeError(List<Point> newCenters) {
    double error = 0;
    Collection<List<Point>> collection = clusters.values();
    List<List<Point>> listValues = new ArrayList(collection);
    for (int i = 0; i < listValues.size(); i++) {
      Point newCentre = newCenters.get(i);
      List<Point> tempList = listValues.get(i);
      error += computeCenterError(newCentre, tempList);
    }
    return error;
  }

  private double computeCenterError(Point newCenter, List<Point> points) {
    double error = 0;
    for (Point point : points) {
      error += newCenter.getEuclideanDistance(point);
    }
    return error / points.size();
  }

  private void initializeClusters(List<Point> centers) {
    this.clusters = new LinkedHashMap<>();
    for (Point center : centers) {
      this.clusters.put(center, new ArrayList<>());
    }
  }

  private void assignCentersToPoints(List<Point> points) {
    for (Point point : points) {
      Point center = getNewCenter(point);
      List<Point> centerList = clusters.get(center);
      centerList.add(point);
      clusters.put(center, centerList);
    }
  }

  private List<Point> computeNewCenters() {
    List<Point> newCenters = new ArrayList<>();
    for (List<Point> list : clusters.values()) {
      Point midPoint = getMidPoint(list);
      newCenters.add(midPoint);
    }
    return newCenters;
  }

  private List<Point> getKRandomCenters(List<Point> points, int clusterCount) {
    return getKRandomPoints(points, clusterCount);
  }

  protected abstract Point getMidPoint(List<Point> points);

  private List<Point> getKRandomPoints(List<Point> points, int clusterCount) {
    List<Point> result = new ArrayList<>();
    Collections.shuffle(points);
    for (int i = 0; i < clusterCount; i++) {
      result.add(points.get(i));
    }
    return result;
  }
}
