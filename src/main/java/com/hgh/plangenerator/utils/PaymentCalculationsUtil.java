package com.hgh.plangenerator.utils;

import com.hgh.plangenerator.model.RequestJsonModel;

public class PaymentCalculationsUtil {

    public static double calculateBorrowerPaymentAmount(RequestJsonModel requestJsonModel) {
        return borrowerPaymentAmountNumerator(requestJsonModel) / borrowerPaymentAmountDenominator(requestJsonModel);
    }

    public static double borrowerPaymentAmountNumerator(RequestJsonModel requestJsonModel) {
        return requestJsonModel.getLoanAmount() * InterestRateUtil.interestRatePerYear(requestJsonModel.getNominalRate());
    }

    public static double borrowerPaymentAmountDenominator(RequestJsonModel requestJsonModel) {
        final double subtrahend = Math.pow(InterestRateUtil.interestRatePerYear(requestJsonModel.getNominalRate()) + 1, -requestJsonModel.getDuration());
        return (1 - subtrahend);
    }
}