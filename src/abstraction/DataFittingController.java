package abstraction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kmeans.KMeansConfigurations;
import kmeans.TwoDClusterViewImpl;
import kmeans.TwoDPointClusterModelImpl;
import mvc.Controller;
import mvc.Model;
import mvc.View;
import point.Point;
import point.TwoDPoint;
import regression.TwoDRegressionModelImpl;
import regression.TwoDRegressionViewImpl;

/**
 * This class is an abstraction of the TwoDimension controllers.
 * It offers methods like reading data from a data source which will be passed
 * to the constructor.
 */
public class DataFittingController implements Controller {

  private Readable in;
  private Model model;
  private View view;

  /**
   * This constructor is an abstraction of the child class TwoDControllers.
   * It takes in Model object, View object, data source from which data has to be fed
   * as inputs and creates an object.
   * @param     in Data source from which data is to be read.
   * @param     model Data fitting Model object which has to be fit to the data given.
   * @param     view  View object which has to be called after data fitting is done.
   */
  public DataFittingController(Readable in, Model model, View view) {
    this.in = in;
    this.model = model;
    this.view = view;
  }

  /**
   * This method processes the data and plotting of the data by making calls
   * to the model and the view by transferring data received from the model to the view.
   */
  @Override
  public void process() {
    List<Point> points = this.readData();
    view.setPlot(points);
    view.plot(model.fit(points));
  }

  /**
   * This function parses points from a data source which can be a file, console etc.
   * @return    It returns a list of points after parsing the data source.
   */
  protected List<Point> readData() {
    List<Point> points = new ArrayList<>();
    Scanner sc = new Scanner(this.in);
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      String[] tokens = line.split(" ");
      if (tokens.length != 2) {
        throw new IllegalArgumentException("Ony Two D data!!");
      }
      try {
        points.add(new TwoDPoint(Double.parseDouble(tokens[0]), Double.parseDouble(tokens[1])));
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
        throw e;
      }
    }
    return points;
  }

  public static void main(String[] args) throws FileNotFoundException {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    BufferedReader bufferedReader;
    File file;
    Model model;
    View view;
    Controller c;
    if (n == 0) {
      file = new File("data/clusterdata-2.txt");
      KMeansConfigurations config = KMeansConfigurations.getBuilder()
              .clusterCount(2).iterations(10).ransac(10).threshold(0.01).distanceCalculator(Point::getEuclideanDistance)
              .build();
      model = new TwoDPointClusterModelImpl(config);
      view = new TwoDClusterViewImpl();
    }
    else {
      file = new File("data/linedata-1.txt");
      model = new TwoDRegressionModelImpl();
      view = new TwoDRegressionViewImpl();
    }

    bufferedReader = new BufferedReader(new FileReader(file));
    c = new DataFittingController(bufferedReader, model, view);
    c.process();
  }
}
