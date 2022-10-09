package algorithms.interfaces;

import algorithms.exceptions.ProcessTooBigException;
import algorithms.util.MemoryUtil;
import model.BCP;

public interface MemoryLinkedListInterface {
  public boolean addToBiggestFreeSpace(BCP process, int memorySize) throws ProcessTooBigException;

  public boolean addToSmallestFreeSpaceThatFits(BCP process, int memorySize) throws ProcessTooBigException;

  public boolean addToFirstFreeMemorySpaceThatFits(BCP process, int memorySize) throws ProcessTooBigException;

  public void removeProcessFromMemory(int id);

  public class MemoryChunk {
    private MemoryChunk previous;
    private BCP node;
    private int size;
    private int startPointerInGui;
    private int id;
    private static int cont;

    private MemoryChunk next;

    public MemoryChunk(MemoryChunk previous, BCP node, MemoryChunk next) {
      this.previous = previous;
      this.node = node;
      this.next = next;
      this.id = cont;
      cont++;
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
      this.startPointerInGui = startPointerInGui * MemoryUtil.SIZE_MULTIPLYER;
    }

    public int getId() {
      return id;
    }
  }
}