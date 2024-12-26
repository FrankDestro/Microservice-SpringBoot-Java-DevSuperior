package com.dev.hr_email.services;

import com.dev.hr_email.models.dto.PaymentDTO;
import com.dev.hr_email.services.exceptions.EmailException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class EmailSendGridService {

    @Value("${sendgrid.api-key}")
    private String sendGridApiKey;

    @Value("${template.id}")
    private String templateId;

    @KafkaListener(topics = "payroll.generated.email", groupId = "generated.email.group")
    public void sendEmailBySendGrid(String message) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        PaymentDTO paymentDTO = objectMapper.readValue(message, PaymentDTO.class);

        Mail mail = getMail(paymentDTO);

        try {
            SendGrid sg = new SendGrid(sendGridApiKey);
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getBody());
        } catch (MatchException e) {
            e.printStackTrace();
            throw new EmailException("Failed to send email");
        }
    }

    private Mail getMail(PaymentDTO paymentDTO) {
        Email from = new Email("franklyn.damaceno@gmail.com");
        Email to = new Email("franklyn.damaceno@gmail.com");
        String subject = "Folha de Pagamento";

        Mail mail = new Mail();
        mail.setFrom(from);
        mail.setSubject(subject);

        Personalization personalization = new Personalization();
        personalization.addTo(to);

        personalization.addDynamicTemplateData("name", paymentDTO.getName());
        personalization.addDynamicTemplateData("dailyIncome", paymentDTO.getDailyIncome());
        personalization.addDynamicTemplateData("days", paymentDTO.getDays());
        personalization.addDynamicTemplateData("total", paymentDTO.getTotal());
        mail.addPersonalization(personalization);
        mail.setTemplateId(templateId);

        return mail;
    }
}

