package com.payment.cycle.infrastructure.service.interfaces;

import com.sendgrid.Response;

public interface IEmailService {
    void sendMail(String email);
}
