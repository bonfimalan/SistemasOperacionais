package view.components;

import global.Variables;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ProcessAreaBlockedUpperBar extends ProcessAreaIntervalUpperBar {
  private HBox percentageContainer;
  private Label percentageLabel;
  private NumberSelector percentageSelector;
  private Button applyButton;

  // default values in seconds
  public static final int DEFAULT_MIN = 2;
  public static final int DEFAULT_MAX = 10;
  

  public ProcessAreaBlockedUpperBar (String state) {
    super(state);

    // the interval starts with the default value
    Variables.blockedMinSeconds = DEFAULT_MIN;
    Variables.blockedMaxSeconds = DEFAULT_MAX;

    // configs to the text field
    super.getMin().setText(String.valueOf(DEFAULT_MIN));
    super.getMax().setText(String.valueOf(DEFAULT_MAX));

    // apply button configs
    applyButton = new Button("APPLY");
    applyButton.setOnAction(buttonConfiguration());

    // percentage configs
    percentageLabel = new Label("Chance to block");
    percentageSelector = new NumberSelector(
      Variables.PERCENTAGE_BLOCK_MIN_VALUE, 
      Variables.PERCENTAGE_BLOCK_MAX_VALUE, 
      Variables.PERCENTAGE);
    percentageSelector.getSlider().valueProperty().addListener(listener());
    percentageContainer = new HBox(percentageLabel, percentageSelector);

    super.getChildren().addAll(applyButton, percentageContainer);
  }

  private ChangeListener<? super Number> listener() {
    return (observable, oldValue, newValue) -> {
      Variables.blockPercentage = newValue.intValue();
    };
  }

  private EventHandler<ActionEvent> buttonConfiguration() {
    return event -> {
      int minTemp = Variables.blockedMinSeconds;
      int maxTemp = Variables.blockedMaxSeconds;
      try {
        // get the values in the text field and convert to int
        Variables.blockedMinSeconds = Integer.valueOf(super.getMin().getText());
        Variables.blockedMaxSeconds = Integer.valueOf(super.getMax().getText());

        // reset conditions (GAMBIARRA)
        if(Variables.blockedMinSeconds > Variables.blockedMaxSeconds) throw new NumberFormatException();
      }
      catch(NumberFormatException e) {
        // if the value in the text field can't be passed to int
        // it will be reseted
        Variables.blockedMinSeconds = minTemp;
        Variables.blockedMaxSeconds = maxTemp;

        super.getMin().setText(String.valueOf(minTemp));
        super.getMax().setText(String.valueOf(maxTemp));
      }
    };
  }
}