package br.com.zup.extrato.kafka;

import br.com.zup.extrato.domain.repository.TransacaoRepository;
import br.com.zup.extrato.kafka.message.TransacaoMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class KafkaConsumer {

    private final TransacaoRepository transacaoRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    public KafkaConsumer(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.transactions}", containerFactory = "kafkaListenerContainerFactory")
    @Transactional
    public void consome(@Payload TransacaoMessage transacaoMessage) {
        try {
            var response = transacaoRepository.save(transacaoMessage.messageToDomain());
            LOGGER.info("Registrando a transacao: {}",response.getUuid());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
