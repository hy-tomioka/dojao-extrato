package br.com.zup.extrato.domain.repository;

import br.com.zup.extrato.domain.modelo.Transacao;

import java.util.List;

public interface CustomTransacaoRepository {

    List<Transacao> findClienteOrdenadorPorData(String idCliente, Long numeroConta, int tamanhoExtrato);
}
