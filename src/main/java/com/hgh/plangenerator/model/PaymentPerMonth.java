package com.hgh.plangenerator.model;

import com.hgh.plangenerator.utils.Constants;
import com.hgh.plangenerator.utils.DateUtil;
import com.hgh.plangenerator.utils.InterestRateUtil;
import com.hgh.plangenerator.utils.PaymentCalculationsUtil;

import java.math.BigDecimal;

public class PaymentPerMonth {

    private BigDecimal borrowerPaymentAmount;
    private String date;
    private BigDecimal initialOutstandingPrincipal;
    private BigDecimal interest;
    private BigDecimal principal;
    private BigDecimal remainingOutstandingPrincipal;

    public PaymentPerMonth(RequestJsonModel requestJsonModel) {
        this.borrowerPaymentAmount = roundOff(PaymentCalculationsUtil.calculateBorrowerPaymentAmount(requestJsonModel));
        this.date = DateUtil.generatePaymentDate(requestJsonModel.getStartDate());
        this.initialOutstandingPrincipal = roundOff(requestJsonModel.getLoanAmount());
        this.interest = roundOff(InterestRateUtil.calculateInterest(BigDecimal.valueOf(requestJsonModel.getNominalRate()), this.initialOutstandingPrincipal));
        this.principal = roundOff(calculatePrincipal(this.borrowerPaymentAmount));
        this.remainingOutstandingPrincipal = roundOff(this.initialOutstandingPrincipal.subtract(this.principal));
    }

    public PaymentPerMonth(RequestJsonModel requestJsonModel, PaymentPerMonth previousPaymentPerMonth, int counter) {
        this.initialOutstandingPrincipal = roundOff(previousPaymentPerMonth.getRemainingOutstandingPrincipal());
        this.date = DateUtil.generatePaymentDate(requestJsonModel.getStartDate(), counter);
        this.interest = roundOff(InterestRateUtil.calculateInterest(BigDecimal.valueOf(requestJsonModel.getNominalRate()), this.initialOutstandingPrincipal));
        this.principal = roundOff(calculatePrincipal(previousPaymentPerMonth.getBorrowerPaymentAmount()));
        this.borrowerPaymentAmount = roundOff(this.principal.add(this.interest));
        this.remainingOutstandingPrincipal = roundOff(this.initialOutstandingPrincipal.subtract(this.principal));
    }

    public BigDecimal getBorrowerPaymentAmount() {
        return this.borrowerPaymentAmount;
    }

    public String getDate() {
        return this.date;
    }

    public BigDecimal getInitialOutstandingPrincipal() {
        return this.initialOutstandingPrincipal;
    }

    public BigDecimal getInterest() {
        return this.interest;
    }

    public BigDecimal getPrincipal() {
        return this.principal;
    }

    public BigDecimal getRemainingOutstandingPrincipal() {
        return this.remainingOutstandingPrincipal;
    }

    private BigDecimal roundOff(double doubleValue) {
        return BigDecimal.valueOf(doubleValue).setScale(Constants.NEW_SCALE_OF_DECIMALS, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal roundOff(BigDecimal value) {
        return value.setScale(Constants.NEW_SCALE_OF_DECIMALS, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal calculatePrincipal(BigDecimal annuity) {
        final BigDecimal principal = annuity.subtract(interest);
        if (principal.compareTo(initialOutstandingPrincipal) > 0) {
            return initialOutstandingPrincipal;
        } else {
            return principal;
        }
    }
}