package view.components;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProcessAreaIntervalUpperBar extends VBox {
  private Label stateLabel;
  private TextField min;
  private TextField max;
  private HBox intervalContainer;

  public static final double TEXT_FIELD_WIDTH = 60.0;

  public ProcessAreaIntervalUpperBar(String state) {
    min = new TextField();
    min.setPrefWidth(TEXT_FIELD_WIDTH);
    max = new TextField();
    max.setPrefWidth(TEXT_FIELD_WIDTH);

    intervalContainer = new HBox();
    intervalContainer.getChildren().addAll(
      new Label("Interval from "),
      min,
      new Label(" to "),
      max
    );

    stateLabel = new Label(state);
    super.getChildren().addAll(stateLabel, intervalContainer);
  }
}
