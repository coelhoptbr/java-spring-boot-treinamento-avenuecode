package br.com.sicredi.treinamento.revendedora_plus.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

@ApiModel(description = "Dados relacionados ao recurso Cliente")
public class ClienteDTO {

  @ApiModelProperty(notes = "Nome do cliente")
  @NotNull
  private String nome;

  @ApiModelProperty(notes = "Documento do cliente")
  @NotNull
  private String documento;

  @ApiModelProperty(notes = "Renda Familiar do cliente")
  @NotNull
  private BigDecimal rendaFamiliar;

  @ApiModelProperty(notes = "Telefone do cliente")
  private String telefone;

  @ApiModelProperty(notes = "Endereço do cliente")
  private String endereco;

  @ApiModelProperty(notes = "Email do cliente")
  private String email;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDocumento() {
    return documento;
  }

  public void setDocumento(String documento) {
    this.documento = documento;
  }

  public BigDecimal getRendaFamiliar() {
    return rendaFamiliar;
  }

  public void setRendaFamiliar(BigDecimal rendaFamiliar) {
    this.rendaFamiliar = rendaFamiliar;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
