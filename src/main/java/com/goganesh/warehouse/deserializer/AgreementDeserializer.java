package com.goganesh.warehouse.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.goganesh.warehouse.domain.Agreement;
import com.goganesh.warehouse.domain.AgreementType;
import com.goganesh.warehouse.domain.Contractor;
import com.goganesh.warehouse.repository.AgreementTypeRepository;
import com.goganesh.warehouse.repository.ContractorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
@AllArgsConstructor
public class AgreementDeserializer extends JsonDeserializer<Agreement> {
    private final AgreementTypeRepository agreementTypeRepository;
    private final ContractorRepository contractorRepository;

    @Override
    public Agreement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return null;
    }
}
