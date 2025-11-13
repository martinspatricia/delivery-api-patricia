package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.ClienteRequestDTO;
import com.deliverytech.delivery_api.dto.ClienteResponseDTO;
import com.deliverytech.delivery_api.entity.Cliente;
import com.deliverytech.delivery_api.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteResponseDTO cadastrarCliente(ClienteRequestDTO dto) {
        // Validar se email já existe
        if (clienteRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Já existe um cliente com este e-mail: " + dto.getEmail());
        }

        // Criar novo cliente
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEndereco(dto.getEndereco());
        cliente.setAtivo(true);

        Cliente clienteSalvo = clienteRepository.save(cliente);
        return converterParaResponseDTO(clienteSalvo);
    }

    @Override
    public ClienteResponseDTO buscarClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
        return converterParaResponseDTO(cliente);
    }

    @Override
    public ClienteResponseDTO buscarClientePorEmail(String email) {
        Cliente cliente = clienteRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com email: " + email));
        return converterParaResponseDTO(cliente);
    }

    @Override
    public ClienteResponseDTO atualizarCliente(Long id, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));

        // Validar se email já existe (se mudou)
        if (!cliente.getEmail().equals(dto.getEmail()) && 
            clienteRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Já existe outro cliente com este e-mail: " + dto.getEmail());
        }

        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEndereco(dto.getEndereco());

        Cliente clienteAtualizado = clienteRepository.save(cliente);
        return converterParaResponseDTO(clienteAtualizado);
    }

    @Override
    public void ativarDesativarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
        
        cliente.setAtivo(!cliente.getAtivo());
        clienteRepository.save(cliente);
    }

    @Override
    public List<ClienteResponseDTO> listarClientesAtivos() {
        return clienteRepository.findByAtivoTrue()
            .stream()
            .map(this::converterParaResponseDTO)
            .collect(Collectors.toList());
    }

    // Método auxiliar para converter Entity para ResponseDTO
    private ClienteResponseDTO converterParaResponseDTO(Cliente cliente) {
        return new ClienteResponseDTO(
            cliente.getId(),
            cliente.getNome(),
            cliente.getEmail(),
            cliente.getTelefone(),
            cliente.getEndereco(),
            cliente.getAtivo(),
            cliente.getDataCadastro()
        );
    }
}