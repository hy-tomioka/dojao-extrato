package br.com.zup.extrato.domain.repository;

import br.com.zup.extrato.domain.modelo.Transacao;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface TransacaoRepository extends PagingAndSortingRepository<Transacao, Long> {
}
