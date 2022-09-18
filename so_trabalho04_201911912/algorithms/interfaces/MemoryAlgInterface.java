package algorithms.interfaces;

import java.util.List;

import model.BCP;
import view.PartitionConfigView;

public interface MemoryAlgInterface {
  public void addProcess(BCP process);
  public void removeProcess(int id);
  public void removeFromWaitView(int id);
  public void setMemorySize(int memorySize);
  public void partitionAlgConfig(List<PartitionConfigView> partitionConfigList, int[] partitionSizes);
}
