package com.deliverytech.delivery_api.dto;

import jakarta.validation.constraints.*;

public class ItemPedidoRequestDTO {
    
    @NotNull(message = "Produto ID é obrigatório")
    private Long produtoId;
    
    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 1, message = "Quantidade deve ser pelo menos 1")
    @Max(value = 50, message = "Quantidade não pode exceder 50")
    private Integer quantidade;
    
    // Construtores
    public ItemPedidoRequestDTO() {}
    
    public ItemPedidoRequestDTO(Long produtoId, Integer quantidade) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }
    
    // Getters e Setters
    public Long getProdutoId() { return produtoId; }
    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
}