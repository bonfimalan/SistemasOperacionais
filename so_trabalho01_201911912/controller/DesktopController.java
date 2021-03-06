/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 18/07/2021 14:14
 * Last update: 22/07/2021 21:05
 * Name: DesktopController.java
 * Function: Controlls the actions in the Desktop view
 *******************************************************************/
package controller;

import global.Controllers;
import javafx.application.Platform;
import model.ApplicationInfo;

public class DesktopController {
  public DesktopController() { }

  public void addApp(ApplicationInfo info) {
    Platform.runLater(() -> Controllers.workAreaController.addWorkAreaIcon(info));
  }

  public void addAppWithStageOpen(ApplicationInfo info) {
    Controllers.workAreaController.addInternalStage(info);
    Controllers.lowerBarController.addApplicationButton(info);
    Platform.runLater(() -> Controllers.workAreaController.addWorkAreaIcon(info));
  }
}
