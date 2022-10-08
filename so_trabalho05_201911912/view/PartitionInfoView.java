/********************************************************************
* Author: Alan Bonfim Santos
* Registration: 201911912
* Initial date: 16/09/22 19:24
* Last update: 16/09/22 19:28
* Name: PartitionInfoView.java
* Function:
*******************************************************************/
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
