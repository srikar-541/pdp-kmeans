package point;

/**
 * This interface represents a point of data. A point can be multidimensional. This interface also
 * offers methods on a Point.
 */
public interface Point {

  /**
   * This method calculates the Euclidean distance between this  point and another point.
   *
   * @param other the other point from which the  Euclidean distance to this point has to be
   *              calculated
   * @return the Euclidean distance as a double.
   */
  double getEuclideanDistance(Point other);

  /**
   * Adds two points and returns the resulting point. The value of corresponding dimensions is added
   * to form a new point.
   *
   * @param other The point which has  to be added to this point.
   * @return new Point object resulting from adding the two points.
   */
  Point add(Point other);

  /**
   * Divides each dimension value of this point with a constant.
   *
   * @param divisor the value with which this point has to be divided.
   * @return new Point  object resulting from dividing by a constant.
   */
  Point divideBy(double divisor);

  /**
   * Subtracts two points and returns the resulting point. The value of corresponding dimensions is
   * subtracted to form a new point.
   *
   * @param other The point which has  to be subtracted from this point.
   * @return new Point  resulting from subtracting two points.
   */
  Point subtract(Point other);

  /**
   * Squares the value of each  of the dimensions and returns a new Point with the resulting
   * values.
   *
   * @return new Point whose  dimension values are the square of those of the calling point object.
   */
  Point square();

  /**
   * This method multiplies all the dimension values of this point and returns the resulting value.
   *
   * @return product of all the dimension values as a double.
   */
  double multiplyDimensions();

  double getX();

  double getY();
}
