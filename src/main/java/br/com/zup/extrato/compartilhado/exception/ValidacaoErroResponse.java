package br.com.zup.extrato.compartilhado.exception;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public class ValidacaoErroResponse {

    private final String mensagem;

    public ValidacaoErroResponse(String mensagem) {
        this.mensagem = mensagem;
    }
}
