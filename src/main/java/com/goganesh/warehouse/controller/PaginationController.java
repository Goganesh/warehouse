package com.goganesh.warehouse.controller;

import com.goganesh.warehouse.domain.DataTablesPage;
import com.goganesh.warehouse.service.DataTablesPaginationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@AllArgsConstructor
public class PaginationController {

    private final DataTablesPaginationService paginationService;

    @GetMapping("api/pagination/agreements/{id}/payments")
    public DataTablesPage getPaymentsPagination(@PathVariable("id") String id, @RequestParam Map<String,String> allParams) {
        return paginationService.getPageForPayments(id, allParams);
    }



    @GetMapping("api/pagination/{path}")
    public DataTablesPage getPagination(@PathVariable("path") String path, @RequestParam Map<String,String> allParams) {
        return paginationService.getPage(path, allParams);
    }
}
