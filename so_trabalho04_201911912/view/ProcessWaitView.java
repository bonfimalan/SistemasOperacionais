package view;

import controller.interfaces.MainControllerInterface;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.BCP;

public class ProcessWaitView extends VBox {
  private BCP process;
  private Button button;
  private MainControllerInterface controller;
  
  public ProcessWaitView(MainControllerInterface controller, BCP process, Label... labels) {
    super(new Label("Process " + process.getId()), new Label("Size " + process.getSize()));
    this.controller = controller;
    this.process = process;
    
    button = new Button("REMOVE");
    button.setOnAction(actionEvent -> remove());
    super.getChildren().addAll(labels);
    super.getChildren().add(button);
  }

  private void remove() {
    controller.removeProcessFromWait(process.getId());
  }

  public BCP getProcess() {
    return process;
  }
}
