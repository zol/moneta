package com.jaz.moneta;

import com.jaz.moneta.event.MktInfoEvent;
import com.jaz.moneta.event.MktDataEvent;
import com.jaz.moneta.event.DepthTableEvent;

import org.apache.log4j.Logger;
import org.bushe.swing.event.annotation.*;

public class EventLog {
  static Logger logger = Logger.getLogger(EventLog.class);
  
  public EventLog() 
  {
    AnnotationProcessor.process(this);
  }
  
  // @EventSubscriber(eventClass=MktInfoEvent.class)  
  // public void onMktInfoEvent(MktInfoEvent evt) {
  //   logger.info(evt.toString());
  // }  

  // @EventSubscriber(eventClass=MktDataEvent.class)  
  // public void onMktDataEvent(MktDataEvent evt) {
  //   logger.info(evt.toString());
  // }
  
  // @EventSubscriber(eventClass=DepthTableEvent.class)  
  // public void onDepthTableEvent(DepthTableEvent evt) {
  //   logger.info(evt.toString());
  // }
}