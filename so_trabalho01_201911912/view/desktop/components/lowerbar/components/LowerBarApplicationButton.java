package view.desktop.components.lowerbar.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class LowerBarApplicationButton extends HBox {
  private ImageView applicationIcon;
  private Label applicationLabel;

  public LowerBarApplicationButton(String imagePath, String applicationName) {
    // creating hbox with 2 pixels of spacing
    super(2);

    // all children will be in the center position
    super.setAlignment(Pos.CENTER);

    applicationIcon = new ImageView(
      new Image(imagePath, 30, 30, false, false)
    );
    applicationLabel = new Label(applicationName);

    super.getChildren().addAll(applicationIcon, applicationLabel);
  }
}
