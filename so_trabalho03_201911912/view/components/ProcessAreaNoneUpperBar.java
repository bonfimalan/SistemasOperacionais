package view.components;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ProcessAreaNoneUpperBar extends VBox {
  private Label stateLabel;

  public ProcessAreaNoneUpperBar(String state) {
    stateLabel = new Label(state);
    super.getChildren().addAll(stateLabel);
  }
}
