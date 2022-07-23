/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/07/2021 15:03
 * Last update: 22/07/2021 20:57
 * Name: WorkAreaIconOnMouseDragged.java
 * Function: Handler that is called when a mouse drag event occurs,
 *    this will handle the icon drag over screen
 *******************************************************************/
package controller.events.workarea;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
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
    // this event is only for primary button
    if(!event.getButton().equals(MouseButton.PRIMARY)) return;
    
    icon.setLayoutX(event.getSceneX() - OFFSET);
    icon.setLayoutY(event.getSceneY() - OFFSET);
  }
}
