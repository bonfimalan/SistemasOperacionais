/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/07/2021 15:33
 * Last update: 26/07/2021 15:29
 * Name: WorkAreaController.java
 * Function: Controller to the work area
*******************************************************************/
package controller;

import controller.events.workarea.WorkAreaIconOnMouseClicked;
import javafx.scene.Node;
import model.ApplicationInfo;
import view.desktop.components.workarea.WorkArea;
import view.desktop.components.workarea.components.WorkAreaIcon;
import view.internalstage.InternalStage;

public class WorkAreaController {
  private WorkArea workArea;
  private double movingIconPreviousX;
  private double movingIconPreviousY;
  private double previousWidth;
  private double previousHeight;

  @Deprecated
  private boolean[][] gridSpaces;

  private InternalStage stageOnTop = null;

  public static final int GRID_AREA_SIZE = 100;

  public WorkAreaController(WorkArea workArea, int xGridSize, int yGridSize) {
    xGridSize /= 100;
    yGridSize /= 100;
    
    this.workArea = workArea;
    /*
    gridSpaces = new boolean[xGridSize][yGridSize];
    for (boolean[] line : gridSpaces)
      for (int i = 0; i < line.length; i++)
        line[i] = false;
    */
  }

  public void onTopStage(InternalStage internalStage) {
    if (internalStage == stageOnTop)
      return;
    stageOnTop = internalStage;
    workArea.onTopStage(internalStage);
  }

  public void resetIconPosition(WorkAreaIcon icon) {
    icon.setLayoutX(movingIconPreviousX);
    icon.setLayoutY(movingIconPreviousY);
  }

  public void addWorkAreaIcon(ApplicationInfo info) {
    WorkAreaIcon icon = new WorkAreaIcon(info.getImagePath(), info.getName());

    // calculate the position to the icon
    setIconPosition(icon);

    icon.setOnMouseClicked(new WorkAreaIconOnMouseClicked(info));

    workArea.addWorkAreaIcon(icon);
  }

  @Deprecated
  private void setIconPosition(WorkAreaIcon icon) {
    int gridLineAmount = (int) workArea.getWidth() / 100;
    int gridColumnAmount = (int) workArea.getHeight() / 100;

    // moving by line
    // i = line positon
    for (int i = 0; i < gridLineAmount; i++) {
      // moving by column
      // j = column position
      for (int j = 0; j < gridColumnAmount; j++) {
        if (!isGridAreaOccupied(i, j)) {
          icon.setLayoutX(i * GRID_AREA_SIZE);
          icon.setLayoutY(j * GRID_AREA_SIZE);
          // occupyGridArea(i, j);
          return;
        }
      }
    }
  }

  public void savePreviousSize() {
    this.previousWidth = workArea.getWidth();
    this.previousHeight = workArea.getHeight();
  }

  public void removeStage(InternalStage internalStage) {
    workArea.getChildren().remove(internalStage);
  }

  public void addInternalStage(ApplicationInfo info) {
    InternalStage stage = new InternalStage(info);

    InternalStageController controller = new InternalStageController(stage, info);
    info.setController(controller);

    workArea.addInternalStage(controller.getInternalStage());
  }

  public boolean isGridAreaOccupied(int x, int y) {
    int xPosition = x * 100;
    int yPosition = y * 100;
    for (Node child : workArea.getChildren()) {
      if(child instanceof WorkAreaIcon && child.getLayoutX() == xPosition && child.getLayoutY() == yPosition) 
        return true;
    }

    return false;
  }

  @Deprecated
  public boolean isGridAreaOccupied(int x, int y, boolean a) {
    return gridSpaces[x][y];
  }

  @Deprecated
  public void occupyGridArea(int x, int y) {
    gridSpaces[x][y] = true;
  }

  @Deprecated
  public void freeGridArea(int x, int y) {
    gridSpaces[x][y] = false;
  }

  public void onIconPressed() {
    this.previousWidth = workArea.getWidth();
    this.previousHeight = workArea.getHeight();
  }

  public void resetWorkAreaSize() {
    workArea.setPrefSize(previousWidth, previousHeight);
  }

  public WorkArea getWorkArea() {
    return workArea;
  }

  public double getMovingIconPreviousX() {
    return movingIconPreviousX;
  }

  public void setMovingIconPreviousX(double movingIconPreviousX) {
    this.movingIconPreviousX = movingIconPreviousX;
  }

  public double getMovingIconPreviousY() {
    return movingIconPreviousY;
  }

  public void setMovingIconPreviousY(double movingIconPreviousY) {
    this.movingIconPreviousY = movingIconPreviousY;
  }

  public double getPreviousWidth() {
    return previousWidth;
  }

  public void setPreviousWidth(double previousWidth) {
    this.previousWidth = previousWidth;
  }

  public double getPreviousHeight() {
    return previousHeight;
  }

  public void setPreviousHeight(double previousHeigth) {
    this.previousHeight = previousHeigth;
  }
}
