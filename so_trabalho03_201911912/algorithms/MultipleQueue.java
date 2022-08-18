package algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import interfaces.ScaleAlgorithm;
import model.BCP;

public class MultipleQueue implements ScaleAlgorithm {
  private List<LinkedList<BCP>> priorityList; // a list of lists, that's crazy

  public MultipleQueue() {
    priorityList = new ArrayList<>();

    for(int i=0; i<=10; i++)
      priorityList.add(new LinkedList<>());
  }

  @Override
  public BCP getNext() {
    for(LinkedList<BCP> list : priorityList) {
      if(list.isEmpty()) continue;
      
      return list.removeFirst();
    }
      
    return null;
  }

  @Override
  public void addProcess(BCP process) {
    int priority = process.getPriority();

    priorityList.get(priority).addLast(process);
  }

  @Override
  public void removeProcess() {

  }

  @Override
  public void returnProcess(BCP process) {
    int priority = process.getPriority();

    priorityList.get(priority).addFirst(process);
  }
  
}
