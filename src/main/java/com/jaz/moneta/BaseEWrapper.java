package com.jaz.moneta;

import com.ib.client.EWrapper;

import com.ib.client.Contract;
import com.ib.client.ContractDetails;
import com.ib.client.Execution;
import com.ib.client.Order;
import com.ib.client.OrderState;
import com.ib.client.UnderComp;

/**
 * BaseEWrapper contains empty implementations of EWrapper methods.
 *
 */
public abstract class BaseEWrapper implements EWrapper
{
  ///////////////////////////////////////////////////////////////////////
  // EWrapper empty implementation methods
  ///////////////////////////////////////////////////////////////////////
  public void tickPrice(int tickerId, int field, double price, 
    int canAutoExecute) {}
    
  public void tickSize(int tickerId, int field, int size) {}
  
  public void tickOptionComputation(int tickerId, int field, 
    double impliedVol, double delta, double modelPrice, double pvDividend) {}
    
  public void tickGeneric(int tickerId, int tickType, double value) {}
  
  public void tickString(int tickerId, int tickType, String value) {}
  
  public void tickEFP(int tickerId, int tickType, double basisPoints, 
    String formattedBasisPoints, double impliedFuture, int holdDays, 
    String futureExpiry, double dividendImpact, double dividendsToExpiry) {}
    
  public void orderStatus(int orderId, String status, int filled, 
    int remaining, double avgFillPrice, int permId, int parentId, 
    double lastFillPrice, int clientId, String whyHeld) {}
    
  public void openOrder(int orderId, Contract contract, Order order, 
    OrderState orderState) {}
    
  public void openOrderEnd() {}
  
  public void updateAccountValue(String key, String value, String currency, 
    String accountName) {}
    
  public void updatePortfolio(Contract contract, int position, 
    double marketPrice, double marketValue, double averageCost, 
    double unrealizedPNL, double realizedPNL, String accountName) {}
    
  public void updateAccountTime(String timeStamp) {}
  
  public void accountDownloadEnd(String accountName) {}
  
  public void nextValidId( int orderId) {}
  
  public void contractDetails(int reqId, ContractDetails contractDetails) {}
  
  public void bondContractDetails(int reqId, 
    ContractDetails contractDetails) {}
    
  public void contractDetailsEnd(int reqId) {}
  
  public void execDetails(int reqId, Contract contract, 
    Execution execution) {}
  
  public void execDetailsEnd(int reqId) {}
  
  public void updateMktDepth(int tickerId, int position, int operation, 
    int side, double price, int size) {}
    
  public void updateMktDepthL2(int tickerId, int position, String marketMaker, 
    int operation, int side, double price, int size) {}
    
  public void updateNewsBulletin(int msgId, int msgType, String message, 
    String origExchange) {}
    
  public void managedAccounts(String accountsList) {}
  
  public void receiveFA(int faDataType, String xml) {}
  
  public void historicalData(int reqId, String date, double open, double high, 
    double low, double close, int volume, int count, double WAP, 
    boolean hasGaps) {}
    
  public void scannerParameters(String xml) {}
  
  public void scannerData(int reqId, int rank, 
    ContractDetails contractDetails, String distance, String benchmark, 
    String projection, String legsStr) {}
    
  public void scannerDataEnd(int reqId) {}
  
  public void realtimeBar(int reqId, long time, double open, double high, 
    double low, double close, long volume, double wap, int count) {}
    
  public void currentTime(long time) {}
  
  public void fundamentalData(int reqId, String data) {}
  
  public void deltaNeutralValidation(int reqId, UnderComp underComp) {}
  
  public void tickSnapshotEnd(int reqId) {}
}