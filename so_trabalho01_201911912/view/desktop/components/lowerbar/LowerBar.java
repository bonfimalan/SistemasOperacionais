/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/07/2021 11:58
 * Last update: 
 * Name: 
 * Function: 
 *******************************************************************/
package view.desktop.components.lowerbar;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.desktop.components.lowerbar.components.LowerBarInitButton;

public class LowerBar extends HBox {
  private LowerBarInitButton initButton;

  public LowerBar() {
    // difining size
    super.setPrefHeight(40);
    super.setPrefWidth(VBox.USE_COMPUTED_SIZE);

    initButton = new LowerBarInitButton();
    
    //config to alignment
    super.setAlignment(Pos.CENTER_LEFT);
    super.getChildren().add(initButton);
  }
}
