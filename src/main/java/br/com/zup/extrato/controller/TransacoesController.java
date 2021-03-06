package br.com.zup.extrato.controller;

import br.com.zup.extrato.compartilhado.validation.TamanhoExtrato;
import br.com.zup.extrato.controller.dto.ExtratoResponse;
import br.com.zup.extrato.domain.modelo.Transacao;
import br.com.zup.extrato.domain.repository.TransacaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
public class TransacoesController {

    private final TransacaoRepository repository;

    TransacoesController(TransacaoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("api/v1/extratos/{idCliente}/contas/{numeroConta}")
    public ResponseEntity<List<ExtratoResponse>> lista(
            @PathVariable String idCliente,
            @PathVariable String numeroConta,
            @RequestParam(defaultValue = "20") @TamanhoExtrato Integer tamanhoExtrato
    ) {

        List<Transacao> extrato = repository.findClienteOrdenadorPorData(idCliente, numeroConta, tamanhoExtrato);
        return ResponseEntity.ok(extrato.stream().map(ExtratoResponse::new).collect(Collectors.toList()));
    }
}