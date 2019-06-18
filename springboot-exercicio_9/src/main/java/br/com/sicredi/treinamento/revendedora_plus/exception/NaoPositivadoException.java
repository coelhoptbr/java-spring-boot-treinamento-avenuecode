package br.com.sicredi.treinamento.revendedora_plus.exception;

public class NaoPositivadoException extends Exception {

    private String mensagem;

    public NaoPositivadoException(String mensagem) {
        this.mensagem = mensagem;
    }
}
