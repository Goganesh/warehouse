package com.goganesh.warehouse.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goganesh.warehouse.domain.Contractor;
import com.goganesh.warehouse.domain.ContractorType;
import com.goganesh.warehouse.domain.Country;
import com.goganesh.warehouse.exception.DataNotFoundException;
import com.goganesh.warehouse.repository.ContractorTypeRepository;
import com.goganesh.warehouse.repository.CountryRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;


@Component
@NoArgsConstructor
public class ContractorDeserializer extends JsonDeserializer<Contractor> {

    @Autowired
    private ContractorTypeRepository contractorTypeRepository;
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Contractor deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        long id = node.get("id").asLong();
        String name = node.get("name").asText();
        long phoneNumber = node.get("phoneNumber").asLong();

        String countryJson = node.findValue("country").toString();
        Country country = objectMapper.readValue(countryJson, Country.class);

        if(!countryRepository.findById(country.getId()).get().equals(country))
            throw new DataNotFoundException("Where is no info in DB for entity Country id - " + country.getId()
                    + " , name - " + country.getName());

        String contractorTypeJson = node.findValue("contractorType").toString();
        ContractorType contractorType = objectMapper.readValue(contractorTypeJson, ContractorType.class);

        Contractor contractor = new Contractor(name, phoneNumber, country, contractorType);
        if(id!=-1)
            contractor.setId(id);
        return contractor;
    }
}
