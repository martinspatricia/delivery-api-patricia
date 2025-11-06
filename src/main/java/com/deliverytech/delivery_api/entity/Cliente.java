package com.deliverytech.delivery_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data; // Adiciona Getters, Setters, ToString, EqualsAndHashCode
import lombok.NoArgsConstructor; // Adiciona Construtor sem argumentos
import lombok.AllArgsConstructor; // Adiciona Construtor com todos os argumentos

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email; 
    private boolean ativo; 
    
    // Todos os Getters, Setters, Construtores e métodos de utilidade são gerados pelo Lombok.
}