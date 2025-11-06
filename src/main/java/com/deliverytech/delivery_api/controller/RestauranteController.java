package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.entity.Restaurante;
import com.deliverytech.delivery_api.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    // POST /restaurantes
    @PostMapping
    public ResponseEntity<?> cadastrarRestaurante(@RequestBody Restaurante restaurante) {
        try {
            Restaurante novoRestaurante = restauranteService.cadastrarRestaurante(restaurante);
            return new ResponseEntity<>(novoRestaurante, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // GET /restaurantes (Busca todos ativos ordenados por avaliação)
    @GetMapping
    public ResponseEntity<List<Restaurante>> buscarRestaurantesAtivos() {
        List<Restaurante> restaurantes = restauranteService.buscarMelhoresRestaurantes();
        return ResponseEntity.ok(restaurantes);
    }

    // PATCH /restaurantes/{id}/status (Para ativar/inativar)
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> alterarStatusRestaurante(@PathVariable Long id, @RequestParam boolean ativo) {
        try {
            Restaurante restaurante = restauranteService.alterarStatus(id, ativo);
            return ResponseEntity.ok(restaurante);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}