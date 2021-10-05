package br.com.zup.extrato.domain.repository;

import br.com.zup.extrato.domain.modelo.Transacao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomTransacaoRepositoryImpl implements CustomTransacaoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Transacao> findClienteOrdenadorPorData(String idCliente, Long numeroConta, int limit) {
        return entityManager.createQuery("SELECT t FROM Transacao t WHERE t.idCliente = :idCliente" +
                " AND t.numeroConta = :numeroConta ORDER BY t.data DESC", Transacao.class)
                .setParameter("idCliente", idCliente)
                .setParameter("numeroConta", numeroConta)
                .setMaxResults(limit)
                .getResultList();
    }
}
