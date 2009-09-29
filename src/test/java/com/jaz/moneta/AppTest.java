package com.jaz.moneta;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.jaz.moneta.util.Calc;

import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    /**
     * Basic test of the VWAP algorithm with a sample dataset
     */
    public void testCalcVWAP()
    {
        ArrayList<Double> l = new ArrayList();
        l.add(10.30);
        l.add(100.0);
        l.add(10.32);
        l.add(200.0);
        l.add(10.28);
        l.add(200.0);
        l.add(10.35);
        l.add(400.0);
        l.add(10.27);
        l.add(50.0);

        assertEquals("VWAP calculation incorrect", 10.319473684210527, 
          Calc.vwap(l), 0.0);
    }    
}
