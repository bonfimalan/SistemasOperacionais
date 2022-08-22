package interfaces;

import java.util.List;

import model.BCP;

public interface MainControllerInterface {
  public void reset();
  public void setRunningProcess(BCP process);
  public int getRunningProcessTime();
  public void runProcess();
  public void moveToRunning(int id);
  public void moveFromRunningToReady();
  public void moveFromBlockedToReady(int id);
  public void moveToDone();
  public void moveToBlocked();
  public void runBlockedTime(BCP blocked);
  public void blockProcess();
  public List<BCP> getBlockedProcessList();
  public void updateTimer();
}
