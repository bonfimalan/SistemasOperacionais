package thread;

import java.util.List;

import global.Variables;
import interfaces.MainControllerInterface;
import javafx.application.Platform;
import model.BCP;

public class BlockedLoop extends Thread {
  private MainControllerInterface controller;
  private List<BCP> blockedProcessListReference;

  public BlockedLoop(MainControllerInterface controller) {
    this.controller = controller;
  }

  @Override
  public void run() {
    try {
      blockedProcessListReference = controller.getBlockedProcessList();
      while(true) {
        sleep(10);
        while(!blockedProcessListReference.isEmpty()) {
          for(BCP process : blockedProcessListReference){
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
