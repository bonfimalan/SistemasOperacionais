/********************************************************************
* Author: Alan Bonfim Santos
* Registration: 201911912
* Initial date: 16/09/22 19:02
* Last update: 18/09/22 00:06
* Name: PartitionConfigView.java
* Function:
*******************************************************************/
package view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class PartitionConfigView extends HBox{
  private Label label;
  private TextField textField;
  //private Button button;

  public PartitionConfigView(int partitionNumber) {

    label = new Label("Partition " + partitionNumber + " size:");
    textField = new TextField("0");
    textField.setPrefWidth(50);
    //button = new Button("REMOVE");

    super.getChildren().addAll(label, textField);
  }

  public int getPartitionSize() {
    return Integer.valueOf(textField.getText());
  }

  public void disablePartiotionTextArea() {
    textField.setDisable(true);
  }
}
