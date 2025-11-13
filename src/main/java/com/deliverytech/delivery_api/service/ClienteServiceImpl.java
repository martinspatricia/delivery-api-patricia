package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.ClienteResponseDTO;
import com.deliverytech.delivery_api.entity.Cliente;
import com.deliverytech.delivery_api.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClienteServiceImpl extends ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponseDTO cadastrarCliente(ClienteDTO dto) {
        // 1. Validar se email já existe
        if (clienteRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Já existe um cliente com este e-mail: " + dto.getEmail());
        }

        // 2. Criar novo cliente
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEndereco(dto.getEndereco());
        cliente.setAtivo(true);

        // 3. Salvar no banco
        Cliente clienteSalvo = clienteRepository.save(cliente);

        // 4. Converter para ResponseDTO e retornar
        return converterParaResponseDTO(clienteSalvo);
    }

    public ClienteResponseDTO buscarClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
        return converterParaResponseDTO(cliente);
    }

    public ClienteResponseDTO buscarClientePorEmail(String email) {
        Cliente cliente = clienteRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com email: " + email));
        return converterParaResponseDTO(cliente);
    }

    public ClienteResponseDTO atualizarCliente(Long id, ClienteDTO dto) {
        // Buscar cliente existente
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));

        // Validar se outro cliente já tem o email (se mudou o email)
        if (!cliente.getEmail().equals(dto.getEmail()) && 
            clienteRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Já existe outro cliente com este e-mail: " + dto.getEmail());
        }

        // Atualizar dados
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEndereco(dto.getEndereco());

        Cliente clienteAtualizado = clienteRepository.save(cliente);
        return converterParaResponseDTO(clienteAtualizado);
    }

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

    // Método auxiliar para converter Entidade para ResponseDTO
    // Método auxiliar para converter Entidade para ResponseDTO
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

    // DTO local para evitar import ausente
    public static class ClienteDTO {
        private String nome;
        private String email;
        private String telefone;
        private String endereco;

        public ClienteDTO() {
        }

        public ClienteDTO(String nome, String email, String telefone, String endereco) {
            this.nome = nome;
            this.email = email;
            this.telefone = telefone;
            this.endereco = endereco;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public String getEndereco() {
            return endereco;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }
    }
}