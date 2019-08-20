package com.ihorcompany.fd.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = LoginValidation.class)
public @interface LoginValidator {
    String message() default "Invalid UserInformation";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
