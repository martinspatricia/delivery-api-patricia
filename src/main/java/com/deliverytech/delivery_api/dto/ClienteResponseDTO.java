package com.deliverytech.delivery_api.dto;

import java.time.LocalDateTime;

import com.deliverytech.delivery_api.entity.Cliente;
import lombok.Data;

@Data
public class ClienteResponseDTO {

    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String endereco;

    private Boolean ativo;

    public ClienteResponseDTO(Cliente save) {
        this.id = save.getId();
        this.nome = save.getNome();
        this.email = save.getEmail();
        this.telefone = save.getTelefone();
        this.endereco = save.getEndereco();
        this.ativo = save.getAtivo();
    }

    public ClienteResponseDTO(Long id2, String nome2, String email2, String telefone2, String endereco2, Boolean ativo2,
            LocalDateTime dataCadastro) {
        //TODO Auto-generated constructor stub
    }
}