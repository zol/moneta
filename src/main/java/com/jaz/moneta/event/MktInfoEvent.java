package com.jaz.moneta.event;

import java.util.Hashtable;

public class MktInfoEvent {
  protected Integer id;
  protected Hashtable<String, String> fields;

  public MktInfoEvent(Integer id, Hashtable<String, String> fields) { 
    this.id = id;
    this.fields = fields;
  }

  public Hashtable<String, String> getFields() {
    return fields;
  }
  
  public int getId() {
    return id;
  }
  
  public String toString() {
    String s = getClass().getSimpleName() + " id:" + id;  
    
    for (String key : fields.keySet()) {
      s += " " + key + "=" + fields.get(key);
    }
    
    return s;  
  }
}