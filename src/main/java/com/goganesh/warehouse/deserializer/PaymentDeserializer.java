package com.goganesh.warehouse.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.goganesh.warehouse.domain.*;
import com.goganesh.warehouse.repository.AgreementRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.jackson.JsonComponent;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonComponent
@AllArgsConstructor
public class PaymentDeserializer extends JsonDeserializer<Payment> {

    private final AgreementRepository agreementRepository;

    @SneakyThrows
    @Override
    public Payment deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        long id = node.get("id").asLong();
        String name = node.get("name").asText();
        long amount = node.get("amount").asLong();
        String dateString = node.get("date").asText();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(dateString);

        long agreementId = node.get("agreement").asLong();
        Agreement agreement = agreementRepository.findById(agreementId).get();

        Payment payment = Payment.builder()
                .name(name)
                .date(date)
                .amount(amount)
                .agreement(agreement)
                .build();
        if(id!=-1)
            payment.setId(id);
        return payment;
    }
}
