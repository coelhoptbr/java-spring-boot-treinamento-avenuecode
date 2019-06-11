package com.treinamento.testetreina.controller;

import com.treinamento.testetreina.model.Marca;
import com.treinamento.testetreina.service.IMarcasService;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/marcas")
public class MarcasController {

    private IMarcasService servico;

    public MarcasController(IMarcasService servico) {
        this.servico = servico;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> consultar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(servico.listar().get(id.intValue()));
    }

    @GetMapping("/")
    public ResponseEntity<List<Marca>> consultar() {
        return ResponseEntity.ok(servico.listar());
    }
}
