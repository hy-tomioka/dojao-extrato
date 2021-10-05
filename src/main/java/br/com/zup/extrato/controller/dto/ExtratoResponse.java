package br.com.zup.extrato.controller.dto;

import br.com.zup.extrato.domain.enums.TipoOperacao;
import br.com.zup.extrato.domain.modelo.Transacao;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public class ExtratoResponse {

    private TipoOperacao tipoOperacao;

    private LocalDateTime data;

    private BigDecimal valor;

    public ExtratoResponse(Transacao transacao) {
        this.tipoOperacao = transacao.getOperacao();
        this.data = transacao.getData();
        this.valor = transacao.getValor();
    }

    public LocalDateTime getData() {
        return data;
    }
}
