package br.com.sicredi.treinamento.revendedora_plus.repository;

import br.com.sicredi.treinamento.revendedora_plus.model.Carro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

  List<Carro> findByPlaca(String placa);

  List<Carro> findByModeloId(Long modeloId);

  List<Carro> findByPlacaAndModeloId(String placa, Long modeloId);
}
