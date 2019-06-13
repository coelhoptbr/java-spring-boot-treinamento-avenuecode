package br.com.sicredi.treinamento.revendedora_plus.controller;

import br.com.sicredi.treinamento.revendedora_plus.model.Marca;
import br.com.sicredi.treinamento.revendedora_plus.service.IMarcaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/marcas")
public class MarcasController {

    private IMarcaService serv;

    public MarcasController(IMarcaService serv) {
        this.serv = serv;
    }

    @GetMapping("/")
    public ResponseEntity<List<Marca>> listar() {
        return ResponseEntity.ok(serv.listar());
    }

    @PostMapping("/")
    public ResponseEntity criar(@RequestBody Marca marca) {
        marca.setId(null);
        serv.salvar(marca);
        return ResponseEntity.created(URI.create("marcas/" + marca.getId())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity alterar(@PathVariable("id") Long id, @RequestBody Marca marca) {
        if (serv.consultar(id).isPresent()) {
            marca.setId(id);
            serv.salvar(marca);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        if (serv.consultar(id).isPresent()) {
            serv.excluir(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
