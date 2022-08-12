package view.components;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ProcessAreaSliderUpperBar extends VBox {
  private Label stateLabel;
  private NumberSelector numberSelector; 

  public ProcessAreaSliderUpperBar(String state, int minValue, int maxValue) {
    stateLabel = new Label(state);
    numberSelector = new NumberSelector(minValue, maxValue);
    super.getChildren().addAll(stateLabel, numberSelector);
  }
}
