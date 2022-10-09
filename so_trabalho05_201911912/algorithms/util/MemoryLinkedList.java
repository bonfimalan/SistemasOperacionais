package algorithms.util;

import java.util.ArrayList;
import java.util.List;

import algorithms.exceptions.ProcessTooBigException;
import algorithms.interfaces.MemoryLinkedListInterface;
import controller.interfaces.MainControllerInterface;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import model.BCP;
import view.FreeSpaceView;
import view.InMemoryProcessView;

public class MemoryLinkedList implements MemoryLinkedListInterface {
  private MemoryChunk head;
  private MemoryChunk tail;
  private AnchorPane memory;
  private MainControllerInterface controller;

  public MemoryLinkedList(AnchorPane memory, MainControllerInterface controller) {
    this.head = null;
    this.tail = null;
    this.memory = memory;
    this.controller = controller;
  }

  public BCP getFirstProcess() {
    return head.getNode();
  }

  public BCP getLastProcess() {
    return tail.getNode();
  }

  @Override
  public boolean addToBiggestFreeSpace(BCP process, int memorySize) throws ProcessTooBigException {
    if (process.getSize() > memorySize)
      throw new ProcessTooBigException();

    if (head == null) {
      addWhenMemoryEmpity(process, memorySize);

      return true;
    }

    // adds using the worst fit strategy

    // finding a free space
    MemoryChunk worstFreeSpace = null;
    int sizeDiference = 0;
    for (MemoryChunk pointer = head; pointer != null; pointer = pointer.getNext()) {
      // finds the free space with the biggest free space avaliable
      if (pointer.isFreeSpace() && process.getSize() <= pointer.getSize()
          && sizeDiference <= (pointer.getSize() - process.getSize())) {
        sizeDiference = pointer.getSize() - process.getSize();
        worstFreeSpace = pointer;
      }
    }

    // there's no free space
    if(worstFreeSpace == null) return false;

    if (sizeDiference == 0)
      addProcessWithSameSpaceToList(worstFreeSpace, process);
    else
      addProcessToList(worstFreeSpace, process);
    return true;
  }

  @Override
  public boolean addToSmallestFreeSpaceThatFits(BCP process, int memorySize) throws ProcessTooBigException {
    if (process.getSize() > memorySize)
      throw new ProcessTooBigException();

    if (head == null) {
      addWhenMemoryEmpity(process, memorySize);
      return true;
    }

    // adds using the best fit strategy

    // finding a free space
    MemoryChunk bestFreeSpace = null;
    int sizeDiference;
    for (MemoryChunk pointer = head; pointer != null; pointer = pointer.getNext()) {
      if (pointer.isFreeSpace() && process.getSize() <= pointer.getSize()) {
        bestFreeSpace = pointer;
        break;
      } // end if
    } // end for

    // there's no chunk that can holds the process
    if (bestFreeSpace == null) return false;

    // calculates the size diference
    sizeDiference = bestFreeSpace.getSize() - process.getSize();
    for (MemoryChunk pointer = head; pointer != null; pointer = pointer.getNext()) {
      if (pointer.isFreeSpace() && process.getSize() <= pointer.getSize()
          && sizeDiference > (pointer.getSize() - process.getSize())) {
        bestFreeSpace = pointer;
        sizeDiference = pointer.getSize() - process.getSize(); // updates the difference in size
        // is the best fit
        if (sizeDiference == 0) {
          addProcessWithSameSpaceToList(bestFreeSpace, process);
          return true;
        } // end if
      } // end if
    } // end for

    addProcessToList(bestFreeSpace, process);
    return true;
  }

  @Override
  public boolean addToFirstFreeMemorySpaceThatFits(BCP process, int memorySize) throws ProcessTooBigException {
    if (process.getSize() > memorySize)
      throw new ProcessTooBigException();

    if (head == null) {
      addWhenMemoryEmpity(process, memorySize);
      printList();
      return true;
    }
    
    // adds using the first fit strategy

    // finding the free space
    for (MemoryChunk pointer = head; pointer != null; pointer = pointer.getNext()) {
      // finds the first free space
      if (pointer.isFreeSpace() && process.getSize() <= pointer.getSize()) {
        if (pointer.getSize() - process.getSize() == 0)
          addProcessWithSameSpaceToList(pointer, process);
        else
          addProcessToList(pointer, process);
        printList();
        return true;
      } // end if
    } // end for

    return false; // the process was not added
  }

  @Override
  public boolean addQuickFreeMemorySpaceThatFits(BCP process, int memorySize, MemoryChunk freespace) throws ProcessTooBigException {
    if (process.getSize() > memorySize)
      throw new ProcessTooBigException();

    if (head == null) {
      addWhenMemoryEmpity(process, memorySize);
      printList();
      return true;
    }

    if(freespace == null)
      return addToFirstFreeMemorySpaceThatFits(process, memorySize);

    if(freespace != null && process.getSize() - freespace.getSize() == 0) {
      addProcessWithSameSpaceToList(freespace, process);
      return true;
    }

    if(freespace != null && process.getSize() - freespace.getSize() != 0) {
      addProcessToList(freespace, process);
      return true;
    }

    return false;
  }

  public List<MemoryChunk> getFreeSpaceSet(int size) {
    List<MemoryChunk> list = new ArrayList<>();
    for(MemoryChunk pointer = head; pointer != null; pointer = pointer.getNext()) {
      if(pointer.isFreeSpace() && pointer.getSize() == size)
        list.add(pointer);
    }

    return list;
  }

  @Override
  public void removeProcessFromMemory(int id, int memorySize) {

    // finding the process that must be removed
    MemoryChunk chunkToRemove = null;
    for (MemoryChunk pointer = head; pointer != null; pointer = pointer.getNext()) {
      if (pointer.getNode() != null && pointer.getNode().getId() == id) {
        chunkToRemove = pointer;
        break;
      }
    }

    if (chunkToRemove == null)
      return;

    MemoryChunk previousChunk = chunkToRemove.getPrevious();
    MemoryChunk nextChunk = chunkToRemove.getNext();
    chunkToRemove.setSize(chunkToRemove.getNode().getSize());

    // removes the process from GUI memory
    removeProcessFromView(id);
    chunkToRemove.setNode(null); // now it's a empty space

    // working in the next chunk first
    // if the next is not a free space it won't do anything
    if (nextChunk != null && nextChunk.isFreeSpace()) {
      chunkToRemove.setNext(nextChunk.getNext());
      if(nextChunk.getNext() != null)
        nextChunk.getNext().setPrevious(chunkToRemove);
      chunkToRemove.setSize(chunkToRemove.getSize() + nextChunk.getSize());
      setPointersToNull(nextChunk);
      // removing the next chunk that is a free space from the view
      removeFreeSpaceFromGui(nextChunk.getId());
    } // end if

    if (previousChunk != null && previousChunk.isFreeSpace()) {
      chunkToRemove.setPrevious(previousChunk.getPrevious());
      if(previousChunk.getPrevious() != null)
        previousChunk.getPrevious().setNext(chunkToRemove);

      chunkToRemove.setStartPointerInGui(previousChunk.getStartPointerInGui());
      chunkToRemove.setSize(chunkToRemove.getSize() + previousChunk.getSize());
      setPointersToNull(previousChunk);
      // removing the previous chunk, that is a free space, from view
      removeFreeSpaceFromGui(previousChunk.getId());
    } // end if

    /*
    if(head.getSize() == memorySize && head.isFreeSpace()){
      removeFreeSpaceFromGui(head.getId());
      clear();
      memory.getChildren().clear();
    } */
    
    addFreeSpaceToGui(chunkToRemove);
    printList();
  }

  @Deprecated
  private void setNewTail() {
    MemoryChunk a = null;
    for (MemoryChunk pointer = head; pointer != null; pointer = pointer.getNext()) {
      a = pointer;
    }
    tail = a;
  }

  private void printList() {
    MemoryChunk a = null;
    for (MemoryChunk pointer = head; pointer != null; pointer = pointer.getNext()) {
      System.out.print(pointer + " -> ");
      a = pointer;
    }
    System.out.println("\n(" + tail + ")");
  }

  private void setPointersToNull(MemoryChunk chunk) {
    chunk.setPrevious(null);
    chunk.setNext(null);
  }

  public void clear() {
    this.head = null;
    this.tail = null;
  }

  private void addProcessToList(MemoryChunk freeSpacePointer, BCP process) {
    // creates a new element in the linked list that poinst to the previous of the
    // free space and to the free space
    MemoryChunk newInMemoryProcess = new MemoryChunk(freeSpacePointer.getPrevious(), process, freeSpacePointer);
    newInMemoryProcess.setSize(process.getSize());
    newInMemoryProcess.setPrevious(freeSpacePointer.getPrevious());
    newInMemoryProcess.setNext(freeSpacePointer);
    // updates the next of the previous free space pointer
    freeSpacePointer.getPrevious().setNext(newInMemoryProcess);
    // the previous of the free space is now the new process
    freeSpacePointer.setPrevious(newInMemoryProcess);
    // decreases the size of the free space without removing it
    freeSpacePointer.setSize(freeSpacePointer.getSize() - process.getSize());
    // sets the starter pointer of the new process as the start pointer of the free
    // space
    newInMemoryProcess.setStartPointerInGui(freeSpacePointer.getStartPointerInGui());
    // calculates the new start pointer to the free space
    freeSpacePointer.setStartPointerInGui(freeSpacePointer.getStartPointerInGui() + newInMemoryProcess.getSize());

    // =================================================
    // stuff related to the GUI
    updateFreeSpaceInGUI(freeSpacePointer);
    addNewProcessToGui(newInMemoryProcess, controller);
    // =================================================
  }

  private void addProcessWithSameSpaceToList(MemoryChunk freeSpacePointer, BCP process) {
    freeSpacePointer.setNode(process); // the freeSpacePointer is now the new process, since it have the same size as
                                       // the partition
    // =================================================
    // stuff related to the GUI
    removeFreeSpaceFromGui(freeSpacePointer.getId());
    addNewProcessToGui(freeSpacePointer, controller);
    // =================================================
  }

  private void addWhenMemoryEmpity(BCP process, int memorySize) {
    BCP emptyBCPProcess = new BCP(0, 0); // POG (Programacao Orientada a gambiarra)
    
    MemoryChunk temp = new MemoryChunk(null, process, null);
    head = new MemoryChunk(null, emptyBCPProcess, null);
    temp.setStartPointerInGui(0);

    tail = new MemoryChunk(null, null, null);
    tail.setStartPointerInGui(process.getSize());
    tail.setSize(memorySize - process.getSize());

    // NULL <- head
    head.setPrevious(null);
    // NULL <- head -> temp
    head.setNext(temp);

    // NULL <- head <-> temp
    temp.setPrevious(head);
    // NULL <- head <-> temp -> tail
    temp.setNext(tail);

    // NULL <- head <-> temp <-> tail
    tail.setPrevious(temp);
    // NULL <- head <-> temp <-> tail -> NULL
    tail.setNext(null);

    // =================================================
    // stuff related to the GUI
    addNewProcessToGui(temp, controller);
    addFreeSpaceToGui(tail);
    // =================================================
  }

  // =====================================================
  // methods related to GUI

  public void configMemoryAreaHeigth(int size) {
    this.memory.setPrefHeight(size * MemoryUtil.SIZE_MULTIPLYER);
  }

  private void addFreeSpaceToGui(MemoryChunk freeSpace) {
    FreeSpaceView freeSpaceView = new FreeSpaceView(freeSpace.getId(), freeSpace.getSize());
    freeSpaceView.setLayoutY(freeSpace.getStartPointerInGui() * MemoryUtil.SIZE_MULTIPLYER);

    memory.getChildren().add(freeSpaceView);
  }

  private void removeFreeSpaceFromGui(int freeSpaceId) {
    for (Node child : memory.getChildren()) {
      if (child instanceof FreeSpaceView) {
        FreeSpaceView freeSpace = (FreeSpaceView) child;
        if (freeSpace.getIdentifier() == freeSpaceId) {
          memory.getChildren().remove(freeSpace);
          return;
        }
      }
    }
  }

  private void updateFreeSpaceInGUI(MemoryChunk freeSpace) {
    for (Node child : memory.getChildren()) {
      if (child instanceof FreeSpaceView) {
        FreeSpaceView freeSpaceView = (FreeSpaceView) child;
        if (freeSpaceView.getIdentifier() == freeSpace.getId()) {
          // updates the free space
          freeSpaceView.updateSize(freeSpace.getSize());
          freeSpaceView.setMinHeight(freeSpace.getSize() * MemoryUtil.SIZE_MULTIPLYER);
          freeSpaceView.setPrefHeight(freeSpace.getSize() * MemoryUtil.SIZE_MULTIPLYER);
          freeSpaceView.setMaxHeight(freeSpace.getSize() * MemoryUtil.SIZE_MULTIPLYER);
          freeSpaceView.setLayoutY(freeSpace.getStartPointerInGui() * MemoryUtil.SIZE_MULTIPLYER);
          return;
        }
      }
    }
  }

  private void addNewProcessToGui(MemoryChunk newProcess, MainControllerInterface controller) {
    InMemoryProcessView processInMemory = new InMemoryProcessView(controller, newProcess.getNode());
    processInMemory.setLayoutY(newProcess.getStartPointerInGui() * MemoryUtil.SIZE_MULTIPLYER);

    memory.getChildren().add(processInMemory);
  }

  private void removeProcessFromView(int id) {
    BCP process;
    for (Node node : memory.getChildren()) {
      if (node instanceof InMemoryProcessView) {
        InMemoryProcessView inMemoryProcessView = (InMemoryProcessView) node;
        process = inMemoryProcessView.getProcess();
        if (process.getId() == id) {
          memory.getChildren().remove(node); // removes from view
          return;
        } // end if
      } // end if
    } // end for
  }
}
