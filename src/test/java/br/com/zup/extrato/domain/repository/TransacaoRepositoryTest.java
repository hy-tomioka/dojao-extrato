package br.com.zup.extrato.domain.repository;

import br.com.zup.extrato.domain.enums.TipoOperacao;
import br.com.zup.extrato.domain.modelo.Transacao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TransacaoRepositoryTest {

    @Autowired
    TransacaoRepository repository;

    private final String ID_CLIENTE = "1234";
    private final String NUMERO_CONTA = "4321";
    private final LocalDateTime AGORA = LocalDateTime.now();


    @Test
    void deveRetornar10UltimasTransacoes() {

        repository.deleteAll();
        insereTransacoes(20);

        List<Transacao> extrato = repository.findClienteOrdenadorPorData(ID_CLIENTE, NUMERO_CONTA, 10);

        assertEquals(10, extrato.size());
        assertEquals(AGORA.getDayOfYear(), extrato.get(0).getData().getDayOfYear());
        assertEquals(AGORA.minusDays(9).getDayOfYear(), extrato.get(extrato.size()-1).getData().getDayOfYear());
    }

    @Test
    void naoDeveRetornarTransacoesDeClienteEContaInexistente() {
        repository.deleteAll();
        insereTransacoes(5);

        List<Transacao> extrato = repository.findClienteOrdenadorPorData("2222", "2222", 10);

        assertEquals(0, extrato.size());
    }

    private void insereTransacoes(int total) {
        List<Transacao> transacoes = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            transacoes.add(new Transacao(
                    TipoOperacao.DEPOSITO,
                    BigDecimal.TEN,
                    AGORA.minusDays(i),
                    ID_CLIENTE,
                    NUMERO_CONTA
            ));
        }
        repository.saveAll(transacoes);
    }
}