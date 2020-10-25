package br.com.APICarros.service;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.APICarros.model.Carro;

@Service
public class NativeScriptService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public List<String> tiposCarros(String sql, int tipoVeiculo) {
		
		
		TypedQuery<String> lista = this.em.createQuery(sql, String.class).setParameter("tipoVeiculo", tipoVeiculo);
		
		return lista.getResultList();
	}
	
	@Transactional
	public List<String> modelos(String sql, int tipoVeiculo, String marca) {
		
		TypedQuery<String> lista = this.em.createQuery(sql, String.class)
				.setParameter("marca", marca)
				.setParameter("tipoVeiculo", tipoVeiculo);
		
		return lista.getResultList();
	}
	
	@Transactional
	public List<Integer> anos(String sql, int tipoVeiculo, String marca) {
		
		TypedQuery<Integer> lista = this.em.createQuery(sql, Integer.class)
				.setParameter("marca", marca)
				.setParameter("tipoVeiculo", tipoVeiculo);
		
		return lista.getResultList();
	}
	
	@Transactional
	public BigDecimal valorVeiculo(String sql, int tipoVeiculo, String marca, int anoModelo) {
		
		BigDecimal valor = this.em.createQuery(sql, BigDecimal.class)
				.setParameter("marca", marca)
				.setParameter("anoModelo", anoModelo)
				.setParameter("tipoVeiculo", tipoVeiculo).setMaxResults(1).getSingleResult();
		
		return valor;
	}
	
	@Transactional
	public List<Carro> veiculos(String sql, int tipoVeiculo, String marca, int anoModelo) {
		
		TypedQuery<Carro> carros = this.em.createQuery(sql, Carro.class)
				.setParameter("marca", marca)
				.setParameter("anoModelo", anoModelo)
				.setParameter("tipoVeiculo", tipoVeiculo);
		
		return carros.getResultList();
	}
	

}
