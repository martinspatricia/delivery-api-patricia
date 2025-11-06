package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.entity.Restaurante;
import com.deliverytech.delivery_api.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort; // NOVO IMPORT

import java.util.List;


@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    // Métodos cadastrarRestaurante, atualizarRestaurante, alterarStatus, buscarPorId...
    // (Permanecem inalterados e já estão corretos)
    // ...

    // Busca ativos ordenados por avaliação (AGORA COM OBJETO SORT)
    public List<Restaurante> buscarMelhoresRestaurantes() {
        // Usa o método findByAtivoTrue e passa o objeto Sort, 
        // ordenando pelo campo "avaliacao" de forma DESC
        return restauranteRepository.findByAtivoTrue(Sort.by(Sort.Direction.DESC, "avaliacao"));
    }

    public Restaurante buscarPorId(Long restauranteId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }

    public Restaurante alterarStatus(Long id, boolean ativo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'alterarStatus'");
    }

    public Restaurante cadastrarRestaurante(Restaurante restaurante) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cadastrarRestaurante'");
    }
}