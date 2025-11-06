package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.entity.Restaurante;
import org.springframework.data.domain.Sort; // NOVO IMPORT
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    List<Restaurante> findByCategoria(String categoria);

    // MÉTODO REVISADO: Agora aceita um objeto Sort para ordenação
    List<Restaurante> findByAtivoTrue(Sort sort);
    
    // Buscar por taxa de entrega menor ou igual a um valor
    List<Restaurante> findByTaxaEntregaLessThanEqual(BigDecimal taxa);
    
    // Top 5, ordenado por nome ascendente
    List<Restaurante> findTop5ByOrderByNomeAsc();

    // Query Customizada (Se esta for a sintaxe correta para seu banco, deve funcionar)
    @Query("SELECT r.nome, SUM(p.valorTotal) " +
           "FROM Pedido p JOIN p.itens ip JOIN ip.produto pr JOIN pr.restaurante r " +
           "GROUP BY r.nome " +
           "ORDER BY SUM(p.valorTotal) DESC")
    List<Object[]> calcularTotalVendasPorRestaurante();
}