package com.jaz.moneta.event;

import com.jaz.moneta.data.DepthTableData;

public class DepthTableEvent {
  protected Integer id;
  protected DepthTableData depthTable;

  public DepthTableEvent(Integer id, DepthTableData depthTable) { 
    this.id = id;
    this.depthTable = depthTable;
  }

  public DepthTableData getDepthTable() {
    return depthTable;
  }
  
  public int getId() {
    return id;
  }
  
  public String toString() {
    String s = getClass().getSimpleName() + " id:" + id + "\n" + 
      depthTable.toString();
      
    return s;  
  }
}