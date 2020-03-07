package com.hgh.plangenerator.model;

import com.hgh.plangenerator.exception.InputsNotValidException;

public class RequestJsonModel {

    private double loanAmount;
    private double nominalRate;
    private int duration;
    private String startDate;

    public RequestJsonModel() {
    }

    public RequestJsonModel(double loanAmount, double nominalRate, int duration, String startDate) {
        this.loanAmount = loanAmount;
        this.nominalRate = nominalRate;
        this.duration = duration;
        this.startDate = startDate;
    }

    public void validateRequestJsonModel() {
        if (this.loanAmount <= 0)
            throw new InputsNotValidException("The loan amount is required and can be a positive number!");

        if (this.nominalRate <= 0)
            throw new InputsNotValidException("The nominal rate is required and can be a positive number!");

        if (this.duration <= 0)
            throw new InputsNotValidException("The duration is required and can be a positive number!");

        if (this.startDate == null || this.startDate.isEmpty())
            throw new InputsNotValidException("The start date is required!");
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getNominalRate() {
        return nominalRate;
    }

    public int getDuration() {
        return duration;
    }

    public String getStartDate() {
        return startDate;
    }
}
