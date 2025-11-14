package com.deliverytech.delivery_api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HorarioFuncionamentoValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidHorarioFuncionamento {
    String message() default "Horário de funcionamento inválido. Use o formato HH:MM-HH:MM";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}