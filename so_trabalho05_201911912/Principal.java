/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 17/08/2022 19:33
 * Last update: 18/08/2022 00:28
 * Name: Principal.java
 * Function: the main class that loads the fxml to the stage and shows it
 *******************************************************************/
import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application{
  private static final String PATH_TO_STYLES = "/resources/styles/";
  private static final String[] STYLES = {
    PATH_TO_STYLES + "styles.css"
  };

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main-view.fxml"));
    MainController controller = new MainController();
    loader.setController(controller);
    Parent root = loader.load();

    Scene scene = new Scene(root);
    scene.getStylesheets().addAll(STYLES);

    primaryStage.setScene(scene);
    primaryStage.setTitle("Memory manager");
    primaryStage.show();
  }
}
