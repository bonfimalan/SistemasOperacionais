package view.components;

import global.Variables;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class MainViewUpperMenu extends HBox{
  private ComboBox<String> comboBox;
  private Button applyButton;
  private Button addProcess;
  private Label timer;

  public MainViewUpperMenu() {
    comboBox = new ComboBox<>();
    applyButton = new Button("APPLY");
    addProcess = new Button("ADD PROCESS");
    addProcess.setId("add-process-button");

    // creating timer Label
    timer = new Label("" + Variables.timer);

    // adding algs
    comboBox.getItems().addAll(Variables.ALG_NAMES);
    comboBox.getSelectionModel().select(0);

    //super.setPrefHeight(MainView.MENU_HEIGHT);
    super.setAlignment(Pos.CENTER_LEFT);
    super.setSpacing(5.0);
    super.getChildren().addAll(comboBox, applyButton, addProcess, new Label("Time: "), timer);
  }

  public void setAddProcessEvent(EventHandler<ActionEvent> eventHandler) {
    addProcess.setOnAction(eventHandler);
  }

  public void setAlgApplyEvent(EventHandler<ActionEvent> eventHandler) {
    applyButton.setOnAction(eventHandler);
  }

  public ComboBox<String> getComboBox() {
    return comboBox;
  }

  public void updateTimer() {
    this.timer.setText("" + Variables.timer);
  }
}
