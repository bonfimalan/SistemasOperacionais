/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 26/07/2021 15:35
 * Last update: 22/07/2021 20:59
 * Name: WorkAreaIconOnMouseReleased.java
 * Function: Calculates the positoin in whit the icon must be put,
 *    and occupies that position in the grid
 *******************************************************************/
package controller.events.workarea;

import global.Controllers;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import view.desktop.components.workarea.components.WorkAreaIcon;

public class WorkAreaIconOnMouseReleased implements EventHandler<MouseEvent> {
  private WorkAreaIcon icon;
  public static final double GRID_SIZE = 100;

  public WorkAreaIconOnMouseReleased(WorkAreaIcon icon) {
    this.icon = icon;
  }

  @Override
  public void handle(MouseEvent event) {
    // this event is only for primary button
    if(!event.getButton().equals(MouseButton.PRIMARY)) return;
    
    int gridXPosition = (int) event.getSceneX();
    int gridYPosition = (int) event.getSceneY();

    gridXPosition /= GRID_SIZE;
    gridYPosition /= GRID_SIZE;

    double mouseX = gridXPosition * GRID_SIZE;
    double mouseY = gridYPosition * GRID_SIZE;
    
    // verifies if it can stay at tha place
    // try{
      if (!Controllers.workAreaController.isGridAreaOccupied(gridXPosition, gridYPosition)
          && mouseX < Controllers.workAreaController.getPreviousWidth()
          && mouseX >= 0
          && mouseY < Controllers.workAreaController.getPreviousHeight() - 100
          && mouseY >= 0) {
        
        icon.setLayoutX(mouseX);
        icon.setLayoutY(mouseY);
        // Controllers.workAreaController.occupyGridArea(gridXPosition, gridYPosition);
      }
      else {
        Controllers.workAreaController.resetIconPosition(icon);
      }
    //} catch(ArrayIndexOutOfBoundsException e) {
      //Controllers.workAreaController.resetIconPosition(icon);
    // }

  }
}
