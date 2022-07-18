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
import view.desktop.components.lowerbar.LowerBar;
import view.desktop.components.workarea.components.WorkAreaIcon;
import view.internalstage.InternalStage;

public class WorkArea extends AnchorPane {
  public WorkArea() {
    // configuring size
    AnchorPane.setBottomAnchor(this, LowerBar.LOWER_BAR_HEIGHT);
    AnchorPane.setTopAnchor(this, 0.0);
    AnchorPane.setLeftAnchor(this, 0.0);
    AnchorPane.setRightAnchor(this, 0.0);
  }  

  public void addWorkAreaIcon(WorkAreaIcon icon) {
    super.getChildren().add(0, icon);
  }

  public void addInternalStage(InternalStage internalStage) {
    internalStage.setTranslateZ(1);
    super.getChildren().add(internalStage);
  }

  public void onTopStage(InternalStage internalStage) {
    super.getChildren().remove(internalStage);
    super.getChildren().add(internalStage);
  }
}
