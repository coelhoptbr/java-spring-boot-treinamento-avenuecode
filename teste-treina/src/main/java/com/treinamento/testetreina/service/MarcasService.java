package com.treinamento.testetreina.service;

import com.treinamento.testetreina.model.Marca;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarcasService implements IMarcasService {

    @Override
    public List<Marca> listar() {
        var lista = new ArrayList<Marca>();

        for (int i = 1; i < 50; i++) {
            var marca = new Marca();
            marca.setId(Long.valueOf(i));
            marca.setNome(RandomString.make(50));
            lista.add(marca);
        }
        return lista;
    }

}
