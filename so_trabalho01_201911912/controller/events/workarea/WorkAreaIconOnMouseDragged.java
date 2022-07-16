/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/07/2021 15:03
 * Last update: 
 * Name: 
 * Function: 
 *******************************************************************/
package controller.events.workarea;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import view.desktop.components.workarea.components.WorkAreaIcon;

public class WorkAreaIconOnMouseDragged implements EventHandler<MouseEvent>  {
  private WorkAreaIcon icon;
  public static final int OFFSET = 50;

  public WorkAreaIconOnMouseDragged(WorkAreaIcon icon) {
    this.icon = icon;
  }

  @Override
  public void handle(MouseEvent event) {
    icon.setLayoutX(event.getSceneX() - OFFSET);
    icon.setLayoutY(event.getSceneY() - OFFSET);
  }
}