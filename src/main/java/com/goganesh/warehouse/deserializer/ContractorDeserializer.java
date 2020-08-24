package com.goganesh.warehouse.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.goganesh.warehouse.domain.Contractor;
import com.goganesh.warehouse.domain.ContractorType;
import com.goganesh.warehouse.domain.Country;
import com.goganesh.warehouse.repository.ContractorTypeRepository;
import com.goganesh.warehouse.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;
import java.io.IOException;

@JsonComponent
@AllArgsConstructor
public class ContractorDeserializer extends JsonDeserializer<Contractor> {

    private final ContractorTypeRepository contractorTypeRepository;
    private final CountryRepository countryRepository;

    @Override
    public Contractor deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException{
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        long id = node.get("id").asLong();
        String name = node.get("name").asText();
        long phoneNumber = node.get("phoneNumber").asLong();
        long countryId = node.get("country").asLong();
        long contractorTypeId = node.get("contractorType").asLong();

        Country country = countryRepository.findById(countryId).get();
        ContractorType contractorType = contractorTypeRepository.findById(contractorTypeId).get();

        Contractor contractor = Contractor.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .country(country)
                .contractorType(contractorType)
                .build();
        if(id!=-1)
            contractor.setId(id);
        return contractor;
    }
}
