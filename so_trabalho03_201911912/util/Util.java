package util;

import java.util.Random;

import global.Variables;
import model.BCP;
import view.components.ProcessInfoBox;

public class Util {
  private static Random random = new Random();

  public static BCP generateBCP() {
    int time = generateRandomNumber(Variables.readyMinSeconds, Variables.readyMaxSeconds);
    // lower pritority is a bigger number than higher priority
    int priority = generateRandomNumber(Variables.higherPriority, Variables.lowerPriority);
    boolean blocked = false;
    int timeBlocked = 0;
    int deadLine = 0; // not used yet
    BCP bcp = new BCP(time, priority, blocked, timeBlocked, deadLine);
    return bcp;
  }

  public static ProcessInfoBox generateProcessInfoBox(BCP bcp) {
    return new ProcessInfoBox(bcp.getId(), bcp.getName(), bcp.getTime());
  }

  public static ProcessInfoBox generateProcessInfoBoxWithPriority(BCP bcp) {
    return new ProcessInfoBox(bcp.getId(), bcp.getName(), bcp.getTime(), bcp.getPriority());
  }

  public static ProcessInfoBox generateProcessInfoBoxWithDeadLine(BCP bcp) {
    return new ProcessInfoBox(bcp.getId(), bcp.getName(), bcp.getTime(), bcp.getTimeCreated(), bcp.getDeadLine());
  }

  public static int generateRandomNumber(int min, int max) {
    // generates a number between max + 1 and min
    max++;
    return random.nextInt(max - min) + min;
  }
}
