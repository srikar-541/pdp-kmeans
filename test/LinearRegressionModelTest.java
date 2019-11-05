import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

import abstraction.DataFittingController;
import mvc.Controller;
import mvc.Model;
import mvc.View;
import regression.TwoDRegressionModelImpl;
import regression.TwoDRegressionViewImpl;

public class LinearRegressionModelTest {

  Controller controller;
  @Test
  public void testOne() {
    Model model = new TwoDRegressionModelImpl();
    View view = new TwoDRegressionViewImpl();
    String s = "2.0 7.0\n3.0 9.0\n12.0 27.0\n9.0 21.0\n";
    Reader in = new StringReader(s);
    controller = new DataFittingController(in, model, view);
    controller.process();
  }

  private String readData(File file) throws FileNotFoundException {
    Scanner sc = new Scanner(file);
    StringBuilder sb = new StringBuilder();
    while(sc.hasNextLine()) {
      String[] point = sc.nextLine().split(" ");
      if (point.length != 2) {
        throw new IllegalArgumentException("Invalid data");
      }
      String xCoordinate = Double.parseDouble(point[0])+"";
      String yCoordinate = Double.parseDouble(point[1])+"";
      sb.append(xCoordinate+" "+yCoordinate+"\n");
    }
    return sb.toString();
  }
}
