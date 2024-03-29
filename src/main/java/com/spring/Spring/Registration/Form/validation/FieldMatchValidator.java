package com.spring.Spring.Registration.Form.validation;



import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            final Object firstObj = BeanUtils.getProperty(value,firstFieldName);
            final Object secondObj = BeanUtils.getProperty(value,secondFieldName);
            return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        } catch (final Exception e) {
            return true;
        }
    }
}
