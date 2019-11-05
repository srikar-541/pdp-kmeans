package abstraction;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import plotter.ImagePlotter;
import point.Point;
import point.TwoDPoint;

/**
 * This class abstracts the  common functionality between Two Dimension Views. It offers methods to
 * set the Height and Width of the  plot and a method to write the plot as a .png image to disk
 */
public abstract class AbstractTwoDView {
  protected ImagePlotter plotter;

  public AbstractTwoDView() {
    this.plotter = new ImagePlotter();
  }

  /**
   * This method takes a list of points and sets the dimensions of the plot required to  plot those
   * points.
   *
   * @param points list of points to be plotted.
   */
  protected void setHeightWidth(List<Point> points) {
    double max_x = Integer.MIN_VALUE;
    double min_x = Integer.MAX_VALUE;
    double max_y = Integer.MIN_VALUE;
    double min_y = Integer.MAX_VALUE;
    List<TwoDPoint> twoDPoints = points.stream().map(a -> (TwoDPoint) a).
            collect(Collectors.toList());
    for (TwoDPoint point : twoDPoints) {
      double x = point.getX();
      double y = point.getY();

      max_x = Math.max(max_x, x);
      max_y = Math.max(max_y, y);
      min_x = Math.min(min_x, x);
      min_y = Math.min(min_y, y);
    }

    max_x = Math.ceil(max_x + 10);
    max_y = Math.ceil(max_y + 10);
    min_x = Math.floor(min_x - 10);
    min_y = Math.floor(min_y - 10);

    int height = (int) Math.abs(Math.round(max_y - min_y));
    int width = (int) Math.abs(Math.round(max_x - min_x));

    plotter.setDimensions((int) Math.round(min_x), (int) Math.round(max_x),
            (int) Math.round(min_y), (int) Math.round(max_y));

    plotter.setHeight(height + 100);
    plotter.setWidth(width + 100);
  }

  /**
   * This method writes the plot as a .png image  to the disk at a specified location.
   *
   * @param path location to store the image.
   */
  protected void write(String path) {
    try {
      plotter.write(path);
    } catch (IOException e) {
      System.out.println("Error");
    }
  }
}
