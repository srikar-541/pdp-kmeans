package mvc;

import java.util.List;

import point.Point;

/**
 * This interface is a representation of a model which can be used across different
 * fitting models.
 * It offers only one function which fits given set of points.
 *
 * @param    <T> It takes in param as a generic type which defines the type of the data it
 *               expects after processing the points.
 *
 */
public interface Model<T> {

  /**
   * This function processes the points given to it by applying a fitting algorithm.
   * The core logic of the data fitting goes in this method. It gives output of
   * generic type which is defined at the time of creating the model object.
   * @param     points The list of points for which model has to be fit.
   * @return    Output is the data after fitting the data.
   */
  T fit(List<Point> points);
}
