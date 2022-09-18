package view;

import algorithms.util.MemoryUtil;
import controller.interfaces.MainControllerInterface;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.BCP;

public class InMemoryProcessView extends HBox {
  private Label processName;
  private Label processSize;
  private Button remove;
  private BCP bcp;

  private MainControllerInterface controller;

  public InMemoryProcessView(MainControllerInterface controller, BCP bcp) {
    super.setSpacing(2);

    super.setMinHeight(bcp.getSize() * MemoryUtil.SIZE_MULTIPLYER);

    this.processName = new Label("Process " + bcp.getId());
    this.processSize = new Label("size: " + bcp.getSize());
    this.remove = new Button("X");
    // adding remove event to the button
    this.remove.setOnAction(actionEvent -> remove());

    this.controller = controller;
    this.bcp = bcp;

    super.getChildren().addAll(this.processName, this.processSize, remove);
  }

  private void remove() {
    controller.removeProcess(bcp.getId());
  }

  public BCP getProcess() {
    return this.bcp;
  }
}
