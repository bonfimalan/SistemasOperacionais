package algorithms;

import java.util.ArrayList;
import java.util.List;

import interfaces.ScaleAlgorithm;
import model.BCP;

public class SJF implements ScaleAlgorithm{
  private List<BCP> processList;

  public SJF() {
    processList = new ArrayList<>();
  }

  @Override
  public BCP getNext() {
    if(processList.isEmpty()) return null;
    
    BCP temp = processList.get(0);
    for(BCP process : processList) {
      if(process.getTime() < temp.getTime())
        temp = process;
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

  @Override
  public void returnProcess(BCP process) {
    processList.add(0, process);
  }
}
