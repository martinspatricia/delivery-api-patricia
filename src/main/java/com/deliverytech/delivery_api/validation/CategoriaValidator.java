package com.deliverytech.delivery_api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class CategoriaValidator implements ConstraintValidator<ValidCategoria, String> {
    
    private static final List<String> CATEGORIAS_VALIDAS = Arrays.asList(
        "PIZZA", "LANCHES", "JAPONESA", "BRASILEIRA", "ITALIANA", 
        "VEGANA", "MEXICANA", "ÁRABE", "CHINESA", "DOCES", "BEBIDAS"
    );

    @Override
    public boolean isValid(String categoria, ConstraintValidatorContext context) {
        if (categoria == null || categoria.trim().isEmpty()) {
            return true;
        }
        return CATEGORIAS_VALIDAS.contains(categoria.toUpperCase());
    }
}