/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/07/2021 12:20
 * Last update: 
 * Name: 
 * Function: 
 *******************************************************************/

package view.desktop.components.lowerbar.components;

import javafx.scene.control.Button;
import view.desktop.components.lowerbar.LowerBar;

public class LowerBarInitButton extends Button{
  public LowerBarInitButton() {
    super("");

    // config to size
    super.setPrefSize(LowerBar.LOWER_BAR_HEIGHT, LowerBar.LOWER_BAR_HEIGHT);
  }
}
