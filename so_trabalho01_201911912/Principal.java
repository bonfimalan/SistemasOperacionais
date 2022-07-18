/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/07/2021 11:00
 * Last update: 
 * Name: Principal.java
 * Function: the main class that loads the first Stage
 *******************************************************************/

import apps.manelkkk.Manel;
import controller.DesktopController;
import controller.LowerBarController;
import controller.WorkAreaController;
import global.Controllers;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import view.desktop.Desktop;

public class Principal extends Application {
  public final static String PATH_TO_STYLES = "/resources/styles/";
  private static String[] styles = {
    PATH_TO_STYLES + "lower-bar.css",
    PATH_TO_STYLES + "work-area.css",
    PATH_TO_STYLES + "work-area-icon.css",
    PATH_TO_STYLES + "internal-stage.css"
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
    
    // creating the lower bar controller, that is a global an unique contoller
    Controllers.lowerBarController = new LowerBarController(desktop.getLowerBar());

    // creating th desktop controller
    Controllers.desktopController = new DesktopController();

    Scene scene = new Scene(desktop);
    
    // adding the style sheets
    scene.getStylesheets().addAll(styles);
    
    Manel manel = new Manel();
    Controllers.desktopController.addApp(manel);

    //ConcorrenteTrabalho8 trabalho8 = new ConcorrenteTrabalho8();
    //Controllers.desktopController.addApp(trabalho8);
    
    // adding apps to test
    //ApplicationInfo info = new ApplicationInfo("/resources/images/manel.jpeg", "MAMANEL KKKKKK");

    //Controllers.desktopController.addApp(new ApplicationInfo("/resources/images/manel.jpeg", "MAMANEL KKKKKK"));
    //Controllers.desktopController.addApp(new ApplicationInfo("/resources/images/manel.jpeg", "MAMANEL KKKKKK"));


    primaryStage.setMinWidth(500);
    primaryStage.setMinHeight(350);
    primaryStage.setScene(scene);
    primaryStage.show();

  }
}