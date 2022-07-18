/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 18/07/2021 11:43
 * Last update: 
 * Name: 
 * Function: 
 *******************************************************************/
package controller.events.internalstage.upperbar;

import controller.InternalStageController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MaxMinimizeButtonOnActionEvent implements EventHandler<MouseEvent> {
  private InternalStageController internalStageController;

  public MaxMinimizeButtonOnActionEvent(InternalStageController internalStageController) {
    this.internalStageController = internalStageController;
  }

  @Override
  public void handle(MouseEvent event) {
    internalStageController.changeSize();
  }
  
}