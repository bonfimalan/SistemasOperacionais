package algorithms.fit;

import java.util.List;

import algorithms.exceptions.ProcessTooBigException;
import algorithms.interfaces.MemoryAlgInterface;
import controller.interfaces.MainControllerInterface;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.BCP;
import view.PartitionConfigView;

public class FirstFit extends FitAlgSuperClass implements MemoryAlgInterface {

  public FirstFit(AnchorPane memory, VBox processWaitArea, MainControllerInterface controller) {
    super(memory, processWaitArea, controller);
  }

  @Override
  public void addProcess(BCP process) {
    try {
      // the process was added
      if(super.memoryList.addToFirstFreeMemorySpaceThatFits(process, super.memorySize)) return;

      // the process was not added
      //super.waitList.add(process);

      //super.addProcessToWait(process);

    } catch (ProcessTooBigException e) {
      super.controller.addWarning("Process is bigger than the memory");
    }
  }

  @Override
  public void removeProcess(int id) {
    super.memoryList.removeProcessFromMemory(id, memorySize);
    /*
    if(!super.waitList.isEmpty()) {
      // will add all process from the wait list that the memory can fit
      for(BCP process : waitList) {
        try {
          if(super.memoryList.addToSmallestFreeSpaceThatFits(process, super.memorySize)){
            waitList.remove(process);
            super.removeFromWaitViewSuper(process.getId());
          }
        } catch (ProcessTooBigException e) { }
      }
    }
     */
  }

  @Override
  public void removeFromWaitView(int id) {
    super.removeFromWaitViewSuper(id);
  }

  @Override
  public void setMemorySize(int memorySize) {
    super.memorySize = memorySize;
    memoryList.configMemoryAreaHeigth(memorySize);
  }

  @Override
  public void partitionAlgConfig(List<PartitionConfigView> partitionConfigList, int[] partitionSizes) {    
  }
  
}
