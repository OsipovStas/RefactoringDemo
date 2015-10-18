package com.scrumtrek.simplestore;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author SO
 * @since 18.10.2015.
 */
public class CustomerTest {

    private static final String TITLE = "Movie1";
    private String regu = "Rental record for Test\n" +
            "\tMovie1\t2.0\n" +
            "Amount owed is 2.0\n" +
            "You earned 1 frequent renter points.";
    private Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("Test");

    }

    @org.junit.Test
    public void testStatementIsNotNull() throws Exception {
        customer.addRental(new Rental(new Movie(TITLE, PriceCodes.Regular), 1));
        String statement = customer.Statement();
        System.out.println(statement);
        assertNotNull("Statement is not null", statement);
        assertTrue(statement.contains(TITLE + "\t2.0"));
    }


    @Test
    public void thatNewReleaseIsExpectable() throws Exception {
        customer.addRental(new Rental(new Movie(TITLE, PriceCodes.NewRelease), 3));
        String statement = customer.Statement();
        System.out.println(statement);
        assertNotNull("Statement is not null", statement);
        assertTrue(statement.contains(TITLE + "\t9.0"));
    }

    @Test
    public void thatChildrenIsExpectable() throws Exception {
        customer.addRental(new Rental(new Movie(TITLE, PriceCodes.Childrens), 3));
        String statement = customer.Statement();
        System.out.println(statement);
        assertNotNull("Statement is not null", statement);
        assertTrue(statement.contains(TITLE + "\t1.5"));
    }

    @Test
    public void thatExtraReqPointForNewRelease() throws Exception {
        customer.addRental(new Rental(new Movie(TITLE, PriceCodes.NewRelease), 3));
        String statement = customer.Statement();
        System.out.println(statement);
        assertNotNull("Statement is not null", statement);
        assertTrue(statement.contains("You earned 2"));
    }

    @Test
    public void thatOneReqPointForNewRelease() throws Exception {
        customer.addRental(new Rental(new Movie(TITLE, PriceCodes.NewRelease), 1));
        String statement = customer.Statement();
        System.out.println(statement);
        assertNotNull("Statement is not null", statement);
        assertTrue(statement.contains("You earned 1"));
    }


    @Test
    public void thatOneReqPointForRegularCase() throws Exception {
        customer.addRental(new Rental(new Movie(TITLE, PriceCodes.Childrens), 3));
        String statement = customer.Statement();
        System.out.println(statement);
        assertNotNull("Statement is not null", statement);
        assertTrue(statement.contains("You earned 1"));
    }

}