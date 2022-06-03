package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping("/cozinha/por-nome/contendo")
	public List<Cozinha> consultaPorNomeContendo(String nome) {
		return cozinhaRepository.findTodasCozinhasByNomeContaining(nome);
	}

	@GetMapping("cozinha/por-nome")
	public Optional<Cozinha> consultaPorNome(String nome) {
		return cozinhaRepository.findByNome(nome);
	}
	
	@GetMapping("cozinha/primeiro")
	public Optional<Cozinha> cozinhaPrimeiro() {
		return cozinhaRepository.buscarPrimeiro();
	}
	
	
	@GetMapping("restaurante/primeiro-por-nome")
	public Optional<Restaurante> primeiroPorNome(String nome) {
		return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
	}
	
	
	@GetMapping("/restaurante/por-taxa-frete")
	public List<Restaurante> consultaPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}
	
	@GetMapping("/restaurante/top2-por-nome")
	public List<Restaurante> restaurantesTop2PorNome(String nome) {
		return restauranteRepository.findTop2RestauranteByNomeContaining(nome);
	}
	
	@GetMapping("/cozinha/exists")
	public boolean cozinhaExists(String nome) {
		return cozinhaRepository.existsByNome(nome);
	}
	
	@GetMapping("/restaurantes/por-cozinha")
	public int cozinhaExists(Long cozinhaId) {
		return restauranteRepository.countByCozinhaId(cozinhaId);
	}
	
	@GetMapping("/restaurantes/consultar-por-nome")
	public List<Restaurante> consultarPornome(String nome, Long cozinhaId) {
		return restauranteRepository.consultarPorNome(nome, cozinhaId);
	}
	
	@GetMapping("/restaurantes/nome-e-taxafrete")
	public List<Restaurante> consultaPorNomeeTaxaFrete(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}
	
	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome) {
		
		return restauranteRepository.findComFreteGratis(nome);
	}
	
	@GetMapping("/restaurantes/primeiro")
	public Optional<Restaurante> restaurantePrimeiro() {
		return restauranteRepository.buscarPrimeiro();
	}
	
	
	
	
	
}
