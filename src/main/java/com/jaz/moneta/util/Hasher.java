package com.jaz.moneta.util;

import org.apache.log4j.Logger;

import java.util.Hashtable;

/**
 * Used to hash a string to number. Logs each hash and will halt on collision
 *
 */
public class Hasher
{
  static Logger logger = Logger.getLogger(Hasher.class);
    
  private static Hashtable log = new Hashtable();
  
  public static int generate(String s)
  {
    int hash = s.hashCode();
    
    // check
    String stored = (String)log.get(new Integer(hash));
    
    if (stored != null) {
      // already have something for this hash
      
      if (stored != s) {
        // oh oh, we've stored a different string for the same hash
        logger.fatal("Hash collision detected between:" + s + 
          " and:" + stored);
        System.exit(1);
      }
    } else {
      // store it
      log.put(new Integer(hash), s);
    }
    
    return hash;
  }
}
