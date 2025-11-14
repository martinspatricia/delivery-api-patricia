package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.ClienteRequestDTO;
import com.deliverytech.delivery_api.dto.ClienteResponseDTO;
import com.deliverytech.delivery_api.entity.Cliente;
import com.deliverytech.delivery_api.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ClienteResponseDTO cadastrarCliente(ClienteRequestDTO dto) {
        if (clienteRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Já existe um cliente com este e-mail: " + dto.getEmail());
        }

        Cliente cliente = modelMapper.map(dto, Cliente.class);
        cliente.setAtivo(true);

        Cliente clienteSalvo = clienteRepository.save(cliente);
        return modelMapper.map(clienteSalvo, ClienteResponseDTO.class);
    }

    @Override
    public ClienteResponseDTO buscarClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

    @Override
    public ClienteResponseDTO buscarClientePorEmail(String email) {
        Cliente cliente = clienteRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com email: " + email));
        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

    @Override
    public ClienteResponseDTO atualizarCliente(Long id, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));

        if (!cliente.getEmail().equals(dto.getEmail()) && 
            clienteRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Já existe outro cliente com este e-mail: " + dto.getEmail());
        }

        modelMapper.map(dto, cliente);
        Cliente clienteAtualizado = clienteRepository.save(cliente);
        return modelMapper.map(clienteAtualizado, ClienteResponseDTO.class);
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
            .map(cliente -> modelMapper.map(cliente, ClienteResponseDTO.class))
            .collect(Collectors.toList());
    }
}