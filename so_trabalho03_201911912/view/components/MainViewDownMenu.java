package view.components;

import global.Variables;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class MainViewDownMenu extends HBox {
  private Label speedLabel;
  private NumberSelector speedSelector;
  public MainViewDownMenu() {
    speedLabel = new Label("Speed  ");
    speedSelector = new NumberSelector(
      Variables.MIN_SPEED,
      Variables.MAX_SPEED, 
      Variables.MULTIPLIER);
    speedSelector.getSlider().valueProperty().addListener(listener());
    
    //super.setPrefHeight(MainView.MENU_HEIGHT);
    super.getChildren().addAll(speedLabel, speedSelector);
  }

  private ChangeListener<? super Number> listener() {
    return (observable, oldValue, newValue) -> {
      Variables.speed = newValue.intValue();
    };
  }
}
