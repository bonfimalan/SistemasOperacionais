/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/07/2021 18:12
 * Last update: 22/07/2021 20:54
 * Name: InternalStage.java
 * Function: A stage that is used to open JavaFX apps inside this 
 *    application
 *******************************************************************/
package view.internalstage;

import javafx.geometry.Pos;
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
    fakeScene.setAlignment(Pos.CENTER);

    super.getChildren().addAll(stageUpperBar, fakeScene);
  }

  public void setFakeSceneChild(Pane pane) {
    pane.setPrefWidth(VBox.USE_COMPUTED_SIZE);
    VBox.setVgrow(pane, Priority.ALWAYS);

    pane.setMaxSize(VBox.USE_COMPUTED_SIZE, VBox.USE_COMPUTED_SIZE);

    fakeScene.getChildren().add(pane);
  }

  public StageUpperBar getStageUpperBar() {
    return stageUpperBar;
  }
}
