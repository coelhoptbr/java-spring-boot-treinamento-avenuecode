package br.com.sicredi.treinamento.revendedora_plus.service;

import br.com.sicredi.treinamento.revendedora_plus.model.Marca;

import java.util.List;
import java.util.Optional;

public interface IMarcaService {

    List<Marca> listar();

    Optional<Marca> consultar(Long id);

    void salvar(Marca marca);

    void excluir(Long id);

}
