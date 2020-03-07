package com.hgh.plangenerator.controller;

import com.hgh.plangenerator.model.RequestJsonModel;
import com.hgh.plangenerator.model.ResponseJsonModel;
import com.hgh.plangenerator.service.PlanGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "generate-plan")
public class PlanGeneratorController {

    @Autowired
    private PlanGeneratorService planGeneratorService;

    @PostMapping
    public List<ResponseJsonModel> generateRepaymentPlan(double loanAmount, double nominalRate, int duration, String startDate) {
        return planGeneratorService.generatePaymentPlan(new RequestJsonModel(loanAmount, nominalRate, duration, startDate));
    }
}
