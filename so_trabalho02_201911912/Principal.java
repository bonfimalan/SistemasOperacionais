import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.components.NumberSelector;

public class Principal extends Application {
  private static final String[] STYLES = {
    "/resources/styles/number-selector.css"
  };

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    VBox parent = new VBox();
    parent.getChildren().addAll(new NumberSelector(2, 15));
    Scene scene = new Scene(parent);

    scene.getStylesheets().addAll(STYLES);

    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
