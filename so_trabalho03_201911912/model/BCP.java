package model;

import global.Variables;

public class BCP {
  private String name;
  private int time;
  private int priority;
  private boolean blocked;
  private int timeBlocked;
  private int deadLine;
  private int id;
  private int timeCreated;

  private static int count = 0;
  
  public BCP(int time, int priority, boolean blocked, int timeBlocked, int deadLine) {
    this.id = count;
    this.name = "Process " + count;
    count++;
    this.time = time;
    this.priority = priority;
    this.blocked = blocked;
    this.timeBlocked = timeBlocked;
    this.deadLine = deadLine;
    this.timeCreated = Variables.timer;
  }

  public void run() {
    time--;
  }

  public void runBlocked() {
    timeBlocked--;
  }

  public static void resetCount() {
    count = 0;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  public boolean isBlocked() {
    return blocked;
  }

  public void setBlocked(boolean blocked) {
    this.blocked = blocked;
  }

  public int getTimeBlocked() {
    return timeBlocked;
  }

  public void setTimeBlocked(int timeBlocked) {
    this.timeBlocked = timeBlocked;
  }

  public int getDeadLine() {
    return deadLine;
  }

  public void setDeadLine(int deadLine) {
    this.deadLine = deadLine;
  }

  public int getId() {
    return id;
  } 
  
  public int getTimeCreated() {
    return timeCreated;
  }

  public void setTimeCreated(int timeCreated) {
    this.timeCreated = timeCreated;
  }
}
