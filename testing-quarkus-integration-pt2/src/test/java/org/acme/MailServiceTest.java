package org.acme;

import io.quarkus.mailer.MockMailbox;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.model.UserModel;
import org.acme.service.MailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class MailServiceTest {
    @Inject
    MockMailbox mailbox;

    @Inject
    MailService mailService;

    @BeforeEach
    void init(){
        mailbox.clear();
    }

    @Test
    public void shouldSendAnEmail(){
        UserModel userModel = new UserModel("Alex","alex@example.com","abcd");
        mailService.sendEmail(userModel);

        assertThat(mailbox.getMessagesSentTo("alex@example.com"))
                .hasSize(1)
                .extracting("subject")
                .containsExactly("Your New Password");
    }
}
