package br.com.zup.extrato.domain.repository;

import br.com.zup.extrato.domain.modelo.Transacao;
import org.springframework.integration.annotation.Default;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.springframework.util.Assert.notNull;
import static org.springframework.util.Assert.state;

@Repository
public class CustomTransacaoRepositoryImpl implements CustomTransacaoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Transacao> findClienteOrdenadorPorData(String idCliente, String numeroConta, int tamanhoExtrato) {
        notNull(idCliente, "Id do cliente não pode ser nulo.");
        notNull(numeroConta, "Numero da conta do cliente não pode ser nulo.");
        state(tamanhoExtrato > 0, "Um extrato deve conter um número de transações maior que ZERO.");
        
        return entityManager.createQuery("SELECT t FROM Transacao t WHERE t.idCliente = :idCliente" +
                " AND t.numeroConta = :numeroConta ORDER BY t.data DESC", Transacao.class)
                .setParameter("idCliente", idCliente)
                .setParameter("numeroConta", numeroConta)
                .setMaxResults(tamanhoExtrato)
                .getResultList();
    }
}
