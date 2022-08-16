import controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.MainView;

public class Principal extends Application {
  private static final String[] STYLES = {
    "/resources/styles/styles.css"
  };

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    MainView root = new MainView();
    MainController controller = new MainController(root);
    Scene scene = new Scene(root);

    scene.getStylesheets().addAll(STYLES);

    primaryStage.setOnCloseRequest(event -> {
      controller.onClose();
    });
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
