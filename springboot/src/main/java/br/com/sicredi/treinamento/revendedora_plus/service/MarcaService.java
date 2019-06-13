package br.com.sicredi.treinamento.revendedora_plus.service;

import br.com.sicredi.treinamento.revendedora_plus.model.Marca;
import br.com.sicredi.treinamento.revendedora_plus.repository.IMarcaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService implements IMarcaService {

    private IMarcaRepository repo;

    public MarcaService(IMarcaRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Marca> listar() {
        return repo.findAll();
    }

    @Override
    public void salvar(Marca marca) {
        repo.save(marca);
    }

    @Override
    public void excluir(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<Marca> consultar(Long id) {
        return repo.findById(id);
    }
}
