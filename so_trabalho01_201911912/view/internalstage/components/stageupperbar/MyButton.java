package view.internalstage.components.stageupperbar;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class MyButton extends StackPane {
  private ImageView imageView;

  public MyButton(String imagePath) {
    super.setPrefSize(40, StageUpperBar.UPPER_BAR_HEIGHT);
    imageView = new ImageView();
    imageView.setImage(
      new Image(imagePath , StageUpperBar.UPPER_BAR_HEIGHT - 1 , StageUpperBar.UPPER_BAR_HEIGHT - 1, false, false)
    );

    super.setAlignment(Pos.CENTER);

    super.getChildren().addAll(imageView);
  }
}
