/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 17/07/2021 13:14
 * Last update: 22/07/2021 20:37
 * Name: ResizeOnMousePressed.java
 * Function: Handler to the event that occurs when the mouse is pressed
 *    this is used to difine what resize type will be used, depending
 *    on the cursor mouse stored in the controller of the internal stage
 *******************************************************************/
package controller.events.internalstage;

import controller.InternalStageController;
import controller.events.internalstage.interfaces.HorizontalRisize;
import controller.events.internalstage.interfaces.VerticalResize;
import global.Controllers;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ResizeOnMousePressed implements EventHandler<MouseEvent> {
  private InternalStageController internalStageController;

  private double previousX;
  private double previousY;

  private double previousWidth;
  private double previousHeight;

  private boolean oneTime = true;

  public ResizeOnMousePressed(InternalStageController internalStageController) {
    this.internalStageController = internalStageController;
  }

  @Override
  public void handle(MouseEvent event) {
    Controllers.workAreaController.savePreviousSize();

    // get the position where the mouse was pressed
    previousX = event.getSceneX();
    previousY = event.getSceneY();

    // used to calculate the new size
    previousWidth = internalStageController.getInternalStage().getWidth();
    previousHeight = internalStageController.getInternalStage().getHeight();

    // it will get the current size of the Internal Stage, so it can be used
    // in the resize events
    if(oneTime) {
      internalStageController.setRealMinWidth(previousWidth);
      internalStageController.setRealMinHeight(previousHeight);
      oneTime = false;
    }

    // takes the cursor type to difine what resize function will be added to the
    // internal stage node
    switch (internalStageController.getResizeCursor().toString()) {
      case "N_RESIZE":
        addEventOnStage(nonImplementedHorizontalResize(), northResize());
        break;
      case "S_RESIZE":
        addEventOnStage(nonImplementedHorizontalResize(), southResize());
        break;
      case "E_RESIZE":
        addEventOnStage(eastResize(), nonImplementedVeticalResize());
        break;
      case "W_RESIZE":
        addEventOnStage(westResize(), nonImplementedVeticalResize());
        break;
      case "NE_RESIZE":
        addEventOnStage(eastResize(), northResize());
        break;
      case "NW_RESIZE":
        addEventOnStage(westResize(), northResize());
        break;
      case "SE_RESIZE":
        addEventOnStage(eastResize(), southResize());
        break;
      case "SW_RESIZE":
        addEventOnStage(westResize(), southResize());
        break;
    }
  }

  private HorizontalRisize eastResize() {
    return (internalStage, mouseEvent) -> {
      internalStage.setPrefWidth(mouseEvent.getX());
    };
  }

  private VerticalResize southResize() {
    return (internalStage, mouseEvent) -> {
      internalStage.setPrefHeight(mouseEvent.getY());
    };
  }

  private HorizontalRisize westResize() {
    return (internalStage, mouseEvent) -> {
      // This just works, don't ask me why
      // there's some visual bug, we just have to live with this kind of stuff
      // this condition stop the resize, preventing the stage to start moving
      if(internalStageController.getRealMinWidth() < internalStage.getWidth() || previousX - mouseEvent.getSceneX() > 0) {
        internalStage.setLayoutX(mouseEvent.getSceneX());
        internalStage.setPrefWidth(previousWidth + previousX - mouseEvent.getSceneX());
      }
    };
  }

  private VerticalResize northResize() {
    return (internalStage, mouseEvent) -> {
      if(internalStageController.getRealMinHeight() < internalStage.getHeight() || previousY - mouseEvent.getSceneY() > 0) {
        // I had spend 1 hour in this
        internalStage.setLayoutY(mouseEvent.getSceneY());
        internalStage.setPrefHeight(previousHeight + previousY - mouseEvent.getSceneY());
      }
    };
  }

  private HorizontalRisize nonImplementedHorizontalResize() {
    return (a, b) -> {};
  }

  private VerticalResize nonImplementedVeticalResize() {
    return (a, b) -> {};
  }

  private void addEventOnStage(HorizontalRisize horizontalRisize, VerticalResize verticalResize) {
    internalStageController.getInternalStage().setOnMouseDragged(
      new ResizeOnMouseDragged<HorizontalRisize,VerticalResize>(internalStageController, horizontalRisize, verticalResize)
    );
  }

}
