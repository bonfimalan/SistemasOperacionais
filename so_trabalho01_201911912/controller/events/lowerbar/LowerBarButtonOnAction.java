/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 18/07/2021 12:09
 * Last update: 22/07/2021 20:32
 * Name: LowerBarButtonOnAction.java
 * Function: Event that is called when the lower bar button is pressed
 *    it will hide or show the internal stage, toggling the between the
 *    two states
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
    
    if(internalStageController.isHided()){
      internalStageController.revealStage();
      internalStageController.setHided(false);
      return;
    }
    internalStageController.hideStage();
    internalStageController.setHided(true);
  }
}
