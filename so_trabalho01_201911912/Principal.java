/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/07/2021 11:00
 * Last update: 
 * Name: Principal.java
 * Function: the main class that loads the first Stage
 *******************************************************************/

import controller.WorkAreaController;
import global.Controllers;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import view.desktop.Desktop;

public class Principal extends Application {
  private static String[] styles = {
    "/resources/styles/lower-bar.css",
    "/resources/styles/work-area-icon.css"
  };
  public Desktop desktop = new Desktop(800, 600);
  
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // gets the screen size
    Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    // create the controller for the WorkArea view
    Controllers.workAreaController = new WorkAreaController(
      desktop.getWorkArea(), 
      (int) screenBounds.getMaxX(), 
      (int) screenBounds.getMaxY());

    Scene scene = new Scene(desktop);
    
    scene.getStylesheets().addAll(styles);

    primaryStage.setMinWidth(500);
    primaryStage.setMinHeight(350);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}