package controller;

import java.util.ArrayList;
import java.util.List;

import algorithms.FIFO;
import algorithms.Priority;
import algorithms.SJF;
import global.Variables;
import interfaces.MainControllerInterface;
import interfaces.ScaleAlgorithm;
import javafx.scene.control.ComboBox;
import model.BCP;
import thread.BlockedLoop;
import thread.RunLoop;
import util.Util;
import view.MainView;

public class MainController implements MainControllerInterface {
  private RunLoop loop;
  private BlockedLoop blockedLoop;
  private MainView mainView;
  private BCP runningProcess;
  private ScaleAlgorithm algorithm;
  private List<BCP> blockedProcessList;
  private ComboBox<String> algComboBox;
  // this can be between 1 and 3
  private char selectedAlg = '1'; // FIFO is default

  public MainController(MainView mainView) {
    algorithm = new FIFO();
    this.loop = new RunLoop(algorithm, this);
    this.blockedLoop = new BlockedLoop(this);
    this.mainView = mainView;
    this.blockedProcessList = new ArrayList<>();
    //this.blockedLoops = new ArrayList<>();
    this.algComboBox = mainView.getComboBox();

    configAddProcessActionEvent();
    configApplyAlgActionEvent();

    loop.start();
    blockedLoop.start();
  }

  public void onClose() {
    loop.interrupt();
    blockedLoop.interrupt();
  }

  private void configAddProcessActionEvent() {
    mainView.setAddProcessActionEvent(event -> {
      BCP process = Util.generateBCP();
      algorithm.addProcess(process);
      switch (selectedAlg) {
        case '1': // FIFO
          mainView.addReadyProcess(Util.generateProcessInfoBox(process));
          break;
        case '2': // Priority
          mainView.addReadyProcess(Util.generateProcessInfoBoxWithPriority(process));
          break;
        case '3': // SJF
          mainView.addReadyProcess(Util.generateProcessInfoBox(process));
          break;
      }
    });
  }

  private void configApplyAlgActionEvent() {
    mainView.setAlgApplyEvent(event -> {
      selectedAlg = algComboBox.getSelectionModel().getSelectedItem().charAt(0);
      // reseting
      reset();

      switch (selectedAlg) {
        case '1': // FIFO
          algorithm = new FIFO();
          this.loop = new RunLoop(algorithm, this);
          this.loop.start();
          break;
        case '2': // Priority
          algorithm = new Priority();
          this.loop = new RunLoop(algorithm, this);
          this.loop.start();
          break;
        case '3': // SJF
          algorithm = new SJF();
          this.loop = new RunLoop(algorithm, this);
          this.loop.start();
          break;
      }
    });
  }

  @Override
  public void reset() {
    mainView.resetGui();
    loop.interrupt();
    blockedProcessList.clear();
    BCP.resetCount();
  }

  @Override
  public void setRunningProcess(BCP process) {
    this.runningProcess = process;
  }

  @Override
  public int getRunningProcessTime() {
    return runningProcess.getTime();
  }

  @Override
  public void runProcess() {
    runningProcess.run();
    try {
      mainView.runProcess(runningProcess.getTime());
    } catch (Exception e) { }
  }

  @Override
  public void runBlockedTime(BCP blocked) {
    blocked.runBlocked();
    mainView.countDownBlocked(blocked.getId(), blocked.getTimeBlocked());
    if(blocked.getTimeBlocked() == 0){
      moveFromBlockedToReady(blocked.getId());
      blockedProcessList.remove(blocked);
    }
  }

  @Override
  public void moveToRunning(int id) {
    mainView.addRunningProcess(id);
  }

  @Override
  public void moveFromRunningToReady() {
    algorithm.addProcess(runningProcess);
    mainView.backFromRunningToReady();
  }

  @Override
  public void moveFromBlockedToReady(int id) {
    for (BCP bcp : blockedProcessList) {
      if (bcp.getId() == id) {
        algorithm.addProcess(bcp);
        break;
      }
    }
    mainView.backFromBlockedToReady(id);
  }

  @Override
  public void moveToDone() {
    mainView.addDoneProcess();
  }

  @Override
  public void moveToBlocked() {
    blockProcess();
    mainView.addBlockedProcess(runningProcess);
  }

  @Override
  public List<BCP> getBlockedProcessList() {
    return this.blockedProcessList;
  }

  @Override
  public void blockProcess() {
    int timeBlocked = Util.generateRandomNumber(
        Variables.blockedMinSeconds,
        Variables.blockedMaxSeconds);
    BCP blockedProcess = runningProcess;

    blockedProcess.setTimeBlocked(timeBlocked);
    blockedProcessList.add(blockedProcess);
    // starts the thread that will run the block time
    //BlockedLoop blockedLoop = new BlockedLoop(this, blockedProcess);
    //blockedLoops.add(blockedLoop);
    //blockedLoop.start();
  }
}
