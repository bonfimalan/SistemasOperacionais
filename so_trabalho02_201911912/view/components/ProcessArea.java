package view.components;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProcessArea extends ScrollPane {
  private HBox areaInfo;
  private VBox processArea;
  private VBox container;

  public ProcessArea() {
    areaInfo = new HBox();
    processArea = new VBox();
    container = new VBox();
    container.setPrefWidth(VBox.USE_COMPUTED_SIZE);

    container.getChildren().addAll(areaInfo, processArea);

    super.setContent(container);
  }
}
