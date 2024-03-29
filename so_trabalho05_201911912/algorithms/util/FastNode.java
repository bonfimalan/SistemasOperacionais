/********************************************************************
* Author: Alan Bonfim Santos
* Registration: 201911912
* Initial date: 09/10/22 15:50
* Last update: 09/10/22 18:54
* Name: FastNode.java
* Function:
*******************************************************************/
package algorithms.util;

import java.util.List;

import algorithms.interfaces.MemoryLinkedListInterface.MemoryChunk;

public class FastNode {
  private int size;
  private List<MemoryChunk> list;

  public FastNode(int size, List<MemoryChunk> list) {
    this.size = size;
    this.list = list;
  }

  public int getSize() {
    return size;
  }

  public List<MemoryChunk> getSet() {
    return list;
  }

  public void setList(List<MemoryChunk> list) {
    this.list = list;
  }

  public boolean hasChunks() {
    return !list.isEmpty();
  }

  public MemoryChunk getChunk() {
    return list.get(0);
  }
}
