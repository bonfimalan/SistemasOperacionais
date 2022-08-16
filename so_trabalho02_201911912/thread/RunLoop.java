package thread;

import java.util.List;
import java.util.Random;

import global.Variables;
import interfaces.MainControllerInterface;
import interfaces.ScaleAlgorithm;
import javafx.application.Platform;
import model.BCP;

public class RunLoop extends Thread {
  private int timeSlice;
  private int randomNumber;
  private ScaleAlgorithm algorithm;
  private MainControllerInterface controller;
  private Random random;
  private boolean canGoBack = true;
  private List<BCP> blockedProcessListReference;

  public RunLoop(ScaleAlgorithm algorithm, MainControllerInterface controller) {
    this.algorithm = algorithm;
    this.controller = controller;
    this.random = new Random();
  }

  @Override
  public void run() {
    try {
      blockedProcessListReference = controller.getBlockedProcessList();
      while (true) {
        timeSlice = Variables.timeSlice;
        BCP process = algorithm.getNext();
        // it will be here if the alg doesn't have process
        if (process == null) {
          // executing the time of the blocked process
            //List<BCP> list = controller.getBlockedProcessList();
            if(!blockedProcessListReference.isEmpty()){
              sleepOneSecondWithSpeed();
              //Variables.MUTEX.acquire();
              for(BCP bcp : blockedProcessListReference) {
                Platform.runLater(() -> controller.runBlockedTime(bcp));
                //Variables.MUTEX.release();
              }
            }
            //else 
            { sleep(10);}
        } 
        else {
          //System.out.println(process.getName());

          //Variables.MUTEX.acquire();
          Platform.runLater(() -> {
            controller.moveToRunning(process.getId());
            controller.setRunningProcess(process);
            //Variables.MUTEX.release();
          });

          while (timeSlice != 0) {
            sleepOneSecondWithSpeed();

            timeSlice--;
            //Variables.MUTEX.acquire();
            Platform.runLater(() -> {
              controller.runProcess();
              //Variables.MUTEX.release();
            });

            // executing the time of the blocked process
            //List<BCP> list = controller.getBlockedProcessList();
            if(!blockedProcessListReference.isEmpty())
              for(BCP bcp : blockedProcessListReference) {
                //Variables.MUTEX.acquire();
                Platform.runLater(() -> {
                  controller.runBlockedTime(bcp);
                  //Variables.MUTEX.release();
                });
              }
            

            // the process is finished
            if (controller.getRunningProcessTime() == 0) {
              //Variables.MUTEX.acquire();
              Platform.runLater(() -> {
                controller.moveToDone();
                //Variables.MUTEX.release();
              });
              canGoBack = false;
              break;
            }

            // calculating block chance
            // generates a number between 1 and 100
            randomNumber = random.nextInt(100) + 1;
            if (randomNumber <= Variables.blockPercentage && timeSlice > 0) {
              //Variables.MUTEX.acquire();
              Platform.runLater(() -> {
                controller.moveToBlocked();
                //Variables.MUTEX.release();
              });
              canGoBack = false;
              break;
            }
          } // end time slice while
          if(canGoBack) {
            //Variables.MUTEX.acquire();
            Platform.runLater(() -> {
              controller.moveFromRunningToReady();
              //Variables.MUTEX.release();
            });
            // the thread needs to wait since the javafx will put the process back
            // in the data struct later
            sleep(20);
          }
          canGoBack = true;
        } // end else
      }// end while
    } catch (Exception e) {
    }
  }

  private void sleepOneSecondWithSpeed() throws InterruptedException {
    sleep(Variables.ONE_SECOND / Variables.speed);
  }
}
