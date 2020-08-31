package com.goganesh.warehouse.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.goganesh.warehouse.domain.Agreement;
import com.goganesh.warehouse.domain.AgreementType;
import com.goganesh.warehouse.domain.Contractor;
import com.goganesh.warehouse.domain.User;
import com.goganesh.warehouse.repository.*;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.jackson.JsonComponent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@JsonComponent
@AllArgsConstructor
public class AgreementDeserializer extends JsonDeserializer<Agreement> {

    private final AgreementTypeRepository agreementTypeRepository;
    private final AgreementRepository agreementRepository;
    private final ContractorRepository contractorRepository;
    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public Agreement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        long id = node.get("id").asLong();
        String name = node.get("name").asText();
        long price = node.get("price").asLong();
        long contractorId = node.get("contractor").asLong();
        long agreementTypeId = node.get("agreementType").asLong();
        long userId = node.get("user").asLong();
        String startDateString = node.get("startDate").asText();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = formatter.parse(startDateString);

        Contractor contractor = contractorRepository.findById(contractorId).get();
        AgreementType agreementType = agreementTypeRepository.findById(agreementTypeId).get();
        User user = userRepository.findById(userId).get();

        Agreement agreement;
        if(id!=-1) {
            agreement = agreementRepository.findById(id).get();
            agreement.setAgreementType(agreementType);
            agreement.setContractor(contractor);
            agreement.setName(name);
            agreement.setPrice(price);
            agreement.setStartDate(startDate);
            agreement.setUser(user);
        } else {
            agreement = Agreement.builder()
                    .name(name)
                    .startDate(startDate)
                    .price(price)
                    .contractor(contractor)
                    .agreementType(agreementType)
                    .user(user)
                    .build();
        }
        return agreement;
    }
}
