package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.RestauranteRequestDTO;
import com.deliverytech.delivery_api.dto.RestauranteResponseDTO;
import com.deliverytech.delivery_api.service.RestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurantes")
@Tag(name = "Restaurantes", description = "Operações relacionadas aos restaurantes")
public class RestauranteController {

    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar restaurante", description = "Cria um novo restaurante no sistema")
    public ResponseEntity<RestauranteResponseDTO> cadastrarRestaurante(@Valid @RequestBody RestauranteRequestDTO restauranteRequestDTO) {
        RestauranteResponseDTO restauranteSalvo = restauranteService.cadastrarRestaurante(restauranteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(restauranteSalvo);
    }

    @GetMapping
    @Operation(summary = "Listar restaurantes", description = "Retorna todos os restaurantes ativos")
    public ResponseEntity<List<RestauranteResponseDTO>> listarRestaurantes() {
        List<RestauranteResponseDTO> restaurantes = restauranteService.listarRestaurantesAtivos();
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar restaurante por ID", description = "Retorna um restaurante pelo seu ID")
    public ResponseEntity<RestauranteResponseDTO> buscarRestaurantePorId(@PathVariable Long id) {
        RestauranteResponseDTO restaurante = restauranteService.buscarRestaurantePorId(id);
        return ResponseEntity.ok(restaurante);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar restaurante", description = "Atualiza os dados de um restaurante existente")
    public ResponseEntity<RestauranteResponseDTO> atualizarRestaurante(
            @PathVariable Long id, 
            @Valid @RequestBody RestauranteRequestDTO restauranteRequestDTO) {
        RestauranteResponseDTO restauranteAtualizado = restauranteService.atualizarRestaurante(id, restauranteRequestDTO);
        return ResponseEntity.ok(restauranteAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Inativar restaurante", description = "Inativa um restaurante (exclusão lógica)")
    public ResponseEntity<Void> inativarRestaurante(@PathVariable Long id) {
        restauranteService.ativarDesativarRestaurante(id);
        return ResponseEntity.noContent().build();
    }
}