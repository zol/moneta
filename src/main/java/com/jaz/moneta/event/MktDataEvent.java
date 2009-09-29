package com.jaz.moneta.event;

import java.util.Hashtable;

public class MktDataEvent extends MktInfoEvent 
{
  public MktDataEvent(Integer id, Hashtable fields) {
    super(id, fields);
  }
}