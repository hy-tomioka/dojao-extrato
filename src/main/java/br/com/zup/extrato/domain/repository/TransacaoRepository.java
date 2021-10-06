package br.com.zup.extrato.domain.repository;

import br.com.zup.extrato.domain.modelo.Transacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransacaoRepository extends PagingAndSortingRepository<Transacao, Long>, CustomTransacaoRepository {

}
