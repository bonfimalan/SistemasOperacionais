/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/07/2021 14:40
 * Last update: 
 * Name: 
 * Function: 
 *******************************************************************/
package controller.events.workarea;

import controller.InternalStageController;
import global.Controllers;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.ApplicationInfo;

public class WorkAreaIconOnMouseClicked implements EventHandler<MouseEvent>  {
  private ApplicationInfo info;

  public WorkAreaIconOnMouseClicked(ApplicationInfo info) {
    this.info = info;
  }

  @Override
  public void handle(MouseEvent event) {
    if(info.isOpened()) return;

    Controllers.workAreaController.addInternalStage(info);
    Controllers.lowerBarController.addApplicationButton(info);

    InternalStageController internalStageController = info.getController();

    internalStageController.setParent(info.getParent());

    info.setOpened(true);
  }
  
}
