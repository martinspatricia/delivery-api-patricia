package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.entity.Produto;
import com.deliverytech.delivery_api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // POST /restaurantes/{restauranteId}/produtos
    @PostMapping
    public ResponseEntity<?> cadastrarProduto(@PathVariable Long restauranteId, @RequestBody Produto produto) {
        try {
            Produto novoProduto = produtoService.cadastrarProduto(restauranteId, produto);
            return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Restaurante não encontrado.", HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // GET /restaurantes/{restauranteId}/produtos (Busca produtos disponíveis)
    @GetMapping
    public ResponseEntity<List<Produto>> buscarProdutosDisponiveis(@PathVariable Long restauranteId) {
        try {
            List<Produto> produtos = produtoService.buscarProdutosDisponiveis(restauranteId);
            return ResponseEntity.ok(produtos);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // PATCH /restaurantes/{restauranteId}/produtos/{produtoId}/disponibilidade
    @PatchMapping("/{produtoId}/disponibilidade")
    public ResponseEntity<?> alterarDisponibilidade(
            @PathVariable Long produtoId,
            @RequestParam boolean disponivel) {
        try {
            Produto produtoAtualizado = produtoService.alterarDisponibilidade(produtoId, disponivel);
            return ResponseEntity.ok(produtoAtualizado);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}