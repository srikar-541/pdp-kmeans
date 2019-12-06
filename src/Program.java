import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import abstraction.DataFittingController;
import kmeans.KMeansConfigurations;
import kmeans.TwoDClusterViewImpl;
import kmeans.TwoDPointClusterModelImpl;
import mvc.Controller;
import mvc.Model;
import mvc.View;
import point.Point;
import regression.TwoDRegressionModelImpl;
import regression.TwoDRegressionViewImpl;

/**
 * This is the runner program for our MVC model. Takes input "0" or "1" followed by filename 0 for
 * KMeans Clustering 1 for Linear Regression. The resulting images are written in directory
 * program_output.
 */
public class Program {

  /**
   * Main method for running our program.
   *
   * @param args String input as described above.
   * @throws FileNotFoundException if file specified by string path is not found.
   */
  public static void main(String[] args) throws FileNotFoundException {
    System.out.println("Enter 0 for KMeans clustering and 1 for "
            + "Linear Regression followed by data source path");
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    BufferedReader bufferedReader;
    File file = new File(sc.next());
    Model model;
    View view;
    Controller c;
    if (n == 0) {
      KMeansConfigurations config = KMeansConfigurations.getBuilder()
              .clusterCount(3).iterations(100).ransac(10)
              .threshold(0.01).distanceCalculator(Point::getEuclideanDistance)
              .build();
      model = new TwoDPointClusterModelImpl(config);
      view = new TwoDClusterViewImpl();
    } else {
      model = new TwoDRegressionModelImpl();
      view = new TwoDRegressionViewImpl();
    }

    bufferedReader = new BufferedReader(new FileReader(file));
    c = new DataFittingController(bufferedReader, model, view);
    c.process();
  }
}
