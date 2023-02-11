package org.acme;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.acme.model.UserModel;
import org.acme.util.PasswordGenerator;
import org.acme.util.RegeneratePassword;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
@QuarkusTest
public class RegeneratePasswordTest {
    @InjectMock
    PasswordGenerator passwordGenerator;

    @Inject
    RegeneratePassword regeneratePassword;

    @Test
    public void shouldGenerateANewPassword(){
        Mockito.when(passwordGenerator.generate())
                .thenReturn("regenerated-password");

        //dependency : quarkus-panache-mock
        PanacheMock.mock(UserModel.class);
        UserModel userModel = new
                UserModel("Alex","alex@example.com","my_super_password");
        Mockito.when(UserModel.findByUsername("Alex"))
                .thenReturn(Optional.of(userModel));
        regeneratePassword.regenerate("Alex");

        PanacheMock.verify(
                UserModel.class,
                Mockito.times(1));
        UserModel.findByUsername("Alex");

        System.out.println("user model : "+userModel.toString());
        assertThat(userModel.getPassword()).isEqualTo("regenerated-password");
    }
}
