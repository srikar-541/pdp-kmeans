package mvc;

/**
 * This interface is a representation of a controller which can be used across different
 * fitting models. Controller for any data fitting model remains same as it offers
 * same functionality of making calls to the model and view and transferring
 * data back and forth.
 */
public interface Controller {

  /**
   * This is the entry point of the data fitting application. It makes calls to the
   * model to process the given input.
   * The data it receives from the model is passed to the view for displaying.
   */
  void process();
}
