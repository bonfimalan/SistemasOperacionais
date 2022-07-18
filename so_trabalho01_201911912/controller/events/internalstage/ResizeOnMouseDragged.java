/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 17/07/2021 13:08
 * Last update: 
 * Name: 
 * Function: 
 *******************************************************************/

package controller.events.internalstage;

import controller.InternalStageController;
import controller.events.internalstage.interfaces.HorizontalRisize;
import controller.events.internalstage.interfaces.VerticalResize;
import global.Controllers;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ResizeOnMouseDragged<T extends HorizontalRisize, S extends VerticalResize>
    implements EventHandler<MouseEvent> {
  private InternalStageController internalStageController;
  private T horizontal;
  private S vertical;

  public ResizeOnMouseDragged(InternalStageController internalStageController, T horizontal, S vertical) {
    this.internalStageController = internalStageController;
    this.horizontal = horizontal;
    this.vertical = vertical;
  }

  @Override
  public void handle(MouseEvent event) {
    // System.out.println(event.getX() + " " + event.getY());

    double sceneMouseX = event.getSceneX();
    double sceneMouseY = event.getSceneY();

    if (sceneMouseX >= 0
        && sceneMouseX <= Controllers.workAreaController.getPreviousWidth() + InternalStageController.PADDING_SIZE)
      horizontal.horizontalResize(internalStageController.getInternalStage(), event);
    if (sceneMouseY >= 0
        && sceneMouseY <= Controllers.workAreaController.getPreviousHeight() + InternalStageController.PADDING_SIZE)
      vertical.veticalResize(internalStageController.getInternalStage(), event);
  }

}
