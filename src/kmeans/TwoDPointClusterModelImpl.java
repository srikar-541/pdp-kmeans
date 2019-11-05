package kmeans;

import java.util.List;

import point.Point;
import point.TwoDPoint;

/**
 * This class is a TwoD Point implementation of the K-Means Clustering Model.  It offers a method
 * which fits a cluster model to the data and returns centroids and their corresponding points in a
 * Map.
 */
public class TwoDPointClusterModelImpl extends PointClusterModel {
  /**
   * Constructor for the TwoDPointClusterModelImpl class.
   *
   * @param config a KMeansConfigurations object which specifies the details of the clustering
   *               algorithm.
   */
  public TwoDPointClusterModelImpl(KMeansConfigurations config) {
    super(config);
  }

  @Override
  protected Point getMidPoint(List<Point> points) {
    TwoDPoint midPoint = new TwoDPoint();
    midPoint = points.stream().map(a -> (TwoDPoint) a).
            reduce(midPoint, TwoDPoint::add).divideBy(points.size());
    return midPoint;
  }
}
