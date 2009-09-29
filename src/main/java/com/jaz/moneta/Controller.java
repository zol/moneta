package com.jaz.moneta;

import com.jaz.moneta.data.ContractData;
import com.jaz.moneta.util.Hasher;

import com.ib.client.Contract;
import com.ib.client.EClientSocket;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.bushe.swing.event.EventServiceLocator;
import org.bushe.swing.event.ThreadSafeEventService;

/**
 * The magic conductor
 *
 */
public class Controller
{
  private static final String HOST      = ""; //localhost
  private static final int    PORT      = 7496;
  // private static final int    PORT      = 4001;  // dodgy gwclient port
  private static final int    CLIENT_ID = Hasher.generate("moneta client");
  
  static Logger logger = Logger.getLogger(Controller.class);
  
  private EClientSocket client;
  private ArrayList<Object> subscribers = new ArrayList(); //anon subscribers
  
  public Controller() 
  {
    // initialize EventBus with ThreadSafeEventService instead if Swing
    try {
      EventServiceLocator.setEventService(
        EventServiceLocator.SERVICE_NAME_EVENT_BUS, 
        new ThreadSafeEventService());
    } catch (org.bushe.swing.event.EventServiceExistsException e) {
      logger.fatal("Couldn't start ThreadSafeEventService.");
      System.exit(1);
    }
    
    // add actors
    this.subscribers.add(new EventLog());
    this.subscribers.add(new DepthTablePublisher());
    this.subscribers.add(new DepthTableWriter());
    
    this.client = new EClientSocket(new MktInfoPublisher());
  }
  
  public void start() 
  {    
    // connect
    client.eConnect(HOST, PORT, CLIENT_ID);
    
    if (client.isConnected()) {
      logger.info("Connected");
      
      // ContractData cba = new ContractData(1, "CBA");
      // ContractData nab = new ContractData(2, "NAB");      
      
      // request test market data for CBA 
      // client.reqMktData(cba.mktDataID, cba.contract, "221", false);
      
      // request market depth for CBA & NAB
      // client.reqMktDepth(cba.mktDepthID, cba.contract, 5);
      // client.reqMktDepth(nab.mktDepthID, nab.contract, 5);      
      
      // request market depth for SPI & STW
      ContractData stw = new ContractData(2, "STW", "STK", "ASX", "AUD");
      ContractData spi = new ContractData(3, "SPI", "CFD", "SNFE", "AUD");      

      client.reqMktDepth(stw.mktDepthID, stw.contract, 5);
      client.reqMktDepth(spi.mktDepthID, spi.contract, 5);            
    }
  }
}
