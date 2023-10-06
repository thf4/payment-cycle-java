package com.payment.cycle.infrastructure.service;

import com.payment.cycle.infrastructure.service.interfaces.IEmailService;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService implements IEmailService {
    @Value(value = "${company.email}")
    private String company_email;

    @Value(value = "${sendgrid.api.key}")
    private String api_key;

    @Override
    public void sendMail(String email) {

        String subject = "Welcome to the jungle!";
        Email from = new Email(company_email);
        Email to = new Email(email);

        Content content = new Content("text/plain", "This is spartaaaaaaaaaaaa!");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid send = new SendGrid(api_key);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            send.api(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
