/********************************************************************
* Author: Alan Bonfim Santos
* Registration: 201911912
* Initial date: 17/09/22 20:14
* Last update: 18/09/22 00:05
* Name: MemoryUtil.java
* Function:
*******************************************************************/
package algorithms.util;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MemoryUtil {
  public static int[] referenceArrayPartitionsStartPointers;
  public static int SIZE_MULTIPLYER = 20;
  public static AnchorPane partitionMemory(AnchorPane memory, int memorySize, int[] partitionsSize) {
    // size of memory
    int temp = 0;
    memory.setPrefHeight(memorySize * SIZE_MULTIPLYER);
    
    referenceArrayPartitionsStartPointers = new int[partitionsSize.length];

    for(int i = 0; i < partitionsSize.length; i++) {
      referenceArrayPartitionsStartPointers[i] = temp;
      if(temp != 0)
        memory.getChildren().add(createDivision(temp));   
      temp = temp + partitionsSize[i] * SIZE_MULTIPLYER;
      temp++;
    }

    return memory;
  }

  private static Rectangle createDivision(int layoultY) {
    Rectangle rectangle = new Rectangle(200, 1);
    rectangle.setStroke(Paint.valueOf("black"));
    rectangle.setLayoutY(layoultY);
    return rectangle;
  }
}
