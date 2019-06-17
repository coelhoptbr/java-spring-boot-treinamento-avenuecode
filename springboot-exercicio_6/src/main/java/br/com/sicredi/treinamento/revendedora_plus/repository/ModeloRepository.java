package br.com.sicredi.treinamento.revendedora_plus.repository;

import br.com.sicredi.treinamento.revendedora_plus.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {
}
