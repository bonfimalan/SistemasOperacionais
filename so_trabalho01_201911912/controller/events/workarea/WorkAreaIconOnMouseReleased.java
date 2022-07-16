/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/07/2021 14:58
 * Last update: 
 * Name: 
 * Function: 
 *******************************************************************/
package controller.events.workarea;

import global.Controllers;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import view.desktop.components.workarea.WorkArea;
import view.desktop.components.workarea.components.WorkAreaIcon;

public class WorkAreaIconOnMouseReleased implements EventHandler<MouseEvent> {
  private WorkAreaIcon icon;
  public static final double GRID_SIZE = 100;

  public WorkAreaIconOnMouseReleased(WorkAreaIcon icon) {
    this.icon = icon;
  }

  @Override
  public void handle(MouseEvent event) {
    WorkArea workArea = Controllers.workAreaController.getWorkArea();

    int gridXPosition = (int) event.getSceneX();
    int gridYPosition = (int) event.getSceneY();

    gridXPosition /= GRID_SIZE;
    gridYPosition /= GRID_SIZE;

    double mouseX = gridXPosition * GRID_SIZE;
    double mouseY = gridYPosition * GRID_SIZE;
    
    // verifies if it can stay at tha place
    try{
      double xBounder = ( (int) workArea.getWidth() / 100 ) * 100;
      double yBounder = ( (int) workArea.getHeight() / 100 ) * 100;
      if (mouseX < Controllers.workAreaController.getPreviousWidth()
          && mouseY < Controllers.workAreaController.getPreviousHeight() - 100
          && Controllers.workAreaController.isGridAreaOccupied(gridXPosition, gridYPosition)) {
        
        icon.setLayoutX(mouseX);
        icon.setLayoutY(mouseY);
        Controllers.workAreaController.occupyGridArea(gridXPosition, gridYPosition);
      }
      else {
        Controllers.workAreaController.resetIconPosition(icon);
      }
    } catch(ArrayIndexOutOfBoundsException e) {
      Controllers.workAreaController.resetIconPosition(icon);
    }

  }
}
