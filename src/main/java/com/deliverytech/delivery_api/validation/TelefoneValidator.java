package com.deliverytech.delivery_api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class TelefoneValidator implements ConstraintValidator<ValidTelefone, String> {
    
    private static final Pattern TELEFONE_PATTERN = 
        Pattern.compile("^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$");

    @Override
    public boolean isValid(String telefone, ConstraintValidatorContext context) {
        if (telefone == null || telefone.trim().isEmpty()) {
            return true;
        }
        return TELEFONE_PATTERN.matcher(telefone).matches();
    }
}