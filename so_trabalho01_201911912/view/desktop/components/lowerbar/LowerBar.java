/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/07/2021 11:58
 * Last update: 22/07/2021 20:50
 * Name: LowerBar.java
 * Function: A lower bar that is used to put app icons
 *******************************************************************/
package view.desktop.components.lowerbar;

import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import view.desktop.components.lowerbar.components.LowerBarApplicationButton;
import view.desktop.components.lowerbar.components.LowerBarInitButton;

public class LowerBar extends HBox {
  private LowerBarInitButton initButton;
  private Region separateRegion;

  public static final double LOWER_BAR_HEIGHT = 40;

  public LowerBar() {
    separateRegion = new Region();
    separateRegion.setPrefSize(2, 2);
    // difining size
    super.setPrefHeight(LOWER_BAR_HEIGHT);
    

    AnchorPane.setBottomAnchor(this, 0.0);
    AnchorPane.setLeftAnchor(this, 0.0);
    AnchorPane.setRightAnchor(this, 0.0);

    initButton = new LowerBarInitButton();
    
    //config to alignment
    super.setAlignment(Pos.CENTER_LEFT);
    super.getChildren().addAll(initButton, separateRegion);
  }

  public void addApplicationButton(LowerBarApplicationButton button) {
    super.getChildren().add(button);
  }

  public void deleteApplicationButton(LowerBarApplicationButton lowerBarButton) {
    super.getChildren().remove(lowerBarButton);
  }
}
