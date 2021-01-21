package com.fastcode.lkjh14.addons.email.application.mail;

import com.fastcode.lkjh14.addons.email.application.mail.dto.CreateEmailInput;
import org.springframework.mail.SimpleMailMessage;

public interface IEmailService {
    void sendMessage(CreateEmailInput email);

    SimpleMailMessage buildEmail(String email, String subject, String emailText);

    void sendEmail(SimpleMailMessage email);
}
