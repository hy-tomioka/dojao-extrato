package br.com.zup.extrato.controller;

import br.com.zup.extrato.controller.dto.ExtratoResponse;
import br.com.zup.extrato.domain.modelo.Transacao;
import br.com.zup.extrato.domain.repository.TransacaoRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
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
    public ResponseEntity<List<ExtratoResponse>> lista(@PageableDefault(size = 20) Pageable paginacao,
                                                       @PathVariable String idCliente,
                                                       @PathVariable Long numeroConta,
                       @RequestParam(defaultValue = "20") @Max(value = 50, message = "Um extrato não pode conter mais de 50 transações.") Integer limit) {

        List<Transacao> extrato = repository.findClienteOrdenadorPorData(idCliente, numeroConta, limit);
        return ResponseEntity.ok(extrato.stream().map(ExtratoResponse::new).collect(Collectors.toList()));
    }
}