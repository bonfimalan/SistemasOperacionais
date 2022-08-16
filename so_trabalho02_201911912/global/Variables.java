package global;

import java.util.concurrent.Semaphore;

public class Variables {
  // interval in seconds to the ready process
  public static int readyMinSeconds = 2;
  public static int readyMaxSeconds = 20;

  // time slice
  public static final int TIME_SLICE_MIN_VALUE = 2;
  public static int timeSlice = TIME_SLICE_MIN_VALUE;

  // block interval and percentage value
  public static int blockedMinSeconds = 2;
  public static int blockedMaxSeconds = 10;
  public static final int PERCENTAGE_BLOCK_MIN_VALUE = 0;
  public static final int PERCENTAGE_BLOCK_MAX_VALUE = 20;
  public static int blockPercentage = PERCENTAGE_BLOCK_MIN_VALUE;

  // priority values
  public static int lowerPriority = 10;
  public static int higherPriority = 1;

  // down menu values
  public static final int MIN_SPEED = 1;
  public static final int MAX_SPEED = 5;
  public static int speed = MIN_SPEED;

  // unit values to be used in the NumberSelector class
  public static final String SECOND = "s";
  public static final String PERCENTAGE = "%";
  public static final String MULTIPLIER = "x";

  // variables to use in the threads
  public static final int ONE_SECOND = 1000;
  public static final Semaphore MUTEX = new Semaphore(1);

  // alg names
  public static final String[] ALG_NAMES = {
    "1 - FIFO",
    "2 - Priority",
    "3 - Shortest Job First"
  };
}
