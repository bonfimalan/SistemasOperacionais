/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/07/2021 12:04
 * Last update: 
 * Name: 
 * Function: 
 *******************************************************************/

package view.desktop.components.lowerbar.components;

import javafx.scene.control.Button;

public class LowerBarInitButton extends Button{
  public LowerBarInitButton() {
    super("Initial");

    // config to size
    super.setPrefSize(Button.USE_COMPUTED_SIZE, Button.USE_COMPUTED_SIZE);
  }
}
