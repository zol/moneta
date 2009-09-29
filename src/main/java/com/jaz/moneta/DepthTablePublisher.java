package com.jaz.moneta;

import com.jaz.moneta.event.MktDepthEvent;
import com.jaz.moneta.event.DepthTableEvent;
import com.jaz.moneta.data.DepthTableData;

import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.bushe.swing.event.annotation.*;
import org.bushe.swing.event.EventBus;

public class DepthTablePublisher {
  static Logger logger = Logger.getLogger(DepthTablePublisher.class);
  
  public static int ROWS = 5; // number of rows to obtain depth for
  
  private Hashtable<Integer, DepthTableData> ids = new Hashtable();
  
  public DepthTablePublisher() 
  {
    AnnotationProcessor.process(this);
  }
  
  @EventSubscriber(eventClass=MktDepthEvent.class)  
  public void onMktDepthEvent(MktDepthEvent evt) {        
    // init the depth table data
    DepthTableData d = ids.get(evt.getId());
    
    if (d == null) {
      d = new DepthTableData();
      ids.put(evt.getId(), d);
    }
    
    // update it's contents from the raw event
    // side: 1 - Bid (left), 0 - Ask (right)
    // operation == 1 when we have all data it seems    
    int position = new Integer(evt.getFields().get("position"));
    if (evt.getFields().get("side").equals("1")) {
      // Bid (left)
      d.bids[position].put("price", evt.getFields().get("price"));
      d.bids[position].put("size", evt.getFields().get("size"));
    } else {
      // Ask (left)
      d.asks[position].put("price", evt.getFields().get("price"));
      d.asks[position].put("size", evt.getFields().get("size"));      
    }
    
    // this means we have been served up a complete DepthTable
    // TODO: actually check this
    if (evt.getFields().get("operation").equals("1")) {
      // we have an update to a complete depth table so FIRE!
      EventBus.publish(new DepthTableEvent(evt.getId(), d));
    }
  }  
}
