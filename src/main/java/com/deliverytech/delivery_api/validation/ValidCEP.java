package com.deliverytech.delivery_api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CEPValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCEP {
    String message() default "CEP inválido. Formato esperado: 00000-000 ou 00000000";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}