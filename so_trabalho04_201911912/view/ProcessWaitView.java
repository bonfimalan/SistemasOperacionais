/********************************************************************
* Author: Alan Bonfim Santos
* Registration: 201911912
* Initial date: 16/09/22 18:45
* Last update: 17/09/22 19:41
* Name: ProcessWaitView.java
* Function:
*******************************************************************/
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
