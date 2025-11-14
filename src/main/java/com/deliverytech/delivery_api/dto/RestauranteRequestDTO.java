package com.deliverytech.delivery_api.dto;

import com.deliverytech.delivery_api.validation.ValidCategoria;
import com.deliverytech.delivery_api.validation.ValidTelefone;
import com.deliverytech.delivery_api.validation.ValidHorarioFuncionamento;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class RestauranteRequestDTO {
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;
    
    @NotBlank(message = "Categoria é obrigatória")
    @ValidCategoria
    private String categoria;
    
    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;
    
    @NotBlank(message = "Telefone é obrigatório")
    @ValidTelefone
    private String telefone;
    
    @NotNull(message = "Taxa de entrega é obrigatória")
    @DecimalMin(value = "0.0", message = "Taxa de entrega não pode ser negativa")
    @DecimalMax(value = "50.0", message = "Taxa de entrega não pode exceder R$ 50,00")
    private BigDecimal taxaEntrega;
    
    @NotBlank(message = "Horário de funcionamento é obrigatório")
    @ValidHorarioFuncionamento
    private String horarioFuncionamento;
    
    // Construtores
    public RestauranteRequestDTO() {}
    
    public RestauranteRequestDTO(String nome, String categoria, String endereco, 
                                String telefone, BigDecimal taxaEntrega, String horarioFuncionamento) {
        this.nome = nome;
        this.categoria = categoria;
        this.endereco = endereco;
        this.telefone = telefone;
        this.taxaEntrega = taxaEntrega;
        this.horarioFuncionamento = horarioFuncionamento;
    }
    
    // Getters e Setters
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
    public String getHorarioFuncionamento() { return horarioFuncionamento; }
    public void setHorarioFuncionamento(String horarioFuncionamento) { this.horarioFuncionamento = horarioFuncionamento; }
}