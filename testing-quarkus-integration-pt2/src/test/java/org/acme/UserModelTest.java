package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.model.UserModel;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;
import java.util.Optional;

@QuarkusTest
public class UserModelTest {
    @Test
    @Transactional
    public void shouldFindUserByUsername(){
        final UserModel userModel = new UserModel();
        userModel.setUsername("Alex");
        userModel.setEmail("asotobu@example.com");
        userModel.persist();

        Optional<UserModel> foundUser = UserModel.findUserByUsername("Alex");
        assertThat(foundUser)
                .isNotEmpty()
                .map(u->u.getEmail())
                .contains("asotobu@example.com");
    }

}