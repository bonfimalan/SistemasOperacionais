package algorithms;

import java.util.LinkedList;

import interfaces.ScaleAlgorithm;
import model.BCP;

public class FIFO implements ScaleAlgorithm {
  private LinkedList<BCP> processQueue;
  
  public FIFO() {
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
}
