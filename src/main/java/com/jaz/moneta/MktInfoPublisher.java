package com.jaz.moneta;

import com.jaz.moneta.event.MktDataEvent;
import com.jaz.moneta.event.MktDepthEvent;

import com.ib.client.EWrapperMsgGenerator;
import com.ib.client.TickType;

import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.bushe.swing.event.EventBus;

/**
 * This is an EWrapper implementation and elects to receive bits of 
 * whatever data the EWrapper is recieveing. It publishes the data on the bus
 *
 */
public class MktInfoPublisher extends BaseEWrapper {
  static Logger logger = Logger.getLogger(MktInfoPublisher.class);
  
  public MktInfoPublisher()
  {        
  }
  
  ///////////////////////////////////////////////////////////////////////
  // Data logging
  ///////////////////////////////////////////////////////////////////////
  public void tickPrice( int tickerId, int field, double price, 
    int canAutoExecute) 
  {
    Hashtable f = new Hashtable();
    f.put(TickType.getField(field), String.valueOf(price));
    f.put("canAutoExecute", String.valueOf(canAutoExecute));    
    
    EventBus.publish(new MktDataEvent(tickerId, f));
  }

  public void tickSize( int tickerId, int field, int size) 
  {
    Hashtable f = new Hashtable();
    f.put(TickType.getField(field), String.valueOf(size));
    
    EventBus.publish(new MktDataEvent(tickerId, f));
  }
  
  public void tickString( int tickerId, int tickType, String value) 
  {
    Hashtable f = new Hashtable();
    f.put(TickType.getField(tickType), value);
    
    EventBus.publish(new MktDataEvent(tickerId, f));
  }

  public void tickGeneric( int tickerId, int tickType, double value) 
  {
    Hashtable f = new Hashtable();
    f.put(TickType.getField(tickType), String.valueOf(value));
    
    EventBus.publish(new MktDataEvent(tickerId, f));        
  }

  public void updateMktDepth(int tickerId, int position, int operation, 
    int side, double price, int size) 
  {
    Hashtable f = new Hashtable();
    f.put("position", String.valueOf(position));
    f.put("operation", String.valueOf(operation));
    f.put("side", String.valueOf(side));
    f.put("price", String.valueOf(price));    
    f.put("size", String.valueOf(size));        
    
    EventBus.publish(new MktDepthEvent(tickerId, f));
  }

  ///////////////////////////////////////////////////////////////////////
  // Error Logging
  ///////////////////////////////////////////////////////////////////////     
  public void error(Exception e) 
  {
    logger.error("Caught exception:" + e.toString());
  }
  
  public void error(String str) 
  {
    logger.error("Caught error:" + str);    
  }
  
  public void error(int id, int errorCode, String errorMsg) 
  {
    logger.error("Caught error with id:" + id + " code:" + errorCode +
      " msg:" + errorMsg);        
  }
  
  public void connectionClosed() {
    logger.warn("Connection closed");
  }
}