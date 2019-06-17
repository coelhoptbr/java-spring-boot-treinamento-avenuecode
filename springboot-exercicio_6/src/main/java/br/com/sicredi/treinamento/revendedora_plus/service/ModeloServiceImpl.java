package br.com.sicredi.treinamento.revendedora_plus.service;

import br.com.sicredi.treinamento.revendedora_plus.model.Modelo;
import br.com.sicredi.treinamento.revendedora_plus.repository.ModeloRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModeloServiceImpl implements ModeloService {

    private final ModeloRepository modeloRepository;

    public ModeloServiceImpl(ModeloRepository modeloRepository) {
        this.modeloRepository = modeloRepository;
    }

    @Override
    public Optional<Modelo> findById(Long id) {
        return modeloRepository.findById(id);
    }

}
