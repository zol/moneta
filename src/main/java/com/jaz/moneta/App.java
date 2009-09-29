package com.jaz.moneta;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

/**
 * Moneta
 *
 */
public class App 
{
  static Logger logger = Logger.getLogger(App.class);
      
  public static void main( String[] args )
  {
    // init logging (to console)
    BasicConfigurator.configure();    
    
    // begin
    logger.info("Moneta starting...");
    Controller c = new Controller();
    c.start();
  }
}
