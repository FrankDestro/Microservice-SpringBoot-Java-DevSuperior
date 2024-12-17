package com.dev.hr_email.services;

import com.dev.hr_email.models.dto.EmailDTO;
import com.dev.hr_email.services.exceptions.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmailService {

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(EmailDTO obj) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            List<String> toList = obj.getTo();
            String[] toArr = new String[toList.size()];
            toArr = toList.toArray(toArr);
            message.setTo(toArr);
            message.setSubject(obj.getSubject());
            message.setText(obj.getBody());
            emailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            throw new EmailException("Failed to send email");
        }
    }

    @KafkaListener(topics = "payroll.generated.email", groupId = "generated.email.group")
    public void listenEmail(String message) {
        List<String> toList = List.of("frank-destro@outlook.com");
        String subject = "Folha de pagamento gerada";
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setTo(toList);
        emailDTO.setSubject(subject);
        emailDTO.setBody(message);
        sendEmail(emailDTO);
    }
}











