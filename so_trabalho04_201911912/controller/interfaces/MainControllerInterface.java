/********************************************************************
* Author: Alan Bonfim Santos
* Registration: 201911912
* Initial date: 16/09/22 18:52
* Last update: 18/09/22 00:23
* Name: MainControllerInterface.java
* Function:
*******************************************************************/
package controller.interfaces;

public interface MainControllerInterface {
  public void reset();
  public void removeProcess(int id);
  public void removeProcessFromWait(int id);
  public void addWarning(String warningMessage);
}
