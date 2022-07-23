/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 17/07/2021 13:38
 * Last update: 22/07/2021 21:17
 * Name: HorizontalResize.java
 * Function: Interface with the method that is called when the drag
 *    event occurs in the internal stage, to resize the internal stage
*******************************************************************/
package controller.events.internalstage.interfaces;

import javafx.scene.input.MouseEvent;
import view.internalstage.InternalStage;

public interface HorizontalRisize {
  void horizontalResize(InternalStage internalStage, MouseEvent mouseEvent);
}
