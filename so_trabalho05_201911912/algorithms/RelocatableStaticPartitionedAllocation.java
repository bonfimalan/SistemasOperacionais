/********************************************************************
* Author: Alan Bonfim Santos
* Registration: 201911912
* Initial date: 17/09/22 17:47
* Last update: 18/09/22 00:49
* Name: RelocatableStaticPartitionedAllocation.java
* Function:
*******************************************************************/
package algorithms;

import java.util.LinkedList;
import java.util.List;

import algorithms.interfaces.MemoryAlgInterface;
import algorithms.util.MemoryUtil;
import controller.interfaces.MainControllerInterface;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.BCP;
import view.InMemoryProcessView;
import view.PartitionConfigView;
import view.ProcessWaitView;

public class RelocatableStaticPartitionedAllocation implements MemoryAlgInterface {
  private boolean[] partitionIsFree;
  private LinkedList<BCP> buffer;
  private AnchorPane memory;
  private VBox processWaitArea;
  private MainControllerInterface controller;
  private int memorySize;
  private int[] partitionsStartPointer;
  private int[] partitionSizes;
  private int biggestPartitionSize;

  public RelocatableStaticPartitionedAllocation(AnchorPane memory, VBox processWaitArea, MainControllerInterface controller) {
    this.memory = memory;
    this.processWaitArea = processWaitArea;
    this.controller = controller;
  }

  @Override
  public void addProcess(BCP process) {
    if(process.getSize() > biggestPartitionSize) {
      controller.addWarning("Process is too big to the partitions");
      return;
    }

    if(addProcessInFreeSpace(process)) return;
    
    // the partition is not free
    addProcessToWait(process);
    buffer.addLast(process);
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
    
    partitionIsFree[partitionRemoved] = true;
    
    // there's one or more process in the buffer
    BCP processFromBuffer = null;
    for(BCP bcp : buffer) {
      if(addProcessInFreeSpace(bcp)){
        processFromBuffer = bcp;
        break;
      }
    }
    if(processFromBuffer == null) return;
    //BCP processFromBuffer = buffer.removeFirst();
    processFromBuffer.setPartition(partitionRemoved);
    removeFromWaitView(processFromBuffer.getId());
    //addProcessToMemory(processFromBuffer);
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
          buffer.remove(process); // removes from buffer
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
    buffer = new LinkedList<>();
    
    // patitioning the array
    MemoryUtil.partitionMemory(memory, memorySize, partitionSizes);

    this.partitionSizes = partitionSizes;

    // fiding the biggest partition
    biggestPartitionSize = 0;
    for(int i : partitionSizes) {
      if(biggestPartitionSize < i) biggestPartitionSize = i;
    }

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
    ProcessWaitView waitView = new ProcessWaitView(controller, process);
    processWaitArea.getChildren().addAll(waitView);
  }

  private boolean addProcessInFreeSpace(BCP process) {
    for(int i =0; i < partitionIsFree.length; i++) {
      if(partitionIsFree[i] && process.getSize() <= partitionSizes[i]) {
        process.setPartition(i);
        addProcessToMemory(process);
        partitionIsFree[i] = false;
        return true;
      }
    }

    return false;
  }
  
}
