/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 17/07/2021 13:23
 * Last update: 22/07/2021 21:21
 * Name: VerticalResize.java
 * Function: Interface with the method that is called when the drag
 *    event occurs in the internal stage, to resize the internal stage
*******************************************************************/
package controller.events.internalstage.interfaces;

import javafx.scene.input.MouseEvent;
import view.internalstage.InternalStage;

public interface VerticalResize {
  void veticalResize(InternalStage internalStage, MouseEvent mouseEvent);
}
