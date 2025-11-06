package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.entity.Cliente;
import com.deliverytech.delivery_api.entity.Pedido;
import com.deliverytech.delivery_api.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PedidoService {
    
    private static final String STATUS_CRIADO = "CRIADO";
    private static final String STATUS_FINALIZADO = "FINALIZADO";

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteService clienteService; 

    // Criação
    public Pedido criarPedido(Long clienteId, Pedido novoPedido) {
        // ... (código omitido, estava correto)
        Cliente cliente = clienteService.buscarPorId(clienteId);
        if (!cliente.isAtivo()) {
            throw new IllegalStateException("O cliente está inativo e não pode fazer pedidos.");
        }
        if (novoPedido.getValorTotal() == null || novoPedido.getValorTotal().compareTo(BigDecimal.ZERO) <= 0) {
             throw new IllegalArgumentException("O valor total do pedido deve ser maior que zero.");
        }

        novoPedido.setCliente(cliente);
        novoPedido.setStatus(STATUS_CRIADO);
        novoPedido.setDataPedido(LocalDateTime.now());
        return pedidoRepository.save(novoPedido);
    }

    // Mudança de status
    public Pedido mudarStatus(Long id, String novoStatus) {
        // ... (código omitido, estava correto)
        Pedido pedido = buscarPorId(id);
        if (pedido.getStatus().equals(STATUS_FINALIZADO)) {
            throw new IllegalStateException("Não é possível alterar o status de um pedido finalizado.");
        }
        pedido.setStatus(novoStatus);
        return pedidoRepository.save(pedido);
    }

    // Busca por ID
    @SuppressWarnings("null")
    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido não encontrado."));
    }

    // Relatórios: Filtrar por status/data
    public List<Pedido> buscarRelatorioPedidos(Long clienteId, String status, LocalDateTime dataInicio, LocalDateTime dataFim) {
        // A busca por clienteId pode ser desnecessária se a @Query não a usa, mas mantemos a validação:
        clienteService.buscarPorId(clienteId); 
        
        
        return pedidoRepository.findRelatorioPorPeriodoEStatus(
                status, 
                dataInicio, 
                dataFim
        );
    }
}