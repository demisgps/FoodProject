package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

@Service
public class CadastroFormaPagamentoService {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return formaPagamentoRepository.save(formaPagamento);
	}
	
	public void excluir(Long formaPagamentoId) {
		try {
			formaPagamentoRepository.deleteById(formaPagamentoId);
		} catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de forma de pagamento com código %d.", formaPagamentoId));
		} catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Código de Forma Pagamento %d não pode ser removido pois está em uso.",formaPagamentoId));
		}
	}
}
