/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 16/07/2021 12:18
 * Last update: 
 * Name: 
 * Function: 
 *******************************************************************/

package view.desktop.components.workarea.components;

import controller.events.workarea.WorkAreaIconOnMouseClicked;
import controller.events.workarea.WorkAreaIconOnMouseDragged;
import controller.events.workarea.WorkAreaIconOnMousePressed;
import controller.events.workarea.WorkAreaIconOnMouseReleased;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class WorkAreaIcon extends VBox {
  private ImageView iconImage;
  private Label iconLabel;

  public WorkAreaIcon(String imagePath, String iconName) {
    super.getStylesheets()
        .forEach(string -> System.out.println(string));
    super.setPrefSize(100, 100);
    super.setAlignment(Pos.TOP_CENTER);
    
    iconImage = new ImageView(new Image(imagePath, 50, 50, false, false));

    // icon config
    iconLabel = new Label(iconName);
    iconLabel.setWrapText(true);
    iconLabel.setTextAlignment(TextAlignment.CENTER);;

    //super.getChildren().addAll(iconImage, iconLabel);
    super.getChildren().addAll(iconImage, iconLabel);

    //super.setOnMousePressed(new WorkAreaIconOnMousePressed());
    super.setOnMouseDragged(new WorkAreaIconOnMouseDragged(this));
    super.setOnMouseReleased(new WorkAreaIconOnMouseReleased(this));
  }
}
