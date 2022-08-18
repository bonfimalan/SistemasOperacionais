package algorithms;

import java.util.LinkedList;

import interfaces.ScaleAlgorithm;
import model.BCP;

public class RoundRobin implements ScaleAlgorithm {
  private LinkedList<BCP> processQueue;
  
  public RoundRobin() {
    processQueue = new LinkedList<>();
  }

  @Override
  public BCP getNext() {
    if(processQueue.isEmpty()) return null;
    
    return processQueue.removeFirst();
  }

  @Override
  public void addProcess(BCP process) {
    processQueue.addLast(process);
  }

  @Override
  public void removeProcess() {
    
  }

  @Override
  public void returnProcess(BCP process) {
    processQueue.addLast(process);
  }
}
