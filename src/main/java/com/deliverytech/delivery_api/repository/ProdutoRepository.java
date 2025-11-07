package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Buscar produtos por ID do restaurante
    List<Produto> findByRestauranteId(Long restauranteId);

    // Buscar produtos disponíveis de um restaurante específico
    List<Produto> findByRestauranteIdAndDisponivelTrue(Long restauranteId);

    // Buscar todos os produtos disponíveis
    List<Produto> findByDisponivelTrue();

    // Buscar produtos por categoria
    List<Produto> findByCategoria(String categoria);

    // Buscar produtos com preço menor ou igual ao valor informado
    List<Produto> findByPrecoLessThanEqual(BigDecimal preco);
}
