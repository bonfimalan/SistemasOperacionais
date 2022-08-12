package view.components;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ProcessArea extends ScrollPane {
  private VBox areaInfo;
  private VBox processArea;
  private VBox container;

  public ProcessArea() {
    areaInfo = new VBox();
    processArea = new VBox();
    container = new VBox();
    container.setPrefWidth(VBox.USE_COMPUTED_SIZE);

    container.getChildren().addAll(areaInfo, processArea);

    super.setContent(container);
  }

  public void setAreaInfo(VBox areaInfo) {
    this.areaInfo.getChildren().setAll(areaInfo.getChildren());
  }

  public void addProcess(ProcessInfoBox infoBox) {
    this.processArea.getChildren().add(infoBox);
  }

  public ProcessInfoBox removeProcessById(int id) {
    ProcessInfoBox processInfoBox;
    for(Node node : processArea.getChildren()) {
      if (node instanceof ProcessInfoBox) {
        processInfoBox = (ProcessInfoBox) node;
        if (processInfoBox.getIdentifier() == id) {
          processArea.getChildren().remove(processInfoBox); 
          return processInfoBox;
        }
      }
    }

    return null; // id not found
  }

  public ProcessInfoBox getProcessById(int id) {
    ProcessInfoBox processInfoBox;
    for(Node node : processArea.getChildren()) {
      if (node instanceof ProcessInfoBox) {
        processInfoBox = (ProcessInfoBox) node;
        if (processInfoBox.getIdentifier() == id) 
          return processInfoBox; 
      }
    }

    return null; // id not found
  }

  // used in the running, that will have only one process
  public ProcessInfoBox removeFirst () {
    return (ProcessInfoBox) processArea.getChildren().remove(0);
  }

  public void removeAllProcess() {
    this.processArea.getChildren().clear();
  }

  public VBox getAreaInfo() {
    return areaInfo;
  }
}
