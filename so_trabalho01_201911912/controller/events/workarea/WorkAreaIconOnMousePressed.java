/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/07/2021 14:58
 * Last update: 22/07/2021 20:30
 * Name: WorkAreaIconOnMousePressed.java
 * Function: Handles the event that occur when the mouse is pressed in
 *    the icon, it's used to get some information to the drag event
 *******************************************************************/
package controller.events.workarea;

import global.Controllers;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import view.desktop.components.workarea.components.WorkAreaIcon;

public class WorkAreaIconOnMousePressed implements EventHandler<MouseEvent>  {
  private WorkAreaIcon icon;
  public static final double GRID_SIZE = 100;

  public WorkAreaIconOnMousePressed(WorkAreaIcon icon) {
    this.icon = icon;
  }

  @Override
  public void handle(MouseEvent event) {
    // this event is only for primary button
    if(!event.getButton().equals(MouseButton.PRIMARY)) return;
    
    Controllers.workAreaController.setMovingIconPreviousX(icon.getLayoutX());
    Controllers.workAreaController.setMovingIconPreviousY(icon.getLayoutY());
    Controllers.workAreaController.onIconPressed();

    int gridXPosition = (int) icon.getLayoutX();
    int gridYPosition = (int) icon.getLayoutY();

    gridXPosition /= GRID_SIZE;
    gridYPosition /= GRID_SIZE;
    
    Controllers.workAreaController.freeGridArea(gridXPosition, gridYPosition);
  }
}
