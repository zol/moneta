package com.jaz.moneta.util;

import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.List;

public class Calc
{
  // sums each element in the array
  public static double sum(Collection<Double> c)
  {
    double sum = 0;
    
    for(Double d : c) {
      sum += d;
    }
    
    return sum;
  }
  
  // averages the elements in the array
  public static double average(Collection<Double> c)
  {
    return (sum(c) / c.size());
  }
  
  // returns the Volume Weighted Average for the items in the collection
  // the collection should be in the form of [price, num, price, num...]
  // see: http://en.wikipedia.org/wiki/VWAP
  public static double vwap(List<Double> l)
  {
    Double top, bottom;
    top = bottom = 0.0;
            
    for (int i = 0, j = 1;i < (l.size() - 1);i+=2, j+=2) {
      Double price = l.get(i);
      Double num = l.get(j);
      
      top += (price * num);
      bottom += num;
    }
    
    return top / bottom;
  }
}
