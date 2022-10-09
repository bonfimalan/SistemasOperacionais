/********************************************************************
* Author: Alan Bonfim Santos
* Registration: 201911912
* Initial date: 08/10/22 15:07
* Last update: 09/10/22 15:38
* Name: MemoryLinkedListInterface.java
* Function:
*******************************************************************/
package algorithms.interfaces;

import algorithms.exceptions.ProcessTooBigException;
import model.BCP;

public interface MemoryLinkedListInterface {
  public boolean addToBiggestFreeSpace(BCP process, int memorySize) throws ProcessTooBigException;

  public boolean addToSmallestFreeSpaceThatFits(BCP process, int memorySize) throws ProcessTooBigException;

  public boolean addToFirstFreeMemorySpaceThatFits(BCP process, int memorySize) throws ProcessTooBigException;

  public boolean addQuickFreeMemorySpaceThatFits(BCP process, int memorySize, MemoryChunk freespace) throws ProcessTooBigException;

  public void removeProcessFromMemory(int id, int memorySize);

  public class MemoryChunk {
    private BCP node;
    private int size;
    private int startPointerInGui;
    private int id;
    private static int cont;
    
    private MemoryChunk previous;
    private MemoryChunk next;

    public MemoryChunk(MemoryChunk previous, BCP node, MemoryChunk next) {
      this.previous = previous;
      this.node = node;
      this.next = next;
      this.id = cont;
      cont++;
    }

    @Override
    public String toString() {
      return "Size: " + size + " | Start point: " + startPointerInGui + " | Id: " + id;
    }

    public boolean isFreeSpace() {
      return node == null;
    }

    public MemoryChunk getPrevious() {
      return previous;
    }

    public void setPrevious(MemoryChunk previous) {
      this.previous = previous;
    }

    public BCP getNode() {
      return node;
    }

    public void setNode(BCP node) {
      this.node = node;
    }

    public MemoryChunk getNext() {
      return next;
    }

    public void setNext(MemoryChunk next) {
      this.next = next;
    }
    public int getSize() {
      return size;
    }

    public void setSize(int size) {
      this.size = size;
    }

    public int getStartPointerInGui() {
      return startPointerInGui;
    }

    public void setStartPointerInGui(int startPointerInGui) {
      this.startPointerInGui = startPointerInGui;
    }

    public int getId() {
      return id;
    }
  }
}
