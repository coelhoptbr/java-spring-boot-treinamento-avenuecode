package br.com.sicredi.treinamento.revendedora_plus.repository;

import br.com.sicredi.treinamento.revendedora_plus.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMarcaRepository extends JpaRepository<Marca, Long> {
}
