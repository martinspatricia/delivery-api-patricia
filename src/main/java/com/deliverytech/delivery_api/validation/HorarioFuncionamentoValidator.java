package com.deliverytech.delivery_api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class HorarioFuncionamentoValidator implements ConstraintValidator<ValidHorarioFuncionamento, String> {
    
    private static final Pattern HORARIO_PATTERN = 
        Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]-([01]?[0-9]|2[0-3]):[0-5][0-9]$");

    @Override
    public boolean isValid(String horario, ConstraintValidatorContext context) {
        if (horario == null || horario.trim().isEmpty()) {
            return true;
        }
        
        if (!HORARIO_PATTERN.matcher(horario).matches()) {
            return false;
        }
        
        // Validação adicional: horário de abertura deve ser antes do fechamento
        String[] horarios = horario.split("-");
        String abertura = horarios[0];
        String fechamento = horarios[1];
        
        return abertura.compareTo(fechamento) < 0;
    }
}