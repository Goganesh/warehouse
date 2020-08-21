package com.goganesh.warehouse.service;

import com.goganesh.warehouse.domain.Agreement;
import com.goganesh.warehouse.domain.DataTablesPage;
import com.goganesh.warehouse.domain.dto.AgreementDto;
import com.goganesh.warehouse.exception.PathIsNotAccepted;
import com.goganesh.warehouse.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DataTablesPaginationService {
    private final CountryRepository countryRepository;
    private final ContractorTypeRepository contractorTypeRepository;
    private final ContractorRepository contractorRepository;
    private final AgreementTypeRepository agreementTypeRepository;
    private final AgreementRepository agreementRepository;

    public DataTablesPage getPage(String path, Map<String,String> allParams){
        int start = Integer.parseInt(allParams.get("start"));
        int length = Integer.parseInt(allParams.get("length"));
        String search = allParams.get("search[value]");
        long draw = Long.parseLong(allParams.get("draw"));

        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        PageRequest pageRequest = PageRequest.of((start+1)/length, length, sort);

        Page page = null;
        Page<Agreement> page1 = null;
        switch (path){
            case "agreements":
                page1 = agreementRepository.findAllByNameContaining(search, pageRequest);
                break;
            case "countries":
                page = countryRepository.findAllByNameContaining(search, pageRequest);
                break;
            case "contractor_types":
                page = contractorTypeRepository.findAllByNameContaining(search, pageRequest);
                break;
            case "contractors":
                page = contractorRepository.findAllByNameContaining(search, pageRequest);
                break;
            case "agreement_types":
                page = agreementTypeRepository.findAllByNameContaining(search, pageRequest);
                break;
            default:
                throw new PathIsNotAccepted("Path - " + path + " is not accepted for pagination");
        }

        DataTablesPage dataTablesPage = new DataTablesPage();
        if(path.equals("agreements")) {
            dataTablesPage.setData(page1.getContent()
                    .stream()
                    .map(AgreementDto:: toDto)
                    .collect(Collectors.toList())
            );
            fillDataTablesPage(dataTablesPage, page1);
        } else {
            dataTablesPage.setData(page.getContent());
            fillDataTablesPage(dataTablesPage, page);
        }
        dataTablesPage.setDraw(draw);
        return dataTablesPage;
    }

    private DataTablesPage fillDataTablesPage(DataTablesPage dataTablesPage, Page page){
        dataTablesPage.setRecordsFiltered(page.getTotalElements());
        dataTablesPage.setRecordsTotal(page.getTotalElements());

        return dataTablesPage;
    }
}
