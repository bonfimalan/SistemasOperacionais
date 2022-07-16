/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/07/2021 11:00
 * Last update: 
 * Name: Principal.java
 * Function: the main class that loads the first Stage
 *******************************************************************/

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.desktop.Desktop;

public class Principal extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = new Desktop(800, 600);
    Scene scene = new Scene(root);
    
    primaryStage.setMinWidth(800);
    primaryStage.setMinHeight(600);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}