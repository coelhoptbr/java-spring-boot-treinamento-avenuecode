package br.com.sicredi.treinamento.revendedora_plus.repository;

import br.com.sicredi.treinamento.revendedora_plus.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findAllByModeloIdAndPlaca(Long idModelo, String placa);

    List<Carro> findAllByModeloId(Long idModelo);

    List<Carro> findAllByPlaca(String placa);

}
