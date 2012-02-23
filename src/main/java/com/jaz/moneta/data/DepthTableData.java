package com.jaz.moneta.data;

import com.jaz.moneta.util.Calc;

import java.util.Hashtable;
import java.util.Vector;

public class DepthTableData
{
  public static int ROWS = 5;
  
  public Hashtable<String, String>[] bids = new Hashtable[ROWS];
  public Hashtable<String, String>[] asks = new Hashtable[ROWS];
  
  public DepthTableData()
  {
    // init
    for (int i = 0;i < ROWS;i++) {
      bids[i] = new Hashtable();
      asks[i] = new Hashtable();      
    }      
  }
  
  public String toString() {
    String s = "Bid            | Ask\n" +
               "Price   | Size | Price   | Size\n" +
               "-------------------------------\n";
                              
    for(int i = 0;i < ROWS;i++) {
      s += bids[i].get("price") + "\t" + bids[i].get("size") + "\t";
      s += asks[i].get("price") + "\t" + asks[i].get("size") + "\n";
    }
    
    s += "VWAP    | Size | VWAP    | Size\n" + 
         "-------------------------------\n" + 
         bidVWAP() + "\t" + bidSizeSum() + "\t" +
         askVWAP() + "\t" + askSizeSum() + "\t";
    
    return s;
  }
  
  public String bidSizeSum() {
    Vector<Double> vals = new Vector();
    
    for(int i = 0;i < ROWS;i++) {
      if (!bids[i].isEmpty()) {        
        vals.add(new Double(bids[i].get("size")));
      }
    }
    
    return String.valueOf(Calc.sum(vals));
  }

  public String askSizeSum() {
    Vector<Double> vals = new Vector();
    
    for(int i = 0;i < ROWS;i++) {
      if (!asks[i].isEmpty()) {  
        vals.add(new Double(asks[i].get("size")));
      }
    }
    
    return String.valueOf(Calc.sum(vals));
  }
  
  public String bidVWAP() {
    Vector<Double> vals = new Vector();
    
    for(int i = 0;i < ROWS;i++) {
      if (!bids[i].isEmpty()) {
        vals.add(new Double(bids[i].get("price")));
        vals.add(new Double(bids[i].get("size")));
      }
    }
    
    return String.valueOf(Calc.vwap(vals));
  }
  
  public String askVWAP() {
    Vector<Double> vals = new Vector();
    
    for(int i = 0;i < ROWS;i++) {
      if (!asks[i].isEmpty()) {      
        // String s = "";
        // for (String key : asks[i].keySet()) {
        //   s += " " + key + "=" + asks[i].get(key);
        // }
        // System.out.println(s);
        
        vals.add(new Double(asks[i].get("price")));
        vals.add(new Double(asks[i].get("size")));
      }
    }
    
    return String.valueOf(Calc.vwap(vals));
  }  
}