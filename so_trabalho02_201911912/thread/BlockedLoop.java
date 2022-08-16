package thread;

import global.Variables;
import interfaces.MainControllerInterface;
import javafx.application.Platform;
import model.BCP;

public class BlockedLoop extends Thread {
  private MainControllerInterface controller;

  public BlockedLoop(MainControllerInterface controller) {
    this.controller = controller;
  }

  @Override
  public void run() {
    try {
      while(true) {
        sleep(10);
        while(!controller.getBlockedProcessList().isEmpty()) {
          for(BCP process : controller.getBlockedProcessList()){
            //Variables.MUTEX.acquire();
            Platform.runLater(() -> {
              controller.runBlockedTime(process);
              //Variables.MUTEX.release();
            });
          }

          sleepOneSecondWithSpeed();
        }
      }
    } catch (Exception e) { }
  }

  private void sleepOneSecondWithSpeed() throws InterruptedException {
    sleep(Variables.ONE_SECOND / Variables.speed);
  }
}
