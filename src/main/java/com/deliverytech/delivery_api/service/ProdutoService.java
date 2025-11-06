package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.entity.Produto;
import com.deliverytech.delivery_api.entity.Restaurante;
import com.deliverytech.delivery_api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal; // Import necessário
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private RestauranteService restauranteService; 

    // Cadastro por restaurante, validação de preço
    public Produto cadastrarProduto(Long restauranteId, Produto novoProduto) {
        Restaurante restaurante = restauranteService.buscarPorId(restauranteId);
        
        // Validação de preço usando compareTo()
        if (novoProduto.getPreco() == null || novoProduto.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser maior que zero.");
        }

        novoProduto.setRestaurante(restaurante);
        novoProduto.setDisponivel(true); 
        return produtoRepository.save(novoProduto);
    }

    // Disponibilidade (alternar status)
    public Produto alterarDisponibilidade(Long produtoId, boolean disponivel) {
        Produto produto = buscarPorId(produtoId);
        produto.setDisponivel(disponivel);
        return produtoRepository.save(produto);
    }

    // Busca por ID
    @SuppressWarnings("null")
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado."));
    }

    // Busca por restaurante e disponibilidade, usando o método combinado
    public List<Produto> buscarProdutosDisponiveis(Long restauranteId) {
        // Validação de existência do restaurante (garante 404 se o restauranteId for inválido)
        restauranteService.buscarPorId(restauranteId); 
        
        // Usa o novo Query Method que busca por ID E status (findByRestauranteIdAndDisponivelTrue)
        return produtoRepository.findByRestauranteIdAndDisponivelTrue(restauranteId);
    }
}