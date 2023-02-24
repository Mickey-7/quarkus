package org.acme;

import io.quarkus.test.junit.callback.QuarkusTestBeforeEachCallback;
import io.quarkus.test.junit.callback.QuarkusTestMethodContext;
import io.quarkus.test.junit.mockito.InjectMock;
import org.acme.util.BannedUserClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.lang.annotation.Annotation;
import java.util.Arrays;

public class MyQuarkusTestBeforeEachCallback implements QuarkusTestBeforeEachCallback {
    @InjectMock
    @RestClient
    BannedUserClient bannedUserClient;
    //Let’s create a callback that is executed before any test method,
    //printing the current test method and all annotations placed at the class level.
    @Override
    public void beforeEach(QuarkusTestMethodContext context) {
        System.out.println(bannedUserClient);

        System.out.println("Executing " + context.getTestMethod());
        Annotation[] annotations = context.getTestInstance().getClass().getAnnotations();

        Arrays.stream(annotations)
                .forEach(System.out::println);
    }
}
