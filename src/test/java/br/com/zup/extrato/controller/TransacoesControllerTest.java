package br.com.zup.extrato.controller;

import br.com.zup.extrato.domain.repository.TransacaoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
//@EnableAutoConfiguration(exclude = KafkaAutoConfiguration.class)
class TransacoesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransacaoRepository repository;

    @Test
    void deveRetornarUmaListaDeExtrato() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(
                "/api/v1/extratos/4321/contas/1234");

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    void deveRetornarUmaListaDeExtratoCom50Transacoes() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(
                "/api/v1/extratos/4321/contas/1234?tamanhoExtrato=50");

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    void deveRetornarErroAoTentarListarMaisDe50Transacoes() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(
                "/api/v1/extratos/4321/contas/1234?tamanhoExtrato=51");

        mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornarErroAoTentarListarZeroTransacoes() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(
                "/api/v1/extratos/4321/contas/1234?tamanhoExtrato=0");

        mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornarErroAoTentarListarNumeroNegativoDeTransacoes() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(
                "/api/v1/extratos/4321/contas/1234?tamanhoExtrato=-10");

        mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }
}