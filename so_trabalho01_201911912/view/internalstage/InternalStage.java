/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/07/2021 18:12
 * Last update: 
 * Name: Principal.java
 * Function: the main class that loads the first Stage
 *******************************************************************/
package view.internalstage;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.ApplicationInfo;
import view.internalstage.components.stageupperbar.StageUpperBar;

public class InternalStage extends AnchorPane {
  private StageUpperBar stageUpperBar;
  private VBox fakeScene;

  public InternalStage(ApplicationInfo info) {
    super.setPrefSize(info.getPrefWidth(), info.getPrefHeight() + StageUpperBar.UPPER_BAR_HEIGHT);

    stageUpperBar = new StageUpperBar(info.isResizable(), info.getName());

    // fake scene configs
    fakeScene = new VBox();
    AnchorPane.setTopAnchor(fakeScene, StageUpperBar.UPPER_BAR_HEIGHT);
    AnchorPane.setBottomAnchor(fakeScene, 0.0);
    AnchorPane.setLeftAnchor(fakeScene, 0.0);
    AnchorPane.setRightAnchor(fakeScene, 0.0);

    super.getChildren().addAll(stageUpperBar, fakeScene);
  }

  public void setFakeSceneChild(Pane pane) {
    pane.setPrefWidth(VBox.USE_COMPUTED_SIZE);
    VBox.setVgrow(pane, Priority.ALWAYS);

    fakeScene.getChildren().add(pane);
  }

  public StageUpperBar getStageUpperBar() {
    return stageUpperBar;
  }
}
