package com.deliverytech.delivery_api.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "restaurantes")
public class Restaurante {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    private String categoria;
    
    private String endereco;
    
    private String telefone;
    
    private BigDecimal taxaEntrega = BigDecimal.ZERO;
    
    private Boolean ativo = true;
    
    // Construtores
    public Restaurante() {}
    
    public Restaurante(String nome, String categoria, String endereco, String telefone) {
        this.nome = nome;
        this.categoria = categoria;
        this.endereco = endereco;
        this.telefone = telefone;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public BigDecimal getTaxaEntrega() { return taxaEntrega; }
    public void setTaxaEntrega(BigDecimal taxaEntrega) { this.taxaEntrega = taxaEntrega; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public BigDecimal getAvaliacao() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAvaliacao'");
    }
}