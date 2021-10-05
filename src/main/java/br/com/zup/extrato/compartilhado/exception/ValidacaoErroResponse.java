package br.com.zup.extrato.compartilhado.exception;

public class ValidacaoErroResponse {

    private String field;
    private String mensagem;

    public ValidacaoErroResponse(String field, String mensagem) {
        this.field = field;
        this.mensagem = mensagem;
    }

    public String getField() {
        return field;
    }

    public String getMensagem() {
        return mensagem;
    }
}
