package algorithms;

import java.util.LinkedList;
import java.util.List;

import algorithms.interfaces.MemoryAlgInterface;
import controller.interfaces.MainControllerInterface;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.BCP;
import view.InMemoryProcessView;
import view.PartitionConfigView;
import view.ProcessWaitView;

public class AbsoluteStaticPartitionedAllocation implements MemoryAlgInterface {
  private AnchorPane memory;
  private VBox processWaitArea;
  private boolean isMemoryFree;
  private LinkedList<BCP> buffer;
  private MainControllerInterface controller;
  private int memorySize;

  public AbsoluteStaticPartitionedAllocation(AnchorPane memory, VBox processWaitArea, MainControllerInterface controller) {
    this.memory = memory;
    this.processWaitArea = processWaitArea;
    this.isMemoryFree = true; // there's no process in the memory
    this.buffer = new LinkedList<>();
    this.controller = controller;
  }

  @Override
  public void addProcess(BCP process) {
    if(isMemoryFree) {
      addProcessToMemory(process);
      isMemoryFree = false;
      return;
    }
    // the memory is not free so it's added to the buffer
    buffer.addLast(process);
    addProcessToWait(process);
  }

  @Override
  public void removeProcess(int id) {
    // remove the one process in the memory
    memory.getChildren().clear();
    // there's no process in buffer
    if(buffer.isEmpty()) {
      isMemoryFree = true;
      return;
    }
    // there's process in the buffer, so it must be put in the memory
    BCP process = buffer.removeFirst();
    removeFromWait(); // it'll remove the first
    addProcessToMemory(process);
  }

  /**
   * Called when a process is removed from the buffer by the user
   * 
   * @param id ID from the process that will be removed in the graphic and buffer
   */
  @Override
  public void removeFromWaitView(int id) {
    for(Node node : processWaitArea.getChildren()) {
      if(node instanceof ProcessWaitView) {
        ProcessWaitView processWaitView = (ProcessWaitView) node;
        if (processWaitView.getProcess().getId() == id) {
          processWaitArea.getChildren().remove(node); // removes from view
          buffer.remove(processWaitView.getProcess()); // removes from buffer
          return;
        } // end if
      } // end if
    } // end for  
  }

  private void addProcessToWait(BCP process) {
    ProcessWaitView waitView = new ProcessWaitView(controller, process);
    processWaitArea.getChildren().addAll(waitView);
  }
  
  private void removeFromWait() {
    processWaitArea.getChildren().remove(0);
  }  

  private void addProcessToMemory(BCP process) {
    InMemoryProcessView processInMemory = new InMemoryProcessView(controller, process);
    memory.getChildren().add(processInMemory);
  }

  public int getMemorySize() {
    return memorySize;
  }

  @Override
  public void setMemorySize(int memorySize) {
    this.memorySize = memorySize;
  }

  @Override
  public void partitionAlgConfig(List<PartitionConfigView> partitionConfigList, int[] partitionSizes) {
    // TODO Auto-generated method stub
    
  }
}
