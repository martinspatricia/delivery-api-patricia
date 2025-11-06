package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // 1. Busca por ID do Restaurante
    List<Produto> findByRestauranteId(Long restauranteId);
    
    // 2. Busca combinada (usada no ProdutoService)
    List<Produto> findByRestauranteIdAndDisponivelTrue(Long restauranteId); 

    // 3. Busca por status simples
    List<Produto> findByDisponivelTrue();
    
    List<Produto> findByCategoria(String categoria);
    
    // 4. Busca por valor (BigDecimal)
    List<Produto> findByPrecoLessThanEqual(BigDecimal preco);
}