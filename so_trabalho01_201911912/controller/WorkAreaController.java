package controller;

import view.desktop.components.workarea.WorkArea;
import view.desktop.components.workarea.components.WorkAreaIcon;

public class WorkAreaController {
  private WorkArea workArea;
  private double movingIconPreviousX;
  private double movingIconPreviousY;
  private double previousWidth;
  private double previousHeight;
  private boolean[][] gridSpaces;

  public WorkAreaController(WorkArea workArea, int xGridSize, int yGridSize) {
    xGridSize /= 100;
    yGridSize /= 100;

    this.workArea = workArea;
    gridSpaces = new boolean[xGridSize][yGridSize];
    for(boolean[] line : gridSpaces)
      for(int i = 0; i < line.length; i++)
        line[i] = true;
  }

  public void resetIconPosition(WorkAreaIcon icon) {
    icon.setLayoutX(movingIconPreviousX);
    icon.setLayoutY(movingIconPreviousY);
  }

  public void addWorkAreaIcon(String imagePath, String iconName) {
    WorkAreaIcon icon = new WorkAreaIcon(imagePath, iconName);
    // TODO calcular posicao para adicionar os icones
    workArea.addWorkAreaIcon(icon);
  }

  public boolean isGridAreaOccupied(int x, int y) {
    return gridSpaces[x][y];
  }

  public void occupyGridArea(int x, int y) {
    gridSpaces[x][y] = false;
  }

  public void freeGridArea(int x, int y) {
    gridSpaces[x][y] = true;
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
