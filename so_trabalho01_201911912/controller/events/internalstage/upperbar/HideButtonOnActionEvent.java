/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 18/07/2021 11:43
 * Last update: 22/07/2021 20:30
 * Name: HideButtonOnActionEvent.java
 * Function: Event that is called when the hide button is pressed, it will
 *    hide the InternalStage
 *******************************************************************/
package controller.events.internalstage.upperbar;

import controller.InternalStageController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class HideButtonOnActionEvent implements EventHandler<MouseEvent> {
  private InternalStageController internalStageController;

  public HideButtonOnActionEvent(InternalStageController internalStageController) {
    this.internalStageController = internalStageController;
  }

  @Override
  public void handle(MouseEvent event) {
    // this event is only for primary button
    if(!event.getButton().equals(MouseButton.PRIMARY)) return;
    
    internalStageController.hideStage();
    internalStageController.setHided(true);
  }
  
}