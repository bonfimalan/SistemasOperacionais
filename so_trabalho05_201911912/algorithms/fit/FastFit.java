package algorithms.fit;

import java.util.ArrayList;
import java.util.List;

import algorithms.exceptions.ProcessTooBigException;
import algorithms.interfaces.MemoryAlgInterface;
import algorithms.util.FastNode;
import controller.interfaces.MainControllerInterface;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.BCP;
import view.PartitionConfigView;

public class FastFit extends FitAlgSuperClass implements MemoryAlgInterface {
  private FastNode[] freeSpacePointers;
  private VBox partitionConfigArea;
  private int memorySizeTemp = -1;

  public FastFit(AnchorPane memory, VBox processWaitArea, MainControllerInterface controller, VBox partitionConfigArea) {
    super(memory, processWaitArea, controller);
    this.partitionConfigArea = partitionConfigArea;
  }

  @Override
  public void addProcess(BCP process) {
    try {

      for (FastNode fn : freeSpacePointers) {
        if (fn.getSize() == process.getSize() && fn.hasChunks()) {
          super.memoryList.addQuickFreeMemorySpaceThatFits(process, memorySize, fn.getChunk());
          // update the pointers after add the process
          updateFreeSpacePointers();
          return;
        }
      }

      super.memoryList.addQuickFreeMemorySpaceThatFits(process, memorySize, null);
      // update the pointers after add the process
      updateFreeSpacePointers();
    } catch (ProcessTooBigException ex) {
      super.controller.addWarning("Process is bigger than the memory");
    }
  }

  @Override
  public void removeProcess(int id) {
    super.memoryList.removeProcessFromMemory(id, memorySize);
    // updates the pointer
    updateFreeSpacePointers();
  }

  private void updateFreeSpacePointers() {
    this.partitionConfigArea.getChildren().clear();
    for (FastNode fn : freeSpacePointers) {
      fn.setList(super.memoryList.getFreeSpaceSet(fn.getSize()));
      updatePartitionAreaText(fn);
    }
  }

  private void updatePartitionAreaText(FastNode fn) {
    Label text = new Label("Size: "+ fn.getSize() + " | Free: " + fn.getSet().size());
    this.partitionConfigArea.getChildren().add(text);
  }

  @Override
  public void removeFromWaitView(int id) {
  }

  @Override
  public void setMemorySize(int memorySize) {
    super.memorySize = memorySize;
    // more POG :D
    if(this.memorySizeTemp == -1)
      this.memorySizeTemp = memorySize;
  }

  @Override
  public void partitionAlgConfig(List<PartitionConfigView> partitionConfigList, int[] partitionSizes) {
    this.freeSpacePointers = new FastNode[partitionSizes.length];
    for (int i = 0; i < freeSpacePointers.length; i++) {
      freeSpacePointers[i] = new FastNode(partitionSizes[i], new ArrayList<>());
    }
    // I am doing some stuff in the controller, and I don't know where
    // so I am using POG here :D
    super.memorySize = memorySizeTemp;
    this.partitionConfigArea.getChildren().clear();
  }

}
