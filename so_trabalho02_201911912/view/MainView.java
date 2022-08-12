package view;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import view.components.ProcessArea;
import view.components.ProcessAreaIntervalUpperBar;
import view.components.ProcessAreaNoneUpperBar;
import view.components.ProcessAreaSliderUpperBar;
import view.components.ProcessInfoBox;

public class MainView extends VBox {
  private HBox upperMenu;
  private HBox downMenu;
  private ProcessArea ready;
  private ProcessArea running;
  private ProcessArea blocked;
  private ProcessArea done;
  private HBox processAreaContainer;
  private VBox runningBlockedProcessAreaContainer;

  public static final double MENU_HEIGht = 50.0;

  public MainView() {
    super.setPrefSize(500, 400);
    // process area
    ready = new ProcessArea();
    ready.setAreaInfo(new ProcessAreaIntervalUpperBar("READY"));

    running = new ProcessArea();
    running.setAreaInfo(new ProcessAreaSliderUpperBar("RUNNING", 2, 10));

    blocked = new ProcessArea();
    blocked.setAreaInfo(new ProcessAreaIntervalUpperBar("BLOCKED"));

    done = new ProcessArea();
    done.setAreaInfo(new ProcessAreaNoneUpperBar("DONE"));

    // creating menus
    upperMenu = new HBox();
    downMenu = new HBox();

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
    upperMenu.setPrefHeight(MENU_HEIGht);
    downMenu.setPrefHeight(MENU_HEIGht);
    runningBlockedProcessAreaContainer.getChildren().addAll(running, blocked);
    processAreaContainer.getChildren().addAll(ready, runningBlockedProcessAreaContainer, done);
    
    super.getChildren().addAll(upperMenu, processAreaContainer, downMenu);
  }

  public void addReadyProcess(ProcessInfoBox infoBox) {
    ready.addProcess(infoBox);
  }

  public void addRunningProcess(int id) {
    ProcessInfoBox infoBox = ready.removeProcessById(id);
    if(infoBox == null) return;

    running.addProcess(infoBox);
  }

  public void addBlockeProcces() {
    ProcessInfoBox infoBox = running.removeFirst();
    if(infoBox == null) return;

    blocked.addProcess(infoBox);
  }

  public void addDoneProcess() {
    ProcessInfoBox infoBox = running.removeFirst();
    if(infoBox == null) return;

    done.addProcess(infoBox);
  }
}
