package org.acme.service;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import org.acme.model.UserModel;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MailService {
    @Inject
    Mailer mailer;

    public void sendEmail(final UserModel userModel){
        mailer.send(
                Mail.withText(userModel.getEmail() , "Your New Password",
                        String.format("New Password %s",userModel.getPassword()))
        );
    }
}
