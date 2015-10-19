package com.scrumtrek.simplestore;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String createStatement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;

        String result = "Rental record for " + name + "\n";

		for (Rental rental : rentals) {
			// Determine amounts for rental line
			double thisAmount = getRentalAmount(rental);

            // Add frequent renter points
			frequentRenterPoints += getFrequentRentalPoints(rental);

            // Show figures for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
        }

        // Add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points.";
        return result;
    }

    private double getRentalAmount(Rental rental) {
        double result;
        switch (rental.getMovie().getPriceCode()) {
            case Regular:
                result = getRegularMovieAmount(rental);
                break;
            case NewRelease:
                result = getNewReleaseMovieAmount(rental);
                break;
            case Childrens:
                result = getChildrenMovieAmount(rental);
                break;
            default:
                throw new IllegalStateException("A rental has unknown price code.");
        }
        return result;
    }

    private double getChildrenMovieAmount(Rental each) {
        double result = 1.5;
        if (each.getDaysRented() > 3) {
            result = (each.getDaysRented() - 3) * 1.5;
        }
        return result;
    }

    private double getNewReleaseMovieAmount(Rental each) {
        return each.getDaysRented() * 3.;
    }

    private double getRegularMovieAmount(Rental each) {
        double result = 2.;
        if (each.getDaysRented() > 2) {
            result += (each.getDaysRented() - 2) * 1.5;
        }
        return result;
    }

    public int getFrequentRentalPoints(Rental rental) {
        return rental.getMovie().getPriceCode() == PriceCodes.NewRelease && rental.getDaysRented() > 1 ? 2 : 1;
    }
}

