package interfaces;

import model.BCP;

public interface ScaleAlgorithm {
  public BCP getNext();
  public void addProcess(BCP process);
  public void removeProcess();
  public void returnProcess(BCP process);
}
