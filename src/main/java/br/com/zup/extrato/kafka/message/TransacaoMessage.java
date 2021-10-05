package br.com.zup.extrato.kafka.message;

import br.com.zup.extrato.domain.enums.TipoOperacao;
import br.com.zup.extrato.domain.modelo.Transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoMessage {

    private TipoOperacao operacao;

    private BigDecimal valor;

    private LocalDateTime data;

    private String idCliente;

    private Long numeroConta;

    public TransacaoMessage(){}

    public TransacaoMessage(TipoOperacao operacao, BigDecimal valor, LocalDateTime data, String idCliente, Long numeroConta) {
        this.operacao = operacao;
        this.valor = valor;
        this.data = data;
        this.idCliente = idCliente;
        this.numeroConta = numeroConta;
    }

    public void setOperacao(TipoOperacao operacao) {
        this.operacao = operacao;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public void setNumeroConta(Long numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Transacao messageToDomain(){
        return new Transacao(this.operacao, this.valor, this.data, this.idCliente, this.numeroConta);
    }
}
