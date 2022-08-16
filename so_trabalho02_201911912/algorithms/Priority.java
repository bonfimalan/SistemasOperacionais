package algorithms;

import java.util.ArrayList;
import java.util.List;

import global.Variables;
import interfaces.ScaleAlgorithm;
import model.BCP;

public class Priority implements ScaleAlgorithm {
  private List<BCP> processList;

  public Priority() {
    processList = new ArrayList<>();
  }

  @Override
  public BCP getNext() {
    if(processList.isEmpty()) return null;
    
    int tempPriority = 11;
    BCP temp = null;
    for(BCP process : processList) {
      if(process.getPriority() == Variables.higherPriority) {
        temp = process;
        break;
      }
      if(process.getPriority() < tempPriority) {
        temp = process;
        tempPriority = process.getPriority();
      }
    }
    processList.remove(temp);
    return temp;
  }

  @Override
  public void addProcess(BCP process) {
    processList.add(process);
  }

  @Override
  public void removeProcess() {

  }

}
