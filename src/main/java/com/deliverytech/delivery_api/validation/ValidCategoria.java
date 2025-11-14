package com.deliverytech.delivery_api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CategoriaValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCategoria {
    String message() default "Categoria inválida. Categorias válidas: PIZZA, LANCHES, JAPONESA, BRASILEIRA, ITALIANA, VEGANA, MEXICANA, ÁRABE, CHINESA";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}