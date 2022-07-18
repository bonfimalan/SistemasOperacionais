/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 18/07/2021 12:09
 * Last update: 
 * Name: 
 * Function: 
 *******************************************************************/
package controller.events.lowerbar;

import controller.InternalStageController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class LowerBarButtonOnAction implements EventHandler<MouseEvent> {
  private InternalStageController internalStageController;

  public LowerBarButtonOnAction(InternalStageController internalStageController) {
    this.internalStageController = internalStageController;
  }

  @Override
  public void handle(MouseEvent event) {
    // this event is only for primary button
    if(!event.getButton().equals(MouseButton.PRIMARY)) return;
    
    internalStageController.maximize();
  }
}
