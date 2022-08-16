package view.components;

import global.Variables;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ProcessAreaRunningUpperBar extends VBox {
  private Label stateLabel;
  private NumberSelector numberSelector; 

  public ProcessAreaRunningUpperBar(String state, int minValue, int maxValue, String unitLabel) {
    stateLabel = new Label(state);
    numberSelector = new NumberSelector(minValue, maxValue, unitLabel);
    numberSelector.getSlider().valueProperty().addListener(listener());
    super.getChildren().addAll(stateLabel, numberSelector);
  }

  private ChangeListener<? super Number> listener() {
    return (observable, oldValue, newValue) -> {
      Variables.timeSlice = newValue.intValue();
    };
  }
}
