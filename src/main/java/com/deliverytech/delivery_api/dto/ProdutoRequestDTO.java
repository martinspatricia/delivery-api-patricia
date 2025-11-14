package com.deliverytech.delivery_api.dto;

import com.deliverytech.delivery_api.validation.ValidCategoria;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ProdutoRequestDTO {
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres")
    private String nome;
    
    @NotBlank(message = "Descrição é obrigatória")
    @Size(min = 10, message = "Descrição deve ter pelo menos 10 caracteres")
    private String descricao;
    
    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
    @DecimalMax(value = "500.00", message = "Preço não pode exceder R$ 500,00")
    private BigDecimal preco;
    
    @NotBlank(message = "Categoria é obrigatória")
    @ValidCategoria
    private String categoria;
    
    // Construtores
    public ProdutoRequestDTO() {}
    
    public ProdutoRequestDTO(String nome, String descricao, BigDecimal preco, String categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }
    
    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}