/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 18/07/2021 11:35
 * Last update: 22/07/2021 21:05
 * Name: CloseButtonOnActionEvent.java
 * Function: Closes the internal stage when the button is pressed
 *******************************************************************/
package controller.events.internalstage.upperbar;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.ApplicationInfo;

public class CloseButtonOnActionEvent implements EventHandler<MouseEvent> {
  private ApplicationInfo info;

  public CloseButtonOnActionEvent(ApplicationInfo info) {
    this.info = info;
  }

  @Override
  public void handle(MouseEvent event) {
    // this event is only for primary button
    if(!event.getButton().equals(MouseButton.PRIMARY)) return;
    
    info.onClose();
    info.getController().close();
    info.setOpened(false);
  }
  
}
