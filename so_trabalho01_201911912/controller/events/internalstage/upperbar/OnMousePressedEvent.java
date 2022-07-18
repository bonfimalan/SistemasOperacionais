/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/07/2021 20:51
 * Last update: 
 * Name: 
 * Function: 
 *******************************************************************/
package controller.events.internalstage.upperbar;

import controller.InternalStageController;
import controller.WorkAreaController;
import global.Controllers;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import view.desktop.components.workarea.WorkArea;

public class OnMousePressedEvent implements EventHandler<MouseEvent> {
  private InternalStageController internalStageController;
  private WorkAreaController workAreaController;

  public OnMousePressedEvent(InternalStageController internalStageController) {
    this.internalStageController = internalStageController;
  }

  @Override
  public void handle(MouseEvent event) {
    // this event is only for primary button
    if(!event.getButton().equals(MouseButton.PRIMARY)) return;
    
    workAreaController = Controllers.workAreaController;
    WorkArea workArea = workAreaController.getWorkArea();

    // the InternalStage will change the size of it parent
    // so I need to store the real size of the window
    workAreaController.setPreviousHeight(workArea.getHeight());
    workAreaController.setPreviousWidth(workArea.getWidth());

    // off set to move the InternalStage with the mouse 
    internalStageController.setOffSetX(event.getX());
    internalStageController.setOffSetY(event.getY());
  }
  
}
