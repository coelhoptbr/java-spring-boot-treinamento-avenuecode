package br.com.sicredi.revendedora.controller;

import br.com.sicredi.revendedora.exception.AlteracaoNaoPermitidaException;
import br.com.sicredi.revendedora.exception.CarroNaoExisteException;
import br.com.sicredi.revendedora.exception.DadoObrigatorioNaoInformadoException;
import br.com.sicredi.revendedora.exception.ModeloNaoExisteException;
import br.com.sicredi.revendedora.model.Carro;
import br.com.sicredi.revendedora.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class CarrosController {

    @Autowired
    private CarroService servico;

    @GetMapping("/carros")
    public List<Carro> getCarro() {
        return servico.findAll();
    }

    @GetMapping("/carros/{id}")
    public ResponseEntity<Carro> getCarroById(@PathVariable("id") Long id) {
        Optional<Carro> optCarro = servico.findById(id);
        if (optCarro.isPresent()) {
            return ResponseEntity.ok(optCarro.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/carros")
    public ResponseEntity createCarro(@RequestBody Carro carro) throws AlteracaoNaoPermitidaException {
        try {
            Carro carroCriado = servico.create(carro);
            URI uriCriado = URI.create(String.format("/carros/%s", carroCriado.getId()));
            return ResponseEntity.created(uriCriado).build();
        } catch (DadoObrigatorioNaoInformadoException exc){
            return ResponseEntity.badRequest().build();
        } catch (ModeloNaoExisteException exc) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/carros/{id}")
    public ResponseEntity updateCarro(@PathVariable("id") Long id, @RequestBody Carro carro) throws CarroNaoExisteException, ModeloNaoExisteException {
        carro.setId(id);
        servico.update(carro);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/carros/{id}")
    public ResponseEntity updatePlacaCarro(@PathVariable("id") Long id, @RequestBody Carro carro) throws CarroNaoExisteException {
        carro.setId(id);
        servico.updatePlaca(carro);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/carros/{id}")
    public ResponseEntity deleteCarro(@PathVariable("id") Long id) {
        if (servico.findById(id).isPresent()) {
            servico.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
