package controller.interfaces;

public interface MainControllerInterface {
  public void reset();
  public void removeProcess(int id);
  public void removeProcessFromWait(int id);
  public void addWarning(String warningMessage);
}
