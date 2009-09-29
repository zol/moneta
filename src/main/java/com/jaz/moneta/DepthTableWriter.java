package com.jaz.moneta;

import com.jaz.moneta.event.DepthTableEvent;
import com.jaz.moneta.data.DepthTableData;
import com.jaz.moneta.data.ContractData;

import org.apache.log4j.Logger;
import org.bushe.swing.event.annotation.*;

// HACK
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Hashtable;


public class DepthTableWriter
{
  static Logger logger = Logger.getLogger(DepthTableWriter.class);
    
  Connection conn = null;
  
  public DepthTableWriter() 
  {
    AnnotationProcessor.process(this);
    
    //HACK
    try {
      // The newInstance() call is a work around for some
      // broken Java implementations
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      
      conn = DriverManager.getConnection("jdbc:mysql://localhost/pluto_dev?" +
                                         "user=icyte&password=icyte");
    } catch (Exception ex) {
      // handle the error
      logger.fatal(ex.getMessage());
      System.exit(1);
    }
  }
  
  public void write(int id, DepthTableData dt) {
    if (conn == null)
      return;
    
    //HACK
    Statement stmt = null;

    try {
      stmt = conn.createStatement();
      
      // Date
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
      formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
      String dateString = formatter.format(new Date());
      
      // BUILD INSERT HERE
      int contractID = id - ContractData.MKT_DEPTH;
    
      String sql = "INSERT INTO depths  (" +
        "contract_id," +
        "bid_price," +
        "bid_size," +
        "bid_vwap," +
        "bid_total_size," +
        "ask_price," +
        "ask_size," +
        "ask_vwap," +
        "ask_total_size," +
        "created_at," +                                          
        "updated_at) VALUES (" +                                         
        contractID + "," + // contract_id
        dt.bids[0].get("price") + "," + // bid_price
        dt.bids[0].get("size") + "," + // bid_size
        dt.bidVWAP() + "," + // bid_vwap
        dt.bidSizeSum() + "," + // bid_total_size
        dt.asks[0].get("price") + "," + // ask_price
        dt.asks[0].get("size") + "," + // ask_size
        dt.askVWAP() + "," + // ask_vwap
        dt.askSizeSum() + "," + // ask_total_size
        "\"" + dateString + "\"," + // created_at
        "\"" + dateString + "\");"; // updated_at                                                                                  
                         
      stmt.executeUpdate(sql);
      //stmt.getUpdateCount(); // yields affected rows
      
      logger.info("Wrote DepthTableData to DB at: " + dateString);
    } catch (SQLException ex) {
      // handle any errors      
      logger.error(ex.getMessage());
    } finally {
      // it is a good idea to release
      // resources in a finally{} block
      // in reverse-order of their creation
      // if they are no-longer needed

      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException sqlEx) { } // ignore

        stmt = null;
      }
    }
            
  }
    
  @EventSubscriber(eventClass=DepthTableEvent.class)  
  public void onDepthTableEvent(DepthTableEvent evt) {
    write(evt.getId(), evt.getDepthTable());
    // logger.info(evt.toString());        
  }
}