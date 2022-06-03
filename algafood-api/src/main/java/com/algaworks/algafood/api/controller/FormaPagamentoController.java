package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping("/formapagamento")
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamento;
	
	@GetMapping
	public List<FormaPagamento> listar() {
		return formaPagamentoRepository.findAll();
	}
	
	@GetMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamento> buscar(@PathVariable Long formaPagamentoId) {
		Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findById(formaPagamentoId);
		
		if(formaPagamento.isPresent()) {
			return ResponseEntity.ok(formaPagamento.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamento adicionar(@RequestBody FormaPagamento formaPagamento) {
		return formaPagamentoRepository.save(formaPagamento);
	}
	
	@PutMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamento> atualizar(@RequestBody FormaPagamento formaPagamento, @PathVariable Long formaPagamentoId) {
		Optional<FormaPagamento> formaPagamentoAtual = formaPagamentoRepository.findById(formaPagamentoId);
		if(formaPagamentoAtual.isPresent()) {
			BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual.get(),"id");
			FormaPagamento formaPagamentoSalva = formaPagamentoRepository.save(formaPagamentoAtual.get());
			return ResponseEntity.ok(formaPagamentoSalva);
		}
			return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	public ResponseEntity<?> remover(@PathVariable Long formaPagamentoId) {
		try {
			cadastroFormaPagamento.excluir(formaPagamentoId);
			return ResponseEntity.noContent().build();
		} catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch(EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

}
