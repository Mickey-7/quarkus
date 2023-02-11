package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.util.PasswordGenerator;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import  static  org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class PasswordGeneratorTest {
    @Inject
    PasswordGenerator passwordGenerator;

    @Test
    public void shouldGenerateRandomPassword(){
        final String password = passwordGenerator.generate();
        //dependency :  assertj-core
        assertThat(password).containsPattern("[0-9A-F-]+");
    }
}
