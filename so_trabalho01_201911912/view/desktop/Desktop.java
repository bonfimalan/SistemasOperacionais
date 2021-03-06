/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/07/2021 11:56
 * Last update: 22/07/2021 20:19
 * Name: Desktop.java
 * Function: Creates the desktop view, with a lower bar and a work area
 *******************************************************************/
package view.desktop;

import javafx.scene.layout.AnchorPane;
import view.desktop.components.lowerbar.LowerBar;
import view.desktop.components.workarea.WorkArea;

public class Desktop extends AnchorPane {
  private LowerBar lowerBar;
  private WorkArea workArea;

  public Desktop(double width, double height) {
    // difining sizes
    super.setPrefHeight(height);
    super.setPrefWidth(width);

    lowerBar = new LowerBar();
    workArea = new WorkArea();

    super.getChildren().addAll(workArea, lowerBar);
  }

  public LowerBar getLowerBar() {
    return lowerBar;
  }

  public WorkArea getWorkArea() {
    return workArea;
  }
}
