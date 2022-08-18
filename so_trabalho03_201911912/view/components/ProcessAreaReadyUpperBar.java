package view.components;

import global.Variables;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ProcessAreaReadyUpperBar extends ProcessAreaIntervalUpperBar {
  private Button applyButton;

  // default values in second
  public static final int DEFAULT_MIN = 2;
  public static final int DEFAULT_MAX = 20;
  

  public ProcessAreaReadyUpperBar (String state) {
    super(state);

    // the interval starts with the default value
    Variables.readyMinSeconds = DEFAULT_MIN;
    Variables.readyMaxSeconds = DEFAULT_MAX;

    // configs to the text field
    super.getMin().setText(String.valueOf(DEFAULT_MIN));
    super.getMax().setText(String.valueOf(DEFAULT_MAX));

    applyButton = new Button("APPLY");
    
    applyButton.setOnAction(buttonConfiguration());

    super.getChildren().addAll(applyButton);
  }

  private EventHandler<ActionEvent> buttonConfiguration() {
    return event -> {
      int minTemp = Variables.readyMinSeconds;
      int maxTemp = Variables.readyMaxSeconds;
      try {
        // get the values in the text field and convert to int
        Variables.readyMinSeconds = Integer.valueOf(super.getMin().getText());
        Variables.readyMaxSeconds = Integer.valueOf(super.getMax().getText());

        // reset conditions (GAMBIARRA)
        if(Variables.readyMinSeconds > Variables.readyMaxSeconds) throw new NumberFormatException();
      }
      catch(NumberFormatException e) {
        // if the value in the text field can't be passed to int
        // it will be reseted
        Variables.readyMinSeconds = minTemp;
        Variables.readyMaxSeconds = maxTemp;

        super.getMin().setText(String.valueOf(minTemp));
        super.getMax().setText(String.valueOf(maxTemp));
      }
    };
  }
}