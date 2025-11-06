package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.entity.Cliente;
import com.deliverytech.delivery_api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Cadastro, validação de e-mail
    public Cliente cadastrarCliente(Cliente novoCliente) {
        if (clienteRepository.findByEmail(novoCliente.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Erro: Email '" + novoCliente.getEmail() + "' já cadastrado.");
        }
        novoCliente.setAtivo(true);
        return clienteRepository.save(novoCliente);
    }

    // Busca
    @SuppressWarnings("null")
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente não encontrado."));
    }

    // Atualização
    public Cliente atualizarCliente(Long id, Cliente dadosAtualizados) {
        Cliente cliente = buscarPorId(id);
        cliente.setNome(dadosAtualizados.getNome());
        cliente.setAtivo(dadosAtualizados.isAtivo());
        return clienteRepository.save(cliente);
    }

    // Inativação
    public Cliente inativarCliente(Long id) {
        Cliente cliente = buscarPorId(id);
        if (!cliente.isAtivo()) {
            throw new IllegalStateException("Cliente já está inativo.");
        }
        cliente.setAtivo(false);
        return clienteRepository.save(cliente);
    }

    // Busca todos ativos
    public List<Cliente> buscarClientesAtivos() {
        return clienteRepository.findByAtivo(true);
    }
}