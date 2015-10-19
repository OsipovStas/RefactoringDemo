package com.scrumtrek.simplestore;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String m_Name;
	private List<Rental> m_Rentals = new ArrayList<Rental>();

	public Customer(String name) {
		m_Name = name;
	}

	public String getName() {
		return m_Name;
	}


	public void addRental(Rental arg){
		m_Rentals.add(arg);
	}

	public String Statement()
	{
		double totalAmount = 0;
		int frequentRenterPoints = 0;
				
		String result = "Rental record for " + m_Name + "\n";
		
		for(Rental each: m_Rentals) {
			double thisAmount = 0;
			
			// Determine amounts for each line
			thisAmount = getRentalAmount(each);

			// Add frequent renter points
			frequentRenterPoints++;

			// Add bonus for a two-day new-release rental
			if ((each.getMovie().getPriceCode() == PriceCodes.NewRelease) && (each.getDaysRented() > 1))
			{
				frequentRenterPoints ++;
			}

			// Show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}

		// Add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points.";
		return result;
	}

	private double getRentalAmount(Rental each) {
		double result = 0.;
		switch(each.getMovie().getPriceCode()) {
            case Regular:
				result += 2;
                if (each.getDaysRented() > 2)
                {
					result += (each.getDaysRented() - 2) * 1.5;
                }
                break;

            case NewRelease:
				result += each.getDaysRented() * 3;
                break;

            case Childrens:
				result += 1.5;
                if (each.getDaysRented() > 3)
                {
					result = (each.getDaysRented() - 3) * 1.5;
                }
                break;
        }
		return result;
	}
}

