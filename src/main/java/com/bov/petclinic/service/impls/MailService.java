package com.bov.petclinic.service.impls;

import com.bov.petclinic.entity.NotificationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender mailSender;
    private final MailContentBuilderService mailContentBuilderService;

    @Async
    void sendMail(NotificationEmail notificationEmail){
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("petclinic.verify@email.com");
            mimeMessageHelper.setTo(notificationEmail.getRecipient());
            mimeMessageHelper.setText(notificationEmail.getBody());
        };
        try{
            mailSender.send(messagePreparator);
            log.info("Activation email send!");
        }catch (MailException e){
            log.error("Exception occurred when sending mail", e);
            throw new RuntimeException("Exception occurred when sending mail to "+ notificationEmail.getRecipient(),e);
        }
    }
}
