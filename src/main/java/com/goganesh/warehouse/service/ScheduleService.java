package com.goganesh.warehouse.service;

import com.goganesh.warehouse.domain.Agreement;
import com.goganesh.warehouse.domain.User;
import com.goganesh.warehouse.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleService {
    private final AgreementService agreementService;
    private final UserRepository userRepository;
    private final MailService mailService;

    @Scheduled(cron = "0 0 0 * * *") /*(initialDelay = 2000, fixedRate = 30000)*/
    public void checkExpiredPaymentsForAgreements(){
        List<User> users = userRepository.findAll();
        for(User user : users){
            List<Agreement> expiredAgreements = agreementService.findExpiredAgreementsByUser(user);
            mailService.sendMailMessageForUserAboutExpiredAgreements(expiredAgreements, user);
        }
    }
}
