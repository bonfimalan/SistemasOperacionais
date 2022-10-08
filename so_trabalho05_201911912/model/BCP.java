/********************************************************************
* Author: Alan Bonfim Santos
* Registration: 201911912
* Initial date: 16/09/22 18:50
* Last update: 17/09/22 19:52
* Name: BCP.java
* Function:
*******************************************************************/
package model;

public class BCP {
  private int id;
  private int size;
  private int partition;
  private static int cont;

  public BCP(int size, int partition) {
    this.size = size;
    this.partition = partition;
    this.id = cont;
    cont++;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public int getPartition() {
    return partition;
  }

  public void setPartition(int partition) {
    this.partition = partition;
  }

  public int getId() {
    return id;
  }

  public static void setCont(int i) {
    cont = i;
  }
}
