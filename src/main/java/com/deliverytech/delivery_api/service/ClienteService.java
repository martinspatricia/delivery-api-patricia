package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.ClienteRequestDTO;
import com.deliverytech.delivery_api.dto.ClienteResponseDTO;
import java.util.List;

public interface ClienteService {
    ClienteResponseDTO cadastrarCliente(ClienteRequestDTO dto);
    ClienteResponseDTO buscarClientePorId(Long id);
    ClienteResponseDTO buscarClientePorEmail(String email);
    ClienteResponseDTO atualizarCliente(Long id, ClienteRequestDTO dto);
    void ativarDesativarCliente(Long id);
    List<ClienteResponseDTO> listarClientesAtivos();
}