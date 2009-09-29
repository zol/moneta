package com.jaz.moneta.event;

import java.util.Hashtable;

public class MktDepthEvent extends MktInfoEvent 
{
  public MktDepthEvent(Integer id, Hashtable fields) {
    super(id, fields);
  }
}