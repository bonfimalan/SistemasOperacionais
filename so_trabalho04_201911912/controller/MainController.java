/********************************************************************
* Author: Alan Bonfim Santos
* Registration: 201911912
* Initial date: 16/09/22 19:21
* Last update: 18/09/22 00:30
* Name: MainController.java
* Function:
*******************************************************************/
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import algorithms.AbsoluteStaticPartitionedAllocation;
import algorithms.RelocatableStaticPartitionedAllocation;
import algorithms.SimpleContiguousAllocation;
import algorithms.interfaces.MemoryAlgInterface;
import controller.interfaces.MainControllerInterface;
import global.Variables;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.BCP;
import view.PartitionConfigView;

public class MainController implements MainControllerInterface, Initializable {
  @FXML
  private Button addProcessButton;

  @FXML
  private ComboBox<String> alocationAlgComboBox;

  @FXML
  private HBox mainContainer;

  @FXML
  private TextField processSizeTextField;

  @FXML
  private Label partitionChoseLabel;

  @FXML
  private ComboBox<String> partitionChoseComboBox;

  /*
   * When there's no space in memory the process must be shown here
   */
  @FXML
  private VBox processWaitArea;

  /*
   * An container that is treated like it is the memory
   */
  @FXML
  private AnchorPane memory;

  /*
   * The container with the partition quantity chose option
   */
  @FXML
  private HBox partitionQuantityContainer;

  @FXML
  private TextField partitionQuantityTextField;

  /*
   * PartitionConfigView must be added here
   * and then the PartitionInfo
   */
  @FXML
  private VBox partitionConfigArea;

  @FXML
  private TextField memorySizeTextField;

  @FXML
  private Button applyMemorySizeButton;

  @FXML
  private Button confirmPartitionsButton;

  @FXML
  private HBox memorySizeContainer;

  @FXML
  private Label warningLabel;

  private Thread warningTimer;

  private MemoryAlgInterface memoryManager;
  private List<PartitionConfigView> partitionConfigList;
  private Button partitionSetButton;

  private boolean isReseted;
  private boolean isOtherAlg;
  private char algType;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    mainContainer.setVisible(!mainContainer.isVisible());

    alocationAlgComboBox.getItems().addAll(Variables.ALOCATION_ALGS);
    this.alocationAlgComboBox.getSelectionModel().select(0);

    this.partitionSetButton = new Button("SET");

    this.isReseted = true;

    // when a new alg is chose it will set the value of isOtherAlg, so the apply
    // action
    // will reset the simulation
    alocationAlgComboBox.setOnAction(actionEvent -> {
      char algChose = alocationAlgComboBox.getSelectionModel().getSelectedItem().charAt(0);
      this.isOtherAlg = algChose != algType;
    });
  }

  /*
   * Adds a process to the memory ou to the processWaitArea
   */
  @FXML
  void addProcess(ActionEvent event) {
    int size = Integer.valueOf(processSizeTextField.getText());
    if (size <= 0) {
      addWarning("Size must be bigger than 0");
      return;
    }
    int partition = algType == '2' ? Integer.valueOf(partitionChoseComboBox.getSelectionModel().getSelectedItem()) : 0;
    BCP process = new BCP(size, partition);
    memoryManager.addProcess(process);
  }

  /*
   * Applys the chose alg and reset the simulation
   * 
   * Hides or shows the partitionQuantityContainer, denpending on the
   * alg
   * 
   * Hides the partitionChoseLabel and partitionChoseComboBox denpending
   * on the alg chose
   */
  @FXML
  void applyAlg(ActionEvent event) {
    if (this.isOtherAlg)
      reset();

    if (!isReseted)
      return;

    isReseted = false;

    mainContainer.setVisible(!mainContainer.isVisible());

    BCP.setCont(0);
    algType = alocationAlgComboBox.getSelectionModel().getSelectedItem().charAt(0);

    switch (algType) {
      case '1': // Simple Contiguous Allocation
        memoryManager = new AbsoluteStaticPartitionedAllocation(memory, processWaitArea, this);
        addProcessButton.setDisable(false);
        setVisibleComboBoxPartitionAndComboBox(false);
        partitionQuantityContainer.setVisible(false);
        memorySizeContainer.setVisible(false);
        break;
      case '2': // Absolute Static Partitioned Allocation
        memoryManager = new SimpleContiguousAllocation(memory, processWaitArea, this);
        addProcessButton.setDisable(true);
        setVisibleComboBoxPartitionAndComboBox(true);
        partitionQuantityContainer.setVisible(true);
        memorySizeContainer.setVisible(false);
        break;
      case '3': // Relocatable Static Partitioned Allocation
        memoryManager = new RelocatableStaticPartitionedAllocation(memory, processWaitArea, this);
        addProcessButton.setDisable(true);
        setVisibleComboBoxPartitionAndComboBox(false);
        partitionQuantityContainer.setVisible(true);
        memorySizeContainer.setVisible(false);
        break;
    }
  }

  private void setVisibleComboBoxPartitionAndComboBox(boolean value) {
    partitionChoseLabel.setVisible(value);
    partitionChoseComboBox.setVisible(value);
  }

  /*
   * Takes the number passed in the partitionQuantityTextField and adds
   * this amount in the partitionConfigArea
   * 
   * Than adds a button that reset the simulation when pressed, adds all the
   * PartitionInfoView and activates the addProcessButton
   * 
   * Depending on the alg chosed it will add the number of partitions in the
   * partitionChoseComboBox
   */
  @FXML
  void confirmPartitions(ActionEvent event) {
    try {
      int partitionAmount = Integer.valueOf(partitionQuantityTextField.getText());
      if(partitionAmount <= 0) return;

      partitionConfigList = new ArrayList<>();
      partitionConfigArea.getChildren().clear();
      for (int i = 0; i < partitionAmount; i++) {
        PartitionConfigView configView = new PartitionConfigView(i);
        partitionConfigList.add(configView);
        partitionConfigArea.getChildren().add(configView);
        partitionChoseComboBox.getItems().add(String.valueOf(i));
      }
      // partition set button config
      partitionSetButton = new Button("SET");
      partitionSetButton.setOnAction(
          (actionEvent) -> partitionsButtonActionEventHandler());

      partitionConfigArea.getChildren().add(partitionSetButton);

      confirmPartitionsButton.setDisable(true);
    } catch (Exception e) {
    }
  }

  public void partitionsButtonActionEventHandler() {
    int memorySize = 0;
    int partitionSizePointer;
    int[] partitionSizes = new int[partitionConfigList.size()];

    for (int i = 0; i < partitionConfigList.size(); i++) {
      partitionSizePointer = partitionConfigList.get(i).getPartitionSize();
      partitionSizes[i] = partitionSizePointer;
      memorySize += partitionSizePointer;
      if(partitionSizePointer <= 0) return;
    }

    for (PartitionConfigView p : partitionConfigList) { p.disablePartiotionTextArea(); }
    memoryManager.setMemorySize(memorySize);
    memoryManager.partitionAlgConfig(partitionConfigList, partitionSizes);

    
    partitionChoseComboBox.getSelectionModel().select(0);
    
    partitionSetButton.setDisable(true);

    addProcessButton.setDisable(false);
  }

  /*
   * Decrements the number in the processSizeTextField
   */
  @FXML
  void processSizeDown(ActionEvent event) {
    try {
      int value = Integer.valueOf(processSizeTextField.getText());
      value--;
      processSizeTextField.setText(String.valueOf(value));
    } catch (Exception e) {
    }
  }

  /*
   * Increments the number in the processSizeTextField
   */
  @FXML
  void processSizeUp(ActionEvent event) {
    try {
      int value = Integer.valueOf(processSizeTextField.getText());
      value++;
      processSizeTextField.setText(String.valueOf(value));
    } catch (Exception e) {
    }
  }

  /*
   * Reset the simulation when the button is pressed
   */
  @FXML
  void reset(ActionEvent event) {
    this.reset();
  }

  @FXML
  void applyMemorySize(ActionEvent event) {
    try {
      memoryManager.setMemorySize(Integer.valueOf(memorySizeTextField.getText()));
    } catch (Exception e) {
    }
  }

  /*
   * Reset the simulation
   */
  @Override
  public void reset() {
    // it's already reseted
    if (isReseted)
      return;

    // hides the main container
    mainContainer.setVisible(!mainContainer.isVisible());

    // clear the three areas
    processWaitArea.getChildren().clear();
    memory.getChildren().clear();
    partitionConfigArea.getChildren().clear();

    partitionChoseComboBox.getItems().clear();

    confirmPartitionsButton.setDisable(false);

    isReseted = true;
  }

  /*
   * Finds a process in the memory and remove it
   * if the memory supports only one process it
   * will be removed
   */
  @Override
  public void removeProcess(int id) {
    memoryManager.removeProcess(id);
  }

  @Override
  public void removeProcessFromWait(int id) {
    memoryManager.removeFromWaitView(id);
  }

  @Override
  public void addWarning(String warningMessage) {
    warningLabel.setText(warningMessage);

    warningTimer = new WarningTimer();
    warningTimer.start();
  }

  private class WarningTimer extends Thread {
    @Override
    public void run() {
      try {
        Thread.sleep(2000);
        Platform.runLater(() -> warningLabel.setText(""));
      } catch (InterruptedException e) {
      }
    }
  }
}
