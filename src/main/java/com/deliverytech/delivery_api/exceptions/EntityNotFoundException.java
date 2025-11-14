package com.deliverytech.delivery_api.exceptions;

public class EntityNotFoundException extends BusinessException {
    
    public EntityNotFoundException(String message) {
        super(message);
    }
    
    public EntityNotFoundException(Class<?> entityClass, Long id) {
        super(String.format("%s com ID %d não encontrado", 
              entityClass.getSimpleName(), id));
    }
    
    public EntityNotFoundException(Class<?> entityClass, String identifier) {
        super(String.format("%s com identificador '%s' não encontrado", 
              entityClass.getSimpleName(), identifier));
    }
}