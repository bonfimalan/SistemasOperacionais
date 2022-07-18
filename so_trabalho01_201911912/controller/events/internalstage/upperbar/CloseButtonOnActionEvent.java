/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 18/07/2021 11:35
 * Last update: 
 * Name: 
 * Function: 
 *******************************************************************/
package controller.events.internalstage.upperbar;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.ApplicationInfo;

public class CloseButtonOnActionEvent implements EventHandler<MouseEvent> {
  private ApplicationInfo info;

  public CloseButtonOnActionEvent(ApplicationInfo info) {
    this.info = info;
  }

  @Override
  public void handle(MouseEvent event) {
    info.onClose();
    info.getController().close();
    info.setOpened(false);
  }
  
}
