package view.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NumberSelector extends HBox {
  private Label numberLabel;
  private Button up;
  private Button down;
  private VBox buttonContainer;

  private int minValue;
  private int maxValue;
  private int value;

  public static final double HEIGHT = 40;

  public NumberSelector(int minValue, int maxValue) {
    this.minValue = minValue;
    this.maxValue = maxValue;
    this.value = minValue;

    numberSelectorDefault();
  }

  private void numberSelectorDefault() {
    // initializing view Nodes
    up = new Button();
    up.setId("up-button"); // css id
    down = new Button();
    down.setId("down-button"); // css id
    buttonContainer = new VBox(up, down);
    numberLabel = new Label (String.valueOf(value));

    // define sizes
    numberLabel.setPrefHeight(HEIGHT);
    numberLabel.setPrefWidth(50);
    up.setPrefHeight(HEIGHT/ 2);
    down.setPrefHeight(HEIGHT/ 2);
    up.setMinHeight(HEIGHT/ 2);
    down.setMinHeight(HEIGHT/ 2);

    // adding events to the buttons
    up.setOnAction(upButtonActionEvent());
    down.setOnAction(downButtonActionEvent());

    super.getChildren(). addAll(numberLabel, buttonContainer);
  }

  public int getValue() {
    return this.value;
  }

  private EventHandler<ActionEvent> upButtonActionEvent() {
    return event -> {
      if (value < maxValue) {
        value++;
        numberLabel.setText(String.valueOf(value) + "s");
      }
    };
  }

  private EventHandler<ActionEvent> downButtonActionEvent() {
    return event -> {
      if (value > minValue) {
        value--;
        numberLabel.setText(String.valueOf(value) + "s");
      }
    };
  }
}
