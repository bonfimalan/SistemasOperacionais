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
import view.desktop.components.workarea.components.WorkAreaIcon;

public class WorkAreaIconOnMouseReleased implements EventHandler<MouseEvent>  {
  private WorkAreaIcon icon;
  public static final double GRID_SIZE = 100;

  public WorkAreaIconOnMouseReleased(WorkAreaIcon icon) {
    this.icon = icon;
  }

  @Override
  public void handle(MouseEvent event) {
    int mouseX = (int) event.getSceneX();
    int mouseY = (int) event.getSceneY();
    mouseX /= GRID_SIZE;
    mouseY /= GRID_SIZE;

      icon.setLayoutX(mouseX * GRID_SIZE);
      icon.setLayoutY(mouseY * GRID_SIZE);

  }
}
