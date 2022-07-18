/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 17/07/2021 11:29
 * Last update: 
 * Name: 
 * Function: 
 *******************************************************************/
package controller.events.internalstage.upperbar;

import controller.InternalStageController;
import global.Controllers;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class OnMouseDraggedEvent implements EventHandler<MouseEvent> {
  private InternalStageController internalStageController;

  public OnMouseDraggedEvent(InternalStageController internalStageController) {
    this.internalStageController = internalStageController;
  }

  @Override
  public void handle(MouseEvent event) {
    // this event is only for primary button
    if(!event.getButton().equals(MouseButton.PRIMARY)) return;
    
    // position of the mouse in the parent
    double mouseXPosition = event.getSceneX();
    double mouseYPosition = event.getSceneY();

    // movement to the X axis
    if(mouseXPosition > 0 && mouseXPosition < Controllers.workAreaController.getPreviousWidth()) {
      double xOffSet = internalStageController.getOffSetX();
      internalStageController.getInternalStage().setLayoutX(
          mouseXPosition - xOffSet);
    }

    // movement to the Y axis
    if(mouseYPosition > 0 && mouseYPosition < Controllers.workAreaController.getPreviousHeight()) {
      double yOffSet = internalStageController.getOffSetY();
      internalStageController.getInternalStage().setLayoutY(
        mouseYPosition - yOffSet);
    }
  }

}
