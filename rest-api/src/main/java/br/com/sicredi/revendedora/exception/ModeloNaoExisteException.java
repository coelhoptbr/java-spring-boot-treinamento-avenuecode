package br.com.sicredi.revendedora.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(BAD_REQUEST)
public class ModeloNaoExisteException extends Exception {

  public ModeloNaoExisteException(String msg) {
    super(msg);
  }
}
