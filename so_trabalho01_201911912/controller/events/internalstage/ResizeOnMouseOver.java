/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 17/07/2021 12:19
 * Last update: 
 * Name: 
 * Function: 
 *******************************************************************/
package controller.events.internalstage;

import controller.InternalStageController;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import view.internalstage.InternalStage;

public class ResizeOnMouseOver implements EventHandler<MouseEvent> {
  private InternalStage internalStage;
  private InternalStageController internalStageController;

  public ResizeOnMouseOver(InternalStageController internalStageController) {
    this.internalStageController = internalStageController;
    this.internalStage = internalStageController.getInternalStage();
  }

  @Override
  public void handle(MouseEvent event) {
    double mouseX = event.getX();
    double mouseY = event.getY();
    final int PADDING_SIZE = InternalStageController.PADDING_SIZE;

    // shiiii, you didn't see anything here
    // NW
    if (mouseX <= PADDING_SIZE && mouseY <= PADDING_SIZE) {
      setCursor(Cursor.NW_RESIZE);
      return;
    }
    // SW
    if (mouseX <= PADDING_SIZE && mouseY >= internalStage.getHeight() - PADDING_SIZE) {
      setCursor(Cursor.SW_RESIZE);
      return;
    }
    // NE
    if (mouseX >= (internalStage.getWidth() - PADDING_SIZE) && mouseY <= PADDING_SIZE) {
      setCursor(Cursor.NE_RESIZE);
      return;
    }
    // SE
    if (mouseX >= (internalStage.getWidth() - PADDING_SIZE) && mouseY >= internalStage.getHeight() - PADDING_SIZE) {
      setCursor(Cursor.SE_RESIZE);
      return;
    }

    // N
    if (mouseY <= PADDING_SIZE) {
      setCursor(Cursor.N_RESIZE);
      return;
    }
    // S
    if (mouseY >= internalStage.getHeight() - PADDING_SIZE) {
      setCursor(Cursor.S_RESIZE);
      return;
    }
    // E
    if (mouseX >= (internalStage.getWidth() - PADDING_SIZE)) {
      setCursor(Cursor.E_RESIZE);
      return;
    }
    // W
    if (mouseX <= PADDING_SIZE) {
      setCursor(Cursor.W_RESIZE);
      return;
    }

    setCursor(Cursor.DEFAULT);
  }

  private void setCursor(Cursor cursor) {
    internalStageController.setResizeCursor(cursor);
    internalStage.setCursor(cursor);
  }

}
