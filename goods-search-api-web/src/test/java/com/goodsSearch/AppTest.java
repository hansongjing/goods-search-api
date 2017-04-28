package com.goodsSearch;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Unit test for simple App.
 */

public class AppTest extends TestCase
{
    @Autowired
    private Client client;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super(testName );
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
}
