package br.com.zup.extrato.kafka;

import br.com.zup.extrato.domain.enums.TipoOperacao;
import br.com.zup.extrato.domain.repository.TransacaoRepository;
import br.com.zup.extrato.kafka.message.TransacaoMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
@EnableSpringDataWebSupport
class KafkaConsumerTest {

    @Autowired
    private TransacaoRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    @Transactional
    void deveSalvarUmNovoEvento() {
        // cenario
        TransacaoMessage transacaoMessage = new TransacaoMessage(TipoOperacao.DEPOSITO, new BigDecimal("10.0"),
                LocalDateTime.now(), "1", "1");
        KafkaConsumer kafkaConsumer = new KafkaConsumer(repository);

        // acao
        kafkaConsumer.consome(transacaoMessage);

        // validacao
        Assertions.assertEquals(1, repository.count());

    }

}