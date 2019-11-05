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

public class Tester {

  public static void main(String[] args) throws FileNotFoundException {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    BufferedReader bufferedReader;
    File file;
    Model model;
    View view;
    Controller c;
    if (n == 0) {
      String path = sc.next();
      file = new File(path);
      KMeansConfigurations config = KMeansConfigurations.getBuilder()
              .clusterCount(3).iterations(100).ransac(10).threshold(0.01).distanceCalculator(Point::getEuclideanDistance)
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
