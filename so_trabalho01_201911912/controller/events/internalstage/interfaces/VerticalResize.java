package controller.events.internalstage.interfaces;

import javafx.scene.input.MouseEvent;
import view.internalstage.InternalStage;

public interface VerticalResize {
  void veticalResize(InternalStage internalStage, MouseEvent mouseEvent);
}
