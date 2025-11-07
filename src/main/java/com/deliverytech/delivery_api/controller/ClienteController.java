package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.entity.Cliente;
import com.deliverytech.delivery_api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // POST /clientes
    @PostMapping
    public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente) {
        try {
            Cliente novoCliente = clienteService.cadastrarCliente(cliente);
            return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // GET /clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> buscarClientesAtivos() {
        List<Cliente> clientes = clienteService.buscarClientesAtivos();
        return ResponseEntity.ok(clientes);
    }

    // GET /clientes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cliente>> buscarClientePorId(@PathVariable Long id) {
        try {
            Optional<Cliente> cliente = clienteService.buscarPorId(id);
            return ResponseEntity.ok(cliente);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // PUT /clientes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        try {
            Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);
            return ResponseEntity.ok(clienteAtualizado);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE /clientes/{id} - Usado para inativação
    @DeleteMapping("/{id}")
    public ResponseEntity<?> inativarCliente(@PathVariable Long id) {
        try {
            clienteService.inativarCliente(id);
            return new ResponseEntity<>("Cliente inativado com sucesso.", HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}