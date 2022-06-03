package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

@Service
public class CadastroPermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	public Permissao salvar(Permissao permissao) {
		return permissaoRepository.save(permissao);
	}
	
	public void excluir(Long permissaoId) {
		try {
			permissaoRepository.deleteById(permissaoId);
		} catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de Permissao como código %d", permissaoId));
		} catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Permissão de código %d não pode ser removida pois está em uso", permissaoId));
		}
	}
}
