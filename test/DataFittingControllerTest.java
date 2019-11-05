import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import abstraction.DataFittingController;
import kmeans.KMeansConfigurations;
import mock.RegressionMockModel;
import mock.ClusterMockModel;
import mock.RegressionMockView;
import mock.ClusterMockView;
import kmeans.TwoDClusterViewImpl;
import kmeans.TwoDPointClusterModelImpl;
import mvc.Controller;
import mvc.Model;
import mvc.View;
import point.Point;
import regression.TwoDRegressionModelImpl;
import regression.TwoDRegressionViewImpl;

/**
 * This  class is meant for the sole purpose of testing the  controller of  our application.
 */
public class DataFittingControllerTest {

  Controller controller;

  /**
   * This method is for the sole purpose of testing I/O between Controller and a Mock Model that
   * mimics a Model by implementing it. It asserts whether the input passed to the  model from the
   * controller  is the same as the input received by the model.
   *
   * @throws FileNotFoundException if the file is not found.
   */
  @Test
  public void testControllerModelRegression() throws FileNotFoundException {
    StringBuilder log = new StringBuilder();
    RegressionMockModel model = new RegressionMockModel(log, 123456);
    File file = new File("data/linedata-1.txt");
    String expectedString = readData(file);
    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
    controller = new DataFittingController(bufferedReader, model,
            new TwoDRegressionViewImpl());
    controller.process();
    Assert.assertEquals(expectedString, log.toString());
    Assert.assertEquals("123456", model.toString());
  }

  /**
   * This method is for the sole purpose of testing I/O between Controller and a mock View that
   * mimics a View by implementing it. It asserts whether the input passed to the view from the
   * controller  is the same as the input received by the view.
   *
   * @throws FileNotFoundException if the file is not found.
   */
  @Test
  public void testControllerViewRegression() throws FileNotFoundException {
    StringBuilder log1 = new StringBuilder();
    StringBuilder log2 = new StringBuilder();
    RegressionMockView view = new RegressionMockView(log1, log2, 123456);
    Model<List<Double>> model = new TwoDRegressionModelImpl();
    File file = new File("data/linedata-1.txt");
    String expectedString = readData(file);
    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
    controller = new DataFittingController(bufferedReader, model, view);
    controller.process();
    String expected = "-0.6255529367619199 0.7801817245414926 -39.50137856788295 ";
    Assert.assertEquals(expected, log1.toString());
    Assert.assertEquals(expectedString, log2.toString());
    Assert.assertEquals("123456", view.toString());
  }

  private String readData(File file) throws FileNotFoundException {
    Scanner sc = new Scanner(file);
    StringBuilder sb = new StringBuilder();
    while (sc.hasNextLine()) {
      String[] point = sc.nextLine().split(" ");
      if (point.length != 2) {
        throw new IllegalArgumentException("Invalid data");
      }
      String xCoordinate = Double.parseDouble(point[0]) + "";
      String yCoordinate = Double.parseDouble(point[1]) + "";
      sb.append(xCoordinate + " " + yCoordinate + "\n");
    }
    return sb.toString();
  }

  /**
   * This method is for the sole purpose of testing I/O between Controller and a Mock Model that
   * mimics a Model by implementing it. It asserts whether the input passed to the  model from the
   * controller  is the same as the input received by the model.
   *
   * @throws FileNotFoundException if the file is not found.
   */
  @Test
  public void testControllerModel2Regression() {
    StringBuilder logger = new StringBuilder();
    Model model = new RegressionMockModel(logger, 15678);
    View view = new TwoDRegressionViewImpl();
    String s = "1.0 1.0\n2.0 2.0\n3.0 3.0\n4.0 4.0\n";
    Reader in = new StringReader(s);
    Controller controller = new DataFittingController(in, model, view);
    controller.process();
    Assert.assertEquals(s, logger.toString());
    Assert.assertEquals("15678", model.toString());
  }

  /**
   * This method is for the sole purpose of testing I/O between Controller and a Mock View that
   * mimics a View by implementing it. It asserts whether the input passed to the view from the
   * controller  is the same as the input received by the view.
   *
   * @throws FileNotFoundException if the file is not found.
   */
  @Test
  public void testControllerView2Regression() {
    Model model = new TwoDRegressionModelImpl();
    StringBuilder log1 = new StringBuilder();
    StringBuilder log2 = new StringBuilder();
    View view = new RegressionMockView(log1, log2, 12345);
    String s = "1.0 1.0\n2.0 2.0\n3.0 3.0\n4.0 4.0\n";
    Reader in = new StringReader(s);
    Controller controller = new DataFittingController(in, model, view);
    controller.process();
    Assert.assertEquals("-0.7071067811865475 0.7071067811865476 -2.220446049250313E-16 ",
            log1.toString());
    Assert.assertEquals(s, log2.toString());
    Assert.assertEquals("12345", view.toString());
  }

  /**
   * This method is for the sole purpose of testing I/O between Controller and a Mock Model that
   * mimics a Model by implementing it. It asserts whether the input passed to the  model from the
   * controller  is the same as the input received by the model.
   *
   * @throws FileNotFoundException if the file is not found.
   */
  @Test
  public void testControllerModelClustering() throws FileNotFoundException {
    StringBuilder log = new StringBuilder();
    ClusterMockModel model = new ClusterMockModel(log, 123456);
    File file = new File("data/clusterdata-1.txt");
    String expectedString = readData(file);
    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
    controller = new DataFittingController(bufferedReader, model,
            new TwoDClusterViewImpl());
    controller.process();
    Assert.assertEquals(expectedString, log.toString());
    Assert.assertEquals("123456", model.toString());
  }

  /**
   * This method is for the sole purpose of testing I/O between Controller and a mock View that
   * mimics a View by implementing it. It asserts whether the input passed to the view from the
   * controller  is the same as the input received by the view.
   *
   * @throws FileNotFoundException if the file is not found.
   */
  @Test
  public void testControllerViewClustering() throws FileNotFoundException {
    StringBuilder log1 = new StringBuilder();
    StringBuilder log2 = new StringBuilder();
    RegressionMockView view = new RegressionMockView(log1, log2, 123456);
    KMeansConfigurations config = KMeansConfigurations.getBuilder()
            .clusterCount(2).build();
    Model<Map<Point, List<Point>>> model = new TwoDPointClusterModelImpl(config);
    File file = new File("data/clusterdata-1.txt");
    String expectedString = readData(file);
    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
    controller = new DataFittingController(bufferedReader, model, view);
    controller.process();
    String expected = "-0.6255529367619199 0.7801817245414926 -39.50137856788295 ";
    Assert.assertEquals(expected, log1.toString());
    Assert.assertEquals(expectedString, log2.toString());
    Assert.assertEquals("123456", view.toString());
  }

  /**
   * This method is for the sole purpose of testing I/O between Controller and a Mock Model that
   * mimics a Model by implementing it. It asserts whether the input passed to the  model from the
   * controller  is the same as the input received by the model.
   *
   * @throws FileNotFoundException if the file is not found.
   */
  @Test
  public void testControllerModel2Clustering() {
    StringBuilder logger = new StringBuilder();
    Model model = new ClusterMockModel(logger, 15678);
    View view = new TwoDClusterViewImpl();
    String s = "1.0 1.0\n2.0 2.0\n3.0 3.0\n4.0 4.0\n";
    Reader in = new StringReader(s);
    Controller controller = new DataFittingController(in, model, view);
    controller.process();
    Assert.assertEquals(s, logger.toString());
    Assert.assertEquals("15678", model.toString());
  }

  /**
   * This method is for the sole purpose of testing I/O between Controller and a mock View that
   * mimics a View by implementing it. It asserts whether the input passed to the view from the
   * controller  is the same as the input received by the view.
   *
   * @throws FileNotFoundException if the file is not found.
   */
  @Test
  public void testControllerView2Clustering() {
    KMeansConfigurations config = KMeansConfigurations.getBuilder()
            .clusterCount(2).build();
    Model model = new TwoDPointClusterModelImpl(config);
    StringBuilder log1 = new StringBuilder();
    StringBuilder log2 = new StringBuilder();
    View view = new ClusterMockView(log1, log2, 12345);
    String s = "1.0 1.0\n2.0 2.0\n3.0 3.0\n4.0 4.0\n";
    Reader in = new StringReader(s);
    Controller controller = new DataFittingController(in, model, view);
    controller.process();
    Assert.assertEquals("1.0 1.0\n"
            + "2.0 2.0\n"
            + "3.0 3.0\n"
            + "4.0 4.0", log1.toString());
    Assert.assertEquals("1.0 1.0\n"
            + "2.0 2.0\n"
            + "3.0 3.0\n"
            + "4.0 4.0", log2.toString());
    Assert.assertEquals("12345", view.toString());
  }

  /**
   * This  method  tests  what happens if invalid data is read by the controller. Invalid data can
   * be data  with no space delimiter, or a different delimiter such as a comma.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalid1() {
    KMeansConfigurations config = KMeansConfigurations.getBuilder()
            .clusterCount(2).build();
    Model model = new TwoDPointClusterModelImpl(config);
    StringBuilder log1 = new StringBuilder();
    StringBuilder log2 = new StringBuilder();
    View view = new ClusterMockView(log1, log2, 12345);
    String s = "1.01.0\n2.0 2.0\n3.0 3.0\n4.0 4.0\n";
    Reader in = new StringReader(s);
    Controller controller = new DataFittingController(in, model, view);
    controller.process();
    Assert.assertEquals("1.0 1.0\n" +
            "2.0 2.0\n"
            + "3.0 3.0\n"
            + "4.0 4.0", log1.toString());
    Assert.assertEquals("1.0 1.0\n"
            + "2.0 2.0\n"
            + "3.0 3.0\n"
            + "4.0 4.0", log2.toString());
    Assert.assertEquals("12345", view.toString());
  }

  /**
   * This  method  tests  what happens if invalid data is read by the controller. Invalid data can
   * be data  with no space delimiter, or a different delimiter such as a comma.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalid2() {
    Model model = new TwoDRegressionModelImpl();
    StringBuilder log1 = new StringBuilder();
    StringBuilder log2 = new StringBuilder();
    View view = new RegressionMockView(log1, log2, 12345);
    String s = "1.0 ,1.0\n2.0 2.0\n3.0 3.0\n4.0 4.0\n";
    Reader in = new StringReader(s);
    Controller controller = new DataFittingController(in, model, view);
    controller.process();
    Assert.assertEquals("-0.7071067811865475 0.7071067811865476 -2.220446049250313E-16 ",
            log1.toString());
    Assert.assertEquals(s, log2.toString());
    Assert.assertEquals("12345", view.toString());
  }
}