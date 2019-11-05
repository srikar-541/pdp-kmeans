package kmeans;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

import mvc.View;
import point.Point;
import point.TwoDPoint;
import abstraction.AbstractTwoDView;

/**
 * This class represents a 2D implementation of the View for a Clustering Algorithm. This needs data
 * of type Map of Point and List of points to plot data on image plotter.
 */
public class TwoDClusterViewImpl extends AbstractTwoDView implements View<Map<Point, List<Point>>> {

  /**
   * This creates a new TwoDClusterViewImpl. It creates a new Image Plotter object which is used to
   * plot the line.
   */
  public TwoDClusterViewImpl() {
    super();
  }

  /**
   * This method generates a List of K Color objects randomly.
   *
   * @param k the number of random colors to be generated.
   * @return the List of K Color objects.
   */
  private List<Color> generateColors(int k) {
    List<Color> colors = new ArrayList<>();
    Random random = new Random();
    for (int i = 0; i < k; i++) {
      int r = random.nextInt(200 - i);
      int b = random.nextInt(200 - i);
      int g = random.nextInt(200 - i);
      Color color = new Color(r, b, g);
      if (colors.contains(color) || color == Color.BLACK || color == Color.WHITE) {
        i--;
        continue;
      }
      colors.add(color);
    }
    return colors;
  }

  /**
   * This method plots clusters and its corresponding points which are stores inside a map.
   *
   * @param clusters It is a map of Point and List of points which contains clustering.
   */
  @Override
  public void plot(Map<Point, List<Point>> clusters) {
    Collection<List<Point>> collection = clusters.values();
    List<List<TwoDPoint>> pointsLists = new ArrayList(collection);
    int k = pointsLists.size();
    List<Color> colorsList = generateColors(k);

    for (int i = 0; i < k; i++) {
      List<TwoDPoint> pointsList = pointsLists.get(i);
      Color color = colorsList.get(i);
      for (TwoDPoint point : pointsList) {
        plotter.addPoint((int) Math.round(point.getX()), (int) Math.round(point.getY()), color);
      }
    }
    write("output/cluster.png");
  }

  /**
   * This method sets the dimensions of the image plotter view.
   *
   * @param points The list of points that have to be plotted.
   */
  @Override
  public void setPlot(List<Point> points) {
    setHeightWidth(points);
  }
}
