package thread;

import global.Variables;
import interfaces.MainControllerInterface;
import javafx.application.Platform;

public class TimerLoop extends Thread {
  private MainControllerInterface controller;

  public TimerLoop(MainControllerInterface controller) {
    this.controller = controller;
  }

  @Override
  public void run() {
    try {
      while (true) {
        sleepOneSecondWithSpeed();
        Variables.timer++;
        Platform.runLater(() -> controller.updateTimer());
      }
    } catch (Exception e) {   
      Variables.timer = 0; // reset the timer when the thread is interrupted
      Platform.runLater(() -> controller.updateTimer()); // update the timer in view
    }
  }

  private void sleepOneSecondWithSpeed() throws InterruptedException {
    sleep(Variables.ONE_SECOND / Variables.speed);
  }
}
