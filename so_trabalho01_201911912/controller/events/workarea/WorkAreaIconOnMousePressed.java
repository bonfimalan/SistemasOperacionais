/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/07/2021 14:58
 * Last update: 
 * Name: 
 * Function: 
 *******************************************************************/
package controller.events.workarea;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class WorkAreaIconOnMousePressed implements EventHandler<MouseEvent>  {
  private double mouseX;
  private double mouseY;

  @Override
  public void handle(MouseEvent event) {
    mouseX = event.getX();
    mouseY = event.getY();
  }

  public double getMouseX() {
    return mouseX;
  }

  public double getMouseY() {
    return mouseY;
  }
}
