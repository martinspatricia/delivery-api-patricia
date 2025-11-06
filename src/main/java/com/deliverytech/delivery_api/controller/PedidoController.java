package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.entity.Pedido;
import com.deliverytech.delivery_api.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // POST /pedidos?clienteId=1
    @PostMapping
    public ResponseEntity<?> criarPedido(@RequestParam Long clienteId, @RequestBody Pedido pedido) {
        try {
            Pedido novoPedido = pedidoService.criarPedido(clienteId, pedido);
            return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Cliente não encontrado.", HttpStatus.NOT_FOUND);
        } catch (IllegalStateException | IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // PATCH /pedidos/{id}/status?novoStatus=EM_PREPARACAO
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> mudarStatusPedido(@PathVariable Long id, @RequestParam String novoStatus) {
        try {
            Pedido pedidoAtualizado = pedidoService.mudarStatus(id, novoStatus);
            return ResponseEntity.ok(pedidoAtualizado);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // GET /pedidos/relatorio?clienteId=1&status=FINALIZADO&dataInicio=...&dataFim=...
    @GetMapping("/relatorio")
    public ResponseEntity<?> gerarRelatorioPedidos(
            @RequestParam Long clienteId,
            @RequestParam String status,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {
        try {
            List<Pedido> relatorio = pedidoService.buscarRelatorioPedidos(clienteId, status, dataInicio, dataFim);
            return ResponseEntity.ok(relatorio);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Cliente não encontrado.", HttpStatus.NOT_FOUND);
        }
    }
}