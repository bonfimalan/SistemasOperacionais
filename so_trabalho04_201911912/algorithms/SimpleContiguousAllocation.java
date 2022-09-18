package algorithms;

import java.util.LinkedList;
import java.util.List;

import algorithms.interfaces.MemoryAlgInterface;
import algorithms.util.MemoryUtil;
import controller.interfaces.MainControllerInterface;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.BCP;
import view.InMemoryProcessView;
import view.PartitionConfigView;
import view.ProcessWaitView;

public class SimpleContiguousAllocation implements MemoryAlgInterface {
  private boolean[] partitionIsFree;
  private LinkedList<BCP>[] buffer;
  private AnchorPane memory;
  private VBox processWaitArea;
  private MainControllerInterface controller;
  private int memorySize;
  private int[] partitionsStartPointer;
  private int[] partitionSizes;

  public SimpleContiguousAllocation(AnchorPane memory, VBox processWaitArea, MainControllerInterface controller) {
    this.memory = memory;
    this.processWaitArea = processWaitArea;
    this.controller = controller;
  }

  @Override
  public void addProcess(BCP process) {
    int partition = process.getPartition();

    if(process.getSize() > partitionSizes[partition]) {
      controller.addWarning("Process is too big to the partition");
      return;
    }

    if(partitionIsFree[partition]) {
      addProcessToMemory(process);
      partitionIsFree[partition] = false;
      return;
    }
    // the partition is not free
    addProcessToWait(process);
    buffer[partition].addLast(process);
  }

  @Override
  public void removeProcess(int id) {
    BCP process;
    int partitionRemoved = 0;
    for(Node node : memory.getChildren()) {
      if(node instanceof InMemoryProcessView) {
        InMemoryProcessView inMemoryProcessView = (InMemoryProcessView) node;
        process = inMemoryProcessView.getProcess();
        if (process.getId() == id) {
          memory.getChildren().remove(node); // removes from view
          partitionRemoved = process.getPartition();
          break;
        } // end if
      } // end if
    } // end for
    if(buffer[partitionRemoved].isEmpty()) {
      partitionIsFree[partitionRemoved] = true;
      return;
    }
    // there's one or more process in the buffer
    BCP processFromBuffer = buffer[partitionRemoved].removeFirst();
    removeFromWaitView(processFromBuffer.getId());
    addProcessToMemory(processFromBuffer);
  }

  @Override
  public void removeFromWaitView(int id) {
    BCP process;
    for(Node node : processWaitArea.getChildren()) {
      if(node instanceof ProcessWaitView) {
        ProcessWaitView processWaitView = (ProcessWaitView) node;
        process = processWaitView.getProcess();
        if (process.getId() == id) {
          processWaitArea.getChildren().remove(node); // removes from view
          buffer[process.getPartition()].remove(process); // removes from buffer
          return;
        } // end if
      } // end if
    } // end for  
  }

  @Override
  public void setMemorySize(int memorySize) {
    this.memorySize = memorySize;
  }

  @Override
  public void partitionAlgConfig(List<PartitionConfigView> partitionConfigList, int[] partitionSizes) {
    int partitionAmount = partitionConfigList.size();

    // creating the buffer
    buffer = new LinkedList[partitionAmount];
    for(int i = 0; i < partitionAmount; i++) {
      buffer[i] = new LinkedList<BCP>();
    }

    this.partitionSizes = partitionSizes;

    // patitioning the array
    MemoryUtil.partitionMemory(memory, memorySize, partitionSizes);

    partitionsStartPointer = MemoryUtil.referenceArrayPartitionsStartPointers;

    // config to the array that will keep track of the free spaces
    partitionIsFree = new boolean[partitionAmount];
    for (int i = 0; i < partitionIsFree.length; i++) partitionIsFree[i] = true;
  }

  private void addProcessToMemory(BCP process) {
    InMemoryProcessView processInMemory = new InMemoryProcessView(controller, process);
    processInMemory.setLayoutY(partitionsStartPointer[process.getPartition()]);
    memory.getChildren().add(processInMemory);
  }

  private void addProcessToWait(BCP process) {
    ProcessWaitView waitView = new ProcessWaitView(controller, process, new Label("Partition" + process.getPartition()));
    processWaitArea.getChildren().addAll(waitView);
  }
}
