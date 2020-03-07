package com.hgh.plangenerator.service;

import com.hgh.plangenerator.model.PaymentPerMonth;
import com.hgh.plangenerator.model.RequestJsonModel;
import com.hgh.plangenerator.model.ResponseJsonModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanGeneratorService {

    public List<ResponseJsonModel> generatePaymentPlan(RequestJsonModel requestJsonModel) {
        requestJsonModel.validateRequestJsonModel();
        return generateResponseList(generatePaymentPlanList(requestJsonModel));
    }

    private List<PaymentPerMonth> generatePaymentPlanList(RequestJsonModel requestJsonModel) {

        List<PaymentPerMonth> paymentPerMonthList = new ArrayList<>();
        paymentPerMonthList.add(firstMonth(requestJsonModel));
        paymentPerMonthList.addAll(remainingMonths(requestJsonModel, paymentPerMonthList.get(0)));
        return paymentPerMonthList;
    }

    private List<PaymentPerMonth> remainingMonths(RequestJsonModel requestJsonModel, PaymentPerMonth previousPaymentPerMonth) {

        List<PaymentPerMonth> paymentPerMonthList = new ArrayList<>();
        for (int i = 1; i < requestJsonModel.getDuration(); i++) {
            previousPaymentPerMonth = new PaymentPerMonth(requestJsonModel, previousPaymentPerMonth, i);
            paymentPerMonthList.add(previousPaymentPerMonth);
        }
        return paymentPerMonthList;
    }

    private PaymentPerMonth firstMonth(RequestJsonModel requestJsonModel) {
        return new PaymentPerMonth(requestJsonModel);
    }

    private List<ResponseJsonModel> generateResponseList(List<PaymentPerMonth> paymentPerMonthList) {

        List<ResponseJsonModel> responseList = new ArrayList<>();
        paymentPerMonthList.forEach(paymentPerMonth -> {
            ResponseJsonModel response = new ResponseJsonModel(paymentPerMonth);
            responseList.add(response);
        });
        return responseList;
    }
}
