package com.hgh.plangenerator.utils;

import java.math.BigDecimal;

public class InterestRateUtil {

    public static double interestRatePerYear(double nominalRate) {
        return ((nominalRate / Constants.PERCENT) / Constants.PAYMENTS_PER_YEAR);
    }

    public static BigDecimal calculateInterest(BigDecimal nominalRate, BigDecimal initialOutstandingPrincipal) {
        final BigDecimal interestInCents = (nominalRate.multiply(Constants.DAYS_PER_MONTH)
                .multiply(initialOutstandingPrincipal))
                .divide(Constants.DAYS_PER_YEAR, BigDecimal.ROUND_HALF_UP);
        return interestInCents.movePointLeft(Constants.NEW_SCALE_OF_DECIMALS);
    }
}
