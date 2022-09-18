package view;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class PartitionInfoView extends HBox{
  private Label partitionNumber;
  private Label partitonFreeSpace;

  public PartitionInfoView(int number, int freeSpace) {
    super.setSpacing(2);

    this.partitionNumber = new Label("Number: " + number);
    this.partitonFreeSpace = new Label("| Free space: " + freeSpace);

    super.getChildren().addAll(this.partitionNumber, this.partitonFreeSpace);
  }

  public void setFreeSpace(int freeSpace) {
    this.partitonFreeSpace = new Label("| Free space: " + freeSpace);
  }
}
