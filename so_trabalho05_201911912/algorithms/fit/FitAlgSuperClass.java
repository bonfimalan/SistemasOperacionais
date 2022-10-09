/********************************************************************
* Author: Alan Bonfim Santos
* Registration: 201911912
* Initial date: 08/10/22 21:57
* Last update: 08/10/22 22:19
* Name: FitAlgSuperClass.java
* Function:
*******************************************************************/
package algorithms.fit;

import java.util.ArrayList;
import java.util.List;

import algorithms.util.MemoryLinkedList;
import controller.interfaces.MainControllerInterface;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.BCP;
import view.ProcessWaitView;

public class FitAlgSuperClass {
  protected MemoryLinkedList memoryList;
  protected List<BCP> waitList;
  protected int memorySize;
  protected VBox processWaitArea;
  protected MainControllerInterface controller;

  public FitAlgSuperClass(AnchorPane memory, VBox processWaitArea, MainControllerInterface controller) {
    this.processWaitArea = processWaitArea;
    this.controller = controller;
    this.memoryList = new MemoryLinkedList(memory, controller);
    this.waitList = new ArrayList<>();
  }

  public void removeFromWaitViewSuper(int id) {
    BCP process;
    for(Node node : processWaitArea.getChildren()) {
      if(node instanceof ProcessWaitView) {
        ProcessWaitView processWaitView = (ProcessWaitView) node;
        process = processWaitView.getProcess();
        if (process.getId() == id) {
          processWaitArea.getChildren().remove(node); // removes from view
          waitList.remove(process); // removes from wait list
          return;
        } // end if
      } // end if
    } // end for  
  }

  protected void addProcessToWait(BCP process) {
    ProcessWaitView waitView = new ProcessWaitView(controller, process);
    processWaitArea.getChildren().addAll(waitView);
  }
}
