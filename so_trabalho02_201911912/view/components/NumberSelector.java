package view.components;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class NumberSelector extends HBox {
  private Slider slider;
  private Label number;
  private Label unitLibel; 

  private int value;

  public NumberSelector(int minValue, int maxValue, String unitType) {
    unitLibel = new Label(unitType);
    slider = new Slider(minValue, maxValue, minValue);
    number = new Label(String.valueOf(minValue));
    slider.valueProperty().addListener(
      (o, oldValue, newValue) -> {
        value = newValue.intValue();
        number.setText(String.valueOf(value));
      });

    super.getChildren().addAll(slider, number, unitLibel);
  }

  public Slider getSlider() {
    return slider;
  }
}
