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
