/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 18/07/2021 14:06
 * Last update: 22/07/2021 21:12
 * Name: InternalStageOnMouseClicked.java
 * Function: Will put the stage that is clicked on top of the others
 *******************************************************************/
package controller.events.internalstage;

import controller.InternalStageController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class InternalStageOnMouseClicked implements EventHandler<MouseEvent> {
  private InternalStageController internalStageController;
  public InternalStageOnMouseClicked(InternalStageController internalStageController) {
    this.internalStageController = internalStageController;
  }

  @Override
  public void handle(MouseEvent event) {
    // this event is only for primary button
    if(!event.getButton().equals(MouseButton.PRIMARY)) return;
    
    internalStageController.onTop();
  }
  
}
