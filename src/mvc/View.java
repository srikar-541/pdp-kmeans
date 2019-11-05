package mvc;

import java.util.List;

import point.Point;

/**
 * This interface represents a view for a data fitting application. It offers functionality to
 * display results to the user.
 *
 * @param <T> Since the data the view gets to be  displayed  to the user can be of any type, it
 *            takes a generic parameter.
 */
public interface View<T> {

  /**
   * This method plots the data model and either displays or writes the plot as an image. The
   * controller can call this method and pass data to the view for plotting.
   *
   * @param constants The model data that has to be plotted. It is generic and defined at the time
   *                  of implementation.
   */
  void plot(T constants);

  /**
   * This method prepares the view for plotting the model. All the The logic to set the plot such
   * as setting dimensions of the visual representation goes here.
   *
   * @param points The list of points that have to be plotted.
   */
  void setPlot(List<Point> points);
}
