package com.deliverytech.delivery_api.dto;

import com.deliverytech.delivery_api.validation.ValidCEP;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoRequestDTO {
    
    @NotNull(message = "Cliente ID é obrigatório")
    private Long clienteId;
    
    @NotNull(message = "Restaurante ID é obrigatório")
    private Long restauranteId;
    
    @NotBlank(message = "Endereço de entrega é obrigatório")
    private String enderecoEntrega;
    
    @NotBlank(message = "CEP é obrigatório")
    @ValidCEP
    private String cep;
    
    @Valid
    @NotEmpty(message = "Pedido deve ter pelo menos um item")
    private List<ItemPedidoRequestDTO> itens;
    
    // Construtores
    public PedidoRequestDTO() {}
    
    public PedidoRequestDTO(Long clienteId, Long restauranteId, String enderecoEntrega, 
                           String cep, List<ItemPedidoRequestDTO> itens) {
        this.clienteId = clienteId;
        this.restauranteId = restauranteId;
        this.enderecoEntrega = enderecoEntrega;
        this.cep = cep;
        this.itens = itens;
    }
    
    // Getters e Setters
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public Long getRestauranteId() { return restauranteId; }
    public void setRestauranteId(Long restauranteId) { this.restauranteId = restauranteId; }
    public String getEnderecoEntrega() { return enderecoEntrega; }
    public void setEnderecoEntrega(String enderecoEntrega) { this.enderecoEntrega = enderecoEntrega; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    public List<ItemPedidoRequestDTO> getItens() { return itens; }
    public void setItens(List<ItemPedidoRequestDTO> itens) { this.itens = itens; }

    public LocalDateTime getDataPedido() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDataPedido'");
    }
}