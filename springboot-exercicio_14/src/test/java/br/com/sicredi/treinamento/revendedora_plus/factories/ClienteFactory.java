package br.com.sicredi.treinamento.revendedora_plus.factories;

import br.com.sicredi.treinamento.revendedora_plus.model.Cliente;
import java.math.BigDecimal;

public class ClienteFactory {

  public static Cliente build(
      String nome,
      String documento,
      BigDecimal rendaFamiliar,
      String telefone,
      String endereco,
      String email) {
    Cliente cliente = new Cliente();
    cliente.setNome(nome);
    cliente.setDocumento(documento);
    cliente.setRendaFamiliar(rendaFamiliar);
    cliente.setTelefone(telefone);
    cliente.setEndereco(endereco);
    cliente.setEmail(email);
    return cliente;
  }
}
