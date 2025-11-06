package com.deliverytech.delivery_api;

import com.deliverytech.delivery_api.entity.*;
import com.deliverytech.delivery_api.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections; // <--- NOVO
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired private ClienteRepository clienteRepository;
    @Autowired private RestauranteRepository restauranteRepository;
    @Autowired private ProdutoRepository produtoRepository;
    @Autowired private PedidoRepository pedidoRepository;
    @Autowired private ItemPedidoRepository itemPedidoRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n--- 💾 INICIANDO DATALOADER ---");
        
        // 3 Clientes
        Cliente cli1 = clienteRepository.save(new Cliente(null, "João Cliente", "joao@email.com", true));
        Cliente cli2 = clienteRepository.save(new Cliente(null, "Maria Teste", "maria@email.com", true));
        // 4 Restaurantes
        Restaurante res1 = restauranteRepository.save(new Restaurante(null, "Pizzaria A", "Italiana", true, 4.5, new BigDecimal("4.00")));
        Restaurante res2 = restauranteRepository.save(new Restaurante(null, "Hamburgueria B", "Fast Food", true, 4.8, new BigDecimal("7.50")));
        // 5 Produtos (usando BigDecimal)
        Produto prod1 = produtoRepository.save(new Produto(null, "Mussarela", "Pizza", true, new BigDecimal("45.00"), res1));
        Produto prod2 = produtoRepository.save(new Produto(null, "Calabresa", "Pizza", true, new BigDecimal("50.00"), res1));
        Produto prod3 = produtoRepository.save(new Produto(null, "Refrigerante", "Bebida", true, new BigDecimal("8.00"), res1));
        Produto prod4 = produtoRepository.save(new Produto(null, "X-Bacon", "Lanche", true, new BigDecimal("32.00"), res2));
        produtoRepository.save(new Produto(null, "Batata Frita", "Acompanhamento", false, new BigDecimal("15.00"), res2)); 

        // 3 Pedidos (CONSTRUTOR ALINHADO COM ENTIDADE Pedido.java)
        // Pedido(ID, STATUS, DATA_PEDIDO, VALOR_TOTAL, ITENS(List), CLIENTE)
        
        Pedido ped1 = new Pedido(
            null, 
            "FINALIZADO", 
            LocalDateTime.now().minusHours(2), 
            new BigDecimal("58.00"), 
            Collections.emptyList(), 
            cli1
        );
        ped1 = pedidoRepository.save(ped1);
        itemPedidoRepository.save(new ItemPedido(null, 1, prod3.getPreco(), prod3, ped1));
        itemPedidoRepository.save(new ItemPedido(null, 1, prod2.getPreco(), prod2, ped1));
        
        Pedido ped2 = new Pedido(
            null, 
            "CRIADO", 
            LocalDateTime.now(), 
            new BigDecimal("32.00"), 
            Collections.emptyList(), 
            cli2
        );
        ped2 = pedidoRepository.save(ped2);
        itemPedidoRepository.save(new ItemPedido(null, 1, prod4.getPreco(), prod4, ped2));
        
        Pedido ped3 = new Pedido(
            null, 
            "FINALIZADO", 
            LocalDateTime.now().minusDays(5), 
            new BigDecimal("45.00"), 
            Collections.emptyList(), 
            cli1
        );
        ped3 = pedidoRepository.save(ped3);
        itemPedidoRepository.save(new ItemPedido(null, 1, prod1.getPreco(), prod1, ped3));

        // --- VALIDAÇÃO DAS CONSULTAS ---
        // (Continua o código de validação das consultas derivadas e @Query...)

        System.out.println("\n--- Cenário 1: Busca de Cliente por Email ---");
        clienteRepository.findByEmail("joao@email.com").ifPresent(c -> System.out.println("Cliente encontrado: " + c.getNome()));
        
        System.out.println("\n--- Cenário 4: Restaurantes por Taxa <= R$5.00 ---");
        List<Restaurante> restBaixaTaxa = restauranteRepository.findByTaxaEntregaLessThanEqual(new BigDecimal("5.00"));
        System.out.println("Restaurantes com taxa baixa: " + restBaixaTaxa.size()); 

        System.out.println("\n--- ATIVIDADE 3.1: Total de vendas por Restaurante ---");
        List<Object[]> vendasPorRestaurante = restauranteRepository.calcularTotalVendasPorRestaurante();
        vendasPorRestaurante.forEach(arr -> 
            System.out.println(" - " + arr[0] + ": R$ " + arr[1])
        );

        System.out.println("--- ✅ DATALOADER FINALIZADO COM SUCESSO ---");
    }
}