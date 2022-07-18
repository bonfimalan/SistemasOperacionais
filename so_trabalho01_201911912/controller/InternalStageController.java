/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/07/2021 20:04
 * Last update: 
 * Name: 
 * Function: 
 *******************************************************************/
package controller;

import controller.events.internalstage.InternalStageOnMouseClicked;
import controller.events.internalstage.ResizeOnMouseOver;
import controller.events.internalstage.ResizeOnMousePressed;
import controller.events.internalstage.upperbar.CloseButtonOnActionEvent;
import controller.events.internalstage.upperbar.HideButtonOnActionEvent;
import controller.events.internalstage.upperbar.MaxMinimizeButtonOnActionEvent;
import controller.events.internalstage.upperbar.OnMouseDraggedEvent;
import controller.events.internalstage.upperbar.OnMousePressedEvent;
import global.Controllers;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.ApplicationInfo;
import view.desktop.components.lowerbar.components.LowerBarApplicationButton;
import view.internalstage.InternalStage;
import view.internalstage.components.stageupperbar.StageUpperBar;

public class InternalStageController {
  private InternalStage internalStage;

  private boolean maximized = false;

  private double offSetX;
  private double offSetY;

  private double previousWidth;
  private double previousHeitght;

  private double previousLayoultX;
  private double previousLayoultY;

  private double realMinWidth;
  private double realMinHeight;

  private Cursor resizeCursor;

  // lower bar button to delete when close application
  private LowerBarApplicationButton lowerBarButton;

  public final static int PADDING_SIZE = 4;

  public InternalStageController(InternalStage internalStage, ApplicationInfo info) {
    this.internalStage = internalStage;

    // addind events to the internal stage
    internalStage.setOnMouseClicked(new InternalStageOnMouseClicked(this));
    if(info.isResizable()) {
      internalStage.setOnMouseMoved(new ResizeOnMouseOver(this));
      internalStage.setOnMousePressed(new ResizeOnMousePressed(this));
      internalStage.setOnMouseReleased((event) -> 
        internalStage.setOnMouseDragged(null)
      );
    }

    // addind events to the upper bar
    internalStage.getStageUpperBar().setOnMousePressed(new OnMousePressedEvent(this));
    internalStage.getStageUpperBar().setOnMouseDragged(new OnMouseDraggedEvent(this));
    addEventsToButtons(info);
  }
  
  private void addEventsToButtons(ApplicationInfo info) {
    StageUpperBar upperBar = internalStage.getStageUpperBar();
    upperBar.getCloseButton().setOnMouseClicked(new CloseButtonOnActionEvent(info));
    upperBar.getHideButton().setOnMouseClicked(new HideButtonOnActionEvent(this));
    upperBar.getMaxMinimizeButton().setOnMouseClicked(new MaxMinimizeButtonOnActionEvent(this));
  }

  public void close() {
    // so it can't be added again
    internalStage.setOnMouseClicked(null);
    Controllers.workAreaController.removeStage(internalStage);
    Controllers.lowerBarController.deleteApplicationButton(lowerBarButton);
  }

  public void minimize() {
    internalStage.setVisible(false);
  }

  public void maximize() {
    internalStage.setVisible(true);
  }

  public void onTop() {
    Controllers.workAreaController.onTopStage(internalStage);
  }

  public void changeSize() {
    if (maximized) {
      AnchorPane.setBottomAnchor(internalStage, null);
      AnchorPane.setTopAnchor(internalStage, null);
      AnchorPane.setLeftAnchor(internalStage, null);
      AnchorPane.setRightAnchor(internalStage, null);
      // adds the padding again
      internalStage.setPadding(new Insets(PADDING_SIZE));

      internalStage.setLayoutX(previousLayoultX);
      internalStage.setLayoutY(previousLayoultY);

      maximized = false;
      return;
    }
    // change the size of the screen to cover the dektop area
    AnchorPane.setBottomAnchor(internalStage, 0.0);
    AnchorPane.setTopAnchor(internalStage, 0.0);
    AnchorPane.setLeftAnchor(internalStage, 0.0);
    AnchorPane.setRightAnchor(internalStage, 0.0);
    // removes the padding, so it won't have a border
    internalStage.setPadding(new Insets(0));
    // stores the previous position so it can go back
    previousLayoultX = internalStage.getLayoutX();
    previousLayoultY = internalStage.getLayoutY();
    
    maximized = true;
  }

  public void savePreviousSize() {
    this.previousWidth = this.internalStage.getWidth();
    this.previousHeitght = this.internalStage.getHeight();
  }

  public void setPreviousSize(double previousWidth, double previousHeitght) {
    this.previousWidth = previousWidth;
    this.previousHeitght = previousHeitght;
  }
  
  public InternalStage getInternalStage() {
    return internalStage;
  }

  public void setParent(Pane pane) {
    internalStage.setFakeSceneChild(pane);
  }

  public double getOffSetX() {
    return offSetX;
  }

  public void setOffSetX(double offSetX) {
    this.offSetX = offSetX;
  }

  public double getOffSetY() {
    return offSetY;
  }

  public void setOffSetY(double offSetY) {
    this.offSetY = offSetY;
  }

  public Cursor getResizeCursor() {
    return resizeCursor;
  }

  public void setResizeCursor(Cursor resizeCursor) {
    this.resizeCursor = resizeCursor;
  }

  public double getPreviousHeitght() {
    return previousHeitght;
  }

  public void setPreviousHeitght(double previousHeitght) {
    this.previousHeitght = previousHeitght;
  }

  public double getPreviousWidth() {
    return previousWidth;
  }

  public void setPreviousWidth(double previousWidth) {
    this.previousWidth = previousWidth;
  }

  public LowerBarApplicationButton getLowerBarButton() {
    return lowerBarButton;
  }

  public void setLowerBarButton(LowerBarApplicationButton lowerBarButton) {
    this.lowerBarButton = lowerBarButton;
  }

  public double getRealMinWidth() {
    return realMinWidth;
  }

  public void setRealMinWidth(double realMinWidth) {
    this.realMinWidth = realMinWidth;
  }

  public double getRealMinHeight() {
    return realMinHeight;
  }

  public void setRealMinHeight(double realMinHeight) {
    this.realMinHeight = realMinHeight;
  }
}
