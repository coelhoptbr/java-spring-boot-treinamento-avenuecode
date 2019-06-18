package br.com.sicredi.treinamento.revendedora_plus.exception;

public class NaoEncontradoException extends Exception {

    private String mensagem;

    public NaoEncontradoException(String mensagem) {
        this.mensagem = mensagem;
    }
}
