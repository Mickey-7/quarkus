package org.acme.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class EnumValidator implements ConstraintValidator<IEnumValidator, String> {
    List<String> valueList= null;

    @Override
    public void initialize(IEnumValidator constraintAnnotation) {
        valueList = new ArrayList<>();
        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClazz();

        Enum[] enumValArr = enumClass.getEnumConstants();

        for (Enum enumVal : enumValArr){
            valueList.add(enumVal.toString().toUpperCase());
        }
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !StringUtils.isEmpty(s) && valueList.contains(s.toUpperCase());
    }
}
