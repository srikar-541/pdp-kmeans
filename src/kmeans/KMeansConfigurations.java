package kmeans;

import java.util.function.BiFunction;

import point.Point;

/**
 * This class is a config class which sets configuration settings for the KMeansClustering
 * technique.
 */
public class KMeansConfigurations {
  private final Integer iterations;
  private final Double threshold;
  private final Integer ransac;
  private final Integer clusterCount;
  private final BiFunction<Point, Point, Double> distanceCalculator;

  /**
   * This creates a new object of the KMeansConfiguration config. It is a private constructor and is
   * used by the inner ConfigBuilder class to build objects of this class
   *
   * @param iterations   Maximum no of iterations to be done in the KMeansClustering technique.
   * @param threshold    Threshold of the error difference between two consecutive iterations as a
   *                     percentage.
   * @param ransac       RANSAC implementation of KMeans which gives tells the number of times the
   *                     KMeans clustering operatios has to be done for getting the best results.
   * @param clusterCount Number of clusters the data is to be divided into.
   * @param calculator   Distance calculator bifunction which tells the way in which distance is to
   *                     be calcuated between two points.
   */
  public KMeansConfigurations(Integer iterations, Double threshold,
                              Integer ransac, Integer clusterCount,
                              BiFunction<Point, Point, Double> calculator) {
    this.iterations = iterations;
    this.clusterCount = clusterCount;
    this.ransac = ransac;
    this.threshold = threshold;
    this.distanceCalculator = calculator;
  }

  /**
   * A getter method for the distanceCalculator  BiFunction object.
   *
   * @return the distanceCalculator.
   */
  public BiFunction<Point, Point, Double> getDistanceCalculator() {
    return this.distanceCalculator;
  }

  /**
   * A getter method for the number  of iterations the KMeans Clustering algorithm  has to  be run.
   *
   * @return the number of iterations as an integer.
   */
  public int getIterations() {
    return this.iterations;
  }

  /**
   * A  getter method for the threshold percentage for the KMeans Algorithm.
   *
   * @return the threshold as a  double.
   */
  public double getThreshold() {
    return this.threshold;
  }

  /**
   * A getter method for the number of RANSAC iterations.
   *
   * @return the  number   of RANSAC iterations as an integer.
   */
  public int getRansac() {
    return this.ransac;
  }

  /**
   * A getter  method  for the number of  clusters for the  KMeans Clustering algorithm.
   *
   * @return the  number of clusters as an integer.
   */
  public int getClusterCount() {
    return this.clusterCount;
  }

  /**
   * A getter for a Builder  class object for the class KMeansConfigurations.
   *
   * @return the ConfigBuilder object.
   */
  public static ConfigBuilder getBuilder() {
    return new ConfigBuilder();
  }

  /**
   * A Builder class for the class KMeansConfigurations.
   */
  public static class ConfigBuilder {
    private Integer iterations;
    private Double threshold;
    private Integer ransac;
    private Integer clusterCount;
    private BiFunction<Point, Point, Double> distanceCalculator;

    /**
     * A private constructor for the ConfigBuilder object.It sets the default values for the
     * KMeansConfigurations object.
     */
    private ConfigBuilder() {
      this.iterations = 100;
      this.threshold = 0.01;
      this.ransac = 10;
      this.clusterCount = 5;
      this.distanceCalculator = Point::getEuclideanDistance;
    }

    /**
     * Sets the number of iterations for the KMeans Clustering algorithm.
     *
     * @param iterations number  of iterations.
     * @return ConfigBuilder object after setting the  number of  iterations.
     */
    public ConfigBuilder iterations(int iterations) {
      this.iterations = iterations;
      return this;
    }

    /**
     * Sets the threshold percentage for the KMeans Clustering algorithm.
     *
     * @param threshold percentage to be set.
     * @return ConfigBuilder object after setting the  threshold
     */
    public ConfigBuilder threshold(double threshold) {
      this.threshold = threshold;
      return this;
    }

    /**
     * Sets the RANSAC iterations of the KMeans Clustering algorithm.
     *
     * @param ransac number of  ransac iterations.
     * @return ConfigBuilder object after setting the RANSAC iterations.
     */
    public ConfigBuilder ransac(int ransac) {
      this.ransac = ransac;
      return this;
    }

    /**
     * Sets the  number of  clusters for  the  KMeans  Clustering algorithm.
     *
     * @param clusterCount number of clusters.
     * @return ConfigBuilder  object after  setting number  of clusters.
     */
    public ConfigBuilder clusterCount(int clusterCount) {
      this.clusterCount = clusterCount;
      return this;
    }

    /**
     * Sets the BiFunction which specifies the way the distance between points has to be
     * calculated.
     *
     * @param distanceCalculator BiFunction which  calculates distance
     * @return ConfigBuilder  object after  setting number  of distanceCalculator.
     */
    public ConfigBuilder distanceCalculator(BiFunction<Point, Point, Double> distanceCalculator) {
      this.distanceCalculator = distanceCalculator;
      return this;
    }

    /**
     * Builds the KMeansConfigurations object.
     *
     * @return KMeansConfiguration object from the params set.
     */
    public KMeansConfigurations build() {
      return new KMeansConfigurations(this.iterations, this.threshold,
              this.ransac, this.clusterCount, this.distanceCalculator);
    }
  }
}
