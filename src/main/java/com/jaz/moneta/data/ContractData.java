package com.jaz.moneta.data;

import com.jaz.moneta.util.Hasher;

import com.ib.client.Contract;

/**
 * Data item wrapping a Contract and moment-in-time pricing info
 *
 */
public class ContractData
{
  // constants for calculating different id's off the base  
  public static final int MKT_DATA  = 10000;
  public static final int MKT_DEPTH = 20000;  
  
  public Contract contract;
  public int id;
  public int mktDataID;
  public int mktDepthID;
    
  public ContractData(int id, String symbol, String secType, String exchange, 
    String currency)
  {
    // init contract
    contract = new Contract();
    contract.m_symbol      = symbol;
    contract.m_secType     = secType;
    contract.m_exchange    = exchange;
    contract.m_currency    = currency;
    contract.m_primaryExch = exchange;
    
    // init ids
    this.id = id;
    mktDataID = id + MKT_DATA;
    mktDepthID = id + MKT_DEPTH;
  }
  
  public ContractData(int id, String symbol) {
    this(id, symbol, "STK", "ASX", "AUD");
  }
}
