package view.components;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ProcessInfoBox extends VBox {
  private Label nameLabel;
  private Label timeLabel;
  private Label priorityLabel;
  private Label deadLineLabel;
  private Label blockedTimeLabel = new Label();
  // used to remove or chose a process to show in the view
  // this is the link between a process and it info box
  private int identifier; 

  public ProcessInfoBox (int id, String name, int time) {
    this.identifier = id;
    nameLabel = new Label(name);
    timeLabel = new Label("Time " + time + "s");
    addChildren(nameLabel, timeLabel);
  }

  public ProcessInfoBox (int id, String name, int time, int priority) {
    this.identifier = id;
    nameLabel = new Label(name);
    timeLabel = new Label("Time " + time + "s");
    priorityLabel = new Label("Priority " + priority);
    addChildren(nameLabel, timeLabel, priorityLabel);
  }

  // create a process box with the time created and the deadline
  public ProcessInfoBox (int id, String name, int time, int timeCreadted, int deadLine) {
    this.identifier = id;
    nameLabel = new Label(name);
    timeLabel = new Label("Time " + time + "s");
    // there's no priority in the deadline alg
    // priorityLabel = new Label("Priority " + priority + "s");
    deadLineLabel = new Label("Deadline " + deadLine + "s");
    addChildren(nameLabel, timeLabel, deadLineLabel);
  }

  public void processRunCountDown(int time) {
    timeLabel.setText("Time " + time + "s");
  }

  public void block(int blockedTime) {
    blockedTimeLabel.setText("Blocked time " + blockedTime + "s");
    addChildren(blockedTimeLabel);
  }

  public void blockCountDown(int blockedTime) {
    if (blockedTime == 0) {
      removeChildren(blockedTimeLabel);
      return;
    }
    blockedTimeLabel.setText("Blocked time " + blockedTime + "s");
  }

  private void removeChildren(Node... children) {
    super.getChildren().removeAll(children);
  }

  private void addChildren(Node... children) {
    super.getChildren().addAll(children);
  }

  public int getIdentifier() {
    return identifier;
  }

  public void setIdentifier(int identifier) {
    this.identifier = identifier;
  }
}
