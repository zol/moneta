package com.jaz.moneta.data;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Used to store and retrieve ContractDatas
 *
 */
public class ContractDataStore
{
  private ArrayList store;
  private Hashtable mktDataStore;
  private Hashtable mktDepthStore;
    
  public void add(ContractData cd)
  {
    store.add(cd);
    mktDataStore.put(new Integer(cd.mktDataID), cd);
    mktDepthStore.put(new Integer(cd.mktDepthID), cd);    
  }

  public ContractData getByMktDataID(int id)
  {
    return (ContractData)mktDataStore.get(new Integer(id));
  }
  
  public ContractData getByMktDepthID(int id)
  {
    return (ContractData)mktDepthStore.get(new Integer(id));
  }
}
