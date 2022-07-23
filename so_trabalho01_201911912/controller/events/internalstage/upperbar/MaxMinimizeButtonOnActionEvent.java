/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 18/07/2021 11:43
 * Last update: 22/07/2021 21:05
 * Name: MaxMinimizeButtonOnActionEvent.java
 * Function: Change the size of the internal stage when the button is
 *    pressed, it will change the size to cover the area or toggle to
 *    the previous size
 *******************************************************************/
package controller.events.internalstage.upperbar;

import controller.InternalStageController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class MaxMinimizeButtonOnActionEvent implements EventHandler<MouseEvent> {
  private InternalStageController internalStageController;

  public MaxMinimizeButtonOnActionEvent(InternalStageController internalStageController) {
    this.internalStageController = internalStageController;
  }

  @Override
  public void handle(MouseEvent event) {
    // this event is only for primary button
    if(!event.getButton().equals(MouseButton.PRIMARY)) return;
    
    internalStageController.changeSize();
  }
  
}