package view;

import algorithms.util.MemoryUtil;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class FreeSpaceView extends HBox {
  private Label memorySizeLabel;
  private int id;
  private int size;

  public FreeSpaceView(int id, int size) {
    super.setSpacing(2);

    super.setMinHeight(size * MemoryUtil.SIZE_MULTIPLYER);
    this.memorySizeLabel = new Label("Empty size: " + size);
    this.size = size;
    this.id = id;

    super.getChildren().addAll(this.memorySizeLabel);
  }

  public void updateSize(int newSize) {
    this.size = newSize;
    memorySizeLabel.setText("Empty size: " + newSize);
    memorySizeLabel.setMinHeight(newSize * MemoryUtil.SIZE_MULTIPLYER);
    memorySizeLabel.setMaxHeight(newSize * MemoryUtil.SIZE_MULTIPLYER);
  }

  public int getIdentifier() {
    return this.id;
  }
}
