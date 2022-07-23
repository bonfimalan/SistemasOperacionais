/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 17/07/2021 19:41
 * Last update: 22/07/2021 20:30
 * Name: LowerBarController.java
 * Function: A controller to the lower bar
 *******************************************************************/
package controller;

import controller.events.lowerbar.LowerBarButtonOnAction;
import model.ApplicationInfo;
import view.desktop.components.lowerbar.LowerBar;
import view.desktop.components.lowerbar.components.LowerBarApplicationButton;

public class LowerBarController {
  private LowerBar lowerBar;

  public LowerBarController(LowerBar lowerBar) {
    this.lowerBar = lowerBar;
  }

  public void addApplicationButton(ApplicationInfo info) {
    LowerBarApplicationButton button = new LowerBarApplicationButton(info.getImagePath(), info.getName());
    
    // adding event on click
    button.setOnMouseClicked(new LowerBarButtonOnAction(info.getController()));
    // adding button to controller, so it will be deleted when close the application
    info.getController().setLowerBarButton(button);
    lowerBar.addApplicationButton(button);
  }

  public void deleteApplicationButton(LowerBarApplicationButton lowerBarButton) {
    lowerBar.deleteApplicationButton(lowerBarButton);
  }
}
