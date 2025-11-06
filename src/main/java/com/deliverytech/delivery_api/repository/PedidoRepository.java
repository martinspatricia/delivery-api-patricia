package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Cenário 3: Pedidos Recentes
    List<Pedido> findTop10ByOrderByDataPedidoDesc();
    
    // ATIVIDADE 3.1: Consultas Customizadas (@Query) - Pedidos com valor acima de X
    @Query("SELECT p FROM Pedido p WHERE p.valorTotal > :valor AND p.status = 'FINALIZADO'")
    List<Pedido> findPedidosComValorAcimaDe(@Param("valor") BigDecimal valor);

    // ATIVIDADE 3.1: Consultas Customizadas (@Query) - Relatório por Período e Status
    // CORRIGIDO: Usando a anotação @Param para garantir o mapeamento correto.
    @Query("SELECT p FROM Pedido p " +
           "WHERE (:status IS NULL OR p.status = :status) " +
           "AND p.dataPedido BETWEEN :dataInicio AND :dataFim " +
           "ORDER BY p.dataPedido DESC")
    List<Pedido> findRelatorioPorPeriodoEStatus(
            @Param("status") String status, 
            @Param("dataInicio") LocalDateTime dataInicio, 
            @Param("dataFim") LocalDateTime dataFim
    );
    

}