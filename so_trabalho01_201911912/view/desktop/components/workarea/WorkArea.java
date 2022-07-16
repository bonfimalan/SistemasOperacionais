/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/07/2021 12:00
 * Last update: 
 * Name: 
 * Function: 
 *******************************************************************/
package view.desktop.components.workarea;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class WorkArea extends AnchorPane {
  public WorkArea() {
    // configuring size
    VBox.setVgrow(this, Priority.ALWAYS); // height
    super.prefWidth(VBox.USE_COMPUTED_SIZE);
  }
}
