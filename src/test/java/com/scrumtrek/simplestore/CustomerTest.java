package com.scrumtrek.simplestore;

import static org.junit.Assert.assertNotNull;

/**
 * @author SO
 * @since 18.10.2015.
 */
public class CustomerTest {

    @org.junit.Test
    public void testStatementIsNotNull() throws Exception {
        Customer customer = new Customer("Test");
        customer.addRental(new Rental(new Movie("Movie1", PriceCodes.Regular), 1));
        String statement = customer.Statement();
        assertNotNull("Statement is not null", statement);
    }
}