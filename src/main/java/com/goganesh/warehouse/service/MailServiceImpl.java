package com.goganesh.warehouse.service;

import com.goganesh.warehouse.domain.Agreement;
import com.goganesh.warehouse.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender emailSender;
    private static final String SUBJECT_FOR_EXPIRED_AGREEMENTS = "Просроченные платежи по договорам";
    private final Environment environment;

    @Override
    public void sendMailMessageForUserAboutExpiredAgreements(List<Agreement> expiredAgreements ,
                                                             User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(environment.getProperty("spring.mail.username"));
        message.setTo(user.getEmail());
        message.setSubject(SUBJECT_FOR_EXPIRED_AGREEMENTS);

        String text = fillTextForExpiredAgreements(expiredAgreements, user);
        message.setText(text);
        emailSender.send(message);
    }

    private String fillTextForExpiredAgreements(List<Agreement> expiredAgreements , User user){
        StringBuilder text = new StringBuilder().append(user.getFullName() + " добрый день.\n");
        text.append("Обнаружены договоры с просроченными платежами:\n");
        for(Agreement agreement : expiredAgreements){
            text.append("Договор - " + agreement.getName() + ", дата начала соглашения - " + agreement.getStartDate());
        }
        return text.toString();
    }

}
