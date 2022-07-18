/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/07/2021 18:12
 * Last update: 
 * Name: Principal.java
 * Function: the main class that loads the first Stage
 *******************************************************************/
package view.internalstage.components.stageupperbar;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class StageUpperBar extends HBox {
  private Label applicationName;
  private MyButton maxMinimizeButton;
  private MyButton hideButton;
  private CloseButton closeButton;
  private Region centerRegion;

  public static final double UPPER_BAR_HEIGHT = 20;

  public StageUpperBar(boolean resizible, String applicatioName) {
    super.setPrefSize(AnchorPane.USE_COMPUTED_SIZE, UPPER_BAR_HEIGHT);
    AnchorPane.setTopAnchor(this, 0.0);
    AnchorPane.setLeftAnchor(this, 0.0);
    AnchorPane.setRightAnchor(this, 0.0);

    applicationName = new Label(applicatioName);
    centerRegion = new Region();

    maxMinimizeButton = new MyButton("/resources/images/rest-size.png");
    hideButton = new MyButton("/resources/images/minimize.png");
    closeButton = new CloseButton("/resources/images/close-icon.png");

    HBox.setHgrow(centerRegion, Priority.ALWAYS);

    if (resizible)
      super.getChildren().addAll(applicationName, centerRegion, hideButton, maxMinimizeButton, closeButton);
    else
      super.getChildren().addAll(applicationName, centerRegion, hideButton, closeButton);
  }

  public MyButton getMaxMinimizeButton() {
    return maxMinimizeButton;
  }

  public void setMaxMinimizeButton(MyButton maxMinimizeButton) {
    this.maxMinimizeButton = maxMinimizeButton;
  }

  public MyButton getHideButton() {
    return hideButton;
  }

  public void setHideButton(MyButton hideButton) {
    this.hideButton = hideButton;
  }

  public CloseButton getCloseButton() {
    return closeButton;
  }

  public void setCloseButton(CloseButton closeButton) {
    this.closeButton = closeButton;
  }

  public Label getApplicationName() {
    return applicationName;
  }

  public void setApplicationName(Label applicationName) {
    this.applicationName = applicationName;
  }

  public Region getCenterRegion() {
    return centerRegion;
  }

  public void setCenterRegion(Region centerRegion) {
    this.centerRegion = centerRegion;
  }
}
