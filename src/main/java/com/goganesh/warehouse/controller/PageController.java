package com.goganesh.warehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/fragments")
    public String getHome() {
        return "general.html";
    }

    @GetMapping("/contractors")
    public String getContractorsPage(){ return "contractors"; }

    @GetMapping("/agreements")
    public String getAgreementsPage(){ return "agreements"; }

    @GetMapping("/agreements/{id}")
    public String getAgreementPage(){ return "agreement"; }

    @GetMapping("/agreements/{id}/payments/{id}")
    public String getAgreementPaymentPage(){ return "payment"; }

    @GetMapping("/contractors/{id}")
    public String getContractorPage(){
        return "contractor";
    }

    @GetMapping("countries")
    public String getCountiesPage(){
        return "countries";
    }

    @GetMapping("agreement_types")
    public String getAgreementTypesPage(){
        return "agreement_types";
    }

    @GetMapping("contractor_types")
    public String getContractorTypesPage(){
        return "contractor_types";
    }

}
