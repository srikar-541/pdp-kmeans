package kmeans;

import java.util.List;

import point.Point;
import point.TwoDPoint;

public class TwoDPointClusterModelImpl extends PointClusterModel {
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
