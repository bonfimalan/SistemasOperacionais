package view;

import global.Variables;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.BCP;
import view.components.MainViewDownMenu;
import view.components.MainViewUpperMenu;
import view.components.ProcessArea;
import view.components.ProcessAreaBlockedUpperBar;
import view.components.ProcessAreaNoneUpperBar;
import view.components.ProcessAreaReadyUpperBar;
import view.components.ProcessAreaRunningUpperBar;
import view.components.ProcessInfoBox;

public class MainView extends VBox {
  private MainViewUpperMenu upperMenu;
  private MainViewDownMenu downMenu;
  private ProcessArea ready;
  private ProcessArea running;
  private ProcessArea blocked;
  private ProcessArea done;
  private HBox processAreaContainer;
  private VBox runningBlockedProcessAreaContainer;

  public static final double MENU_HEIGHT = 50.0;

  public MainView() {
    super.setPrefSize(800, 600);
    // process area
    ready = new ProcessArea();
    ready.setAreaInfo(new ProcessAreaReadyUpperBar("READY"));

    running = new ProcessArea();
    running.setAreaInfo(
        new ProcessAreaRunningUpperBar("RUNNING", Variables.TIME_SLICE_MIN_VALUE, 10, Variables.SECOND));

    blocked = new ProcessArea();
    blocked.setAreaInfo(new ProcessAreaBlockedUpperBar("BLOCKED"));

    done = new ProcessArea();
    done.setAreaInfo(new ProcessAreaNoneUpperBar("DONE"));

    // creating menus
    upperMenu = new MainViewUpperMenu();
    downMenu = new MainViewDownMenu();

    // uppermenu configurations

    // creating the containers
    processAreaContainer = new HBox();
    runningBlockedProcessAreaContainer = new VBox();

    // config grow mode to the process areas
    // Hgrow to the ones that are in the HBox
    HBox.setHgrow(ready, Priority.ALWAYS);
    HBox.setHgrow(done, Priority.ALWAYS);
    HBox.setHgrow(runningBlockedProcessAreaContainer, Priority.ALWAYS);
    // Vgrow to the ones that are in the VBox
    VBox.setVgrow(running, Priority.ALWAYS);
    VBox.setVgrow(blocked, Priority.ALWAYS);
    VBox.setVgrow(processAreaContainer, Priority.ALWAYS);

    // config height to the menus
    downMenu.setPrefHeight(MENU_HEIGHT);
    runningBlockedProcessAreaContainer.getChildren().addAll(running, blocked);
    processAreaContainer.getChildren().addAll(ready, runningBlockedProcessAreaContainer, done);

    super.getChildren().addAll(upperMenu, processAreaContainer, downMenu);
  }

  public void resetGui() {
    ready.removeAllProcess();
    running.removeAllProcess();
    blocked.removeAllProcess();
    done.removeAllProcess();
  }

  public void setAddProcessActionEvent(EventHandler<ActionEvent> event) {
    upperMenu.setAddProcessEvent(event);
  }

  public void setAlgApplyEvent(EventHandler<ActionEvent> event) {
    upperMenu.setAlgApplyEvent(event);
  }

  public ComboBox<String> getComboBox() {
    return upperMenu.getComboBox();
  }

  public void runProcess(int time) {
    running.getFirst().processRunCountDown(time);
  }

  public void countDownBlocked(int id, int blockedTime) {
    blocked.getProcessById(id).blockCountDown(blockedTime);
  }

  public void addReadyProcess(ProcessInfoBox infoBox) {
    ready.addProcess(infoBox);
  }

  public void backFromRunningToReady() {
    ProcessInfoBox infoBox = running.removeFirst();
    if (infoBox == null)
      return;

    ready.addProcess(infoBox);
  }

  public void backFromBlockedToReady(int id) {
    ProcessInfoBox infoBox = blocked.removeProcessById(id);
    if (infoBox == null)
      return;

    ready.addProcess(infoBox);
  }

  public void addRunningProcess(int id) {
    ProcessInfoBox infoBox = ready.removeProcessById(id);
    if (infoBox == null)
      return;

    running.addProcess(infoBox);
  }

  public void addBlockedProcess(BCP process) {
    ProcessInfoBox infoBox = running.removeFirst();
    if (infoBox == null)
      return;

    infoBox.block(process.getTimeBlocked());
    blocked.addProcess(infoBox);
  }

  public void addDoneProcess() {
    ProcessInfoBox infoBox = running.removeFirst();
    if (infoBox == null)
      return;

    done.addProcess(infoBox);
  }

  public void updateTimer() {
    upperMenu.updateTimer();
  }
}
