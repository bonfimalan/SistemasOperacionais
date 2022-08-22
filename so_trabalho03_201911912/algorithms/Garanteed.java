package algorithms;

import java.util.ArrayList;
import java.util.List;

import global.Variables;
import interfaces.MainControllerInterface;
import interfaces.ScaleAlgorithm;
import javafx.application.Platform;
import model.BCP;
import util.Util;
import view.components.ProcessInfoBox;

public class Garanteed implements ScaleAlgorithm{
  private List<BCP> readyProcessList;
  private List<BCP> deadProcessList;
  private int totalProcessTime;
  private int processAmount;
  private int averageTime;
  private static final int MIN_MULTIPLIER = 1;
  private static final int MAX_MULTIPLIER = 3;
  // controller to kill the process in the GUI
  private MainControllerInterface controller;

  public Garanteed(MainControllerInterface controller) {
    this.readyProcessList = new ArrayList<>();
    this.deadProcessList = new ArrayList<>();
    this.controller = controller;
  }

  @Override
  public BCP getNext() {
    if(readyProcessList.isEmpty())
      return null;

    BCP nextProcessReference = readyProcessList.get(0);
    // the first process is null, it will be removed and the loop will try again
    if(timeToDie(nextProcessReference) < 0){
      killProcess(nextProcessReference);
      readyProcessList.remove(nextProcessReference);
      return null;
    }

    for(BCP process : readyProcessList){
      if(timeToDie(process) < 0){
        killProcess(process);
        continue;
      }
      if(timeToDie(process) < timeToDie(nextProcessReference)) {
        nextProcessReference = process;
      }
    }
    // removing dead process
    for(BCP deadProcess : deadProcessList) {
      readyProcessList.remove(deadProcess);
    }

    processAmount--;
    readyProcessList.remove(nextProcessReference);
    return nextProcessReference;
  }

  private int timeToDie(BCP process) {
    return process.getDeadLine() - Variables.timer;
  }

  private void killProcess(BCP process) {
    for(ProcessInfoBox box: controller.getProcessBoxes()) {
      if(process.getId() == box.getIdentifier()) {
        Platform.runLater(() -> box.kill());
        break;
      }
    }
    deadProcessList.add(process);
  }

  @Override
  public void addProcess(BCP process) {
    totalProcessTime += process.getTime();
    processAmount++; // a process was added
    averageTime = totalProcessTime / processAmount;

    int averageProcessTime = process.getTime() + averageTime / 2;
    // it has a chance to be multiplied per 0.5 to 2.5
    double deadLineMultiplier = Util.generateRandomNumber(MIN_MULTIPLIER, MAX_MULTIPLIER) - 0.5;

    // calculates with the multiplier and half of the total process time, so it has a chance to die
    int deadLine = (int) Math.round(averageProcessTime * deadLineMultiplier) + (totalProcessTime/2) + Variables.timer;

    process.setDeadLine(deadLine);

    readyProcessList.add(process);
  }

  @Override
  public void removeProcess() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void returnProcess(BCP process) {
    totalProcessTime -= (Variables.timeSlice - Variables.remainingTimeSlice);
    processAmount++; // a process was added

    readyProcessList.add(process);
  }
  
}
