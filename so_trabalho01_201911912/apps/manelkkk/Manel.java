package apps.manelkkk;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.ApplicationInfo;

public class Manel extends ApplicationInfo{
  public Manel() {
    super.setName("Manel kkkkkk");
    super.setImagePath("/resources/images/manel.jpeg");
    super.setResizable(true);

    VBox vBox = new VBox();
    vBox.setAlignment(Pos.CENTER);
    vBox.getChildren().addAll(
      new ImageView(new Image("/resources/images/manel.jpeg")), new Label("Olha a carinha dele kkkk"));
    super.setParent(vBox);
  }

  @Override
  public void onClose() {
    
  }
  
}
