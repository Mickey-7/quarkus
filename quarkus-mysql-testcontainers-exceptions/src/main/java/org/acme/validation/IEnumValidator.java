package org.acme.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
//added after creating EnumValidator class
@Constraint(validatedBy = EnumValidator.class)
public @interface IEnumValidator {
    Class<? extends Enum<?>> enumClazz();

    String message() default "value is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
