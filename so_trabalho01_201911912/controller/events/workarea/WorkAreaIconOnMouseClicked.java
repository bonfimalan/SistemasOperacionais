/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/07/2021 14:40
 * Last update: 22/07/2021 20:41
 * Name: WorkAreaIconOnMouseClicked.java
 * Function: Handles the event that occurs when the mouse is clecked,
 *    it will open the app, if the click count is equal to 2, ie,
 *    mosue doble clicked
 *******************************************************************/
package controller.events.workarea;

import controller.InternalStageController;
import global.Controllers;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.ApplicationInfo;

public class WorkAreaIconOnMouseClicked implements EventHandler<MouseEvent>  {
  private ApplicationInfo info;

  public WorkAreaIconOnMouseClicked(ApplicationInfo info) {
    this.info = info;
  }

  @Override
  public void handle(MouseEvent event) {
    // this event is only for primary button
    if(!event.getButton().equals(MouseButton.PRIMARY)) return;

    // double click
    if(event.getClickCount() == 2){
      if(info.isOpened()) return;

      // adds the stage to the desktop area
      Controllers.workAreaController.addInternalStage(info);
      // adds the icon to the lower bar of the desktop
      Controllers.lowerBarController.addApplicationButton(info);

      InternalStageController internalStageController = info.getController();

      internalStageController.setParent(info.getParent());

      // a app can only be opened 1 time
      info.setOpened(true);
    }
  }
  
}
