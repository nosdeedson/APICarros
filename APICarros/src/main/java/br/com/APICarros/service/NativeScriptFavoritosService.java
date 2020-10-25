package br.com.APICarros.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.APICarros.model.Carro;

@Service
public class NativeScriptFavoritosService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public List<Carro> listaFavorito( Long idUser, String sql){
		TypedQuery<Carro> carros = this.em.createQuery(sql, Carro.class)
				.setParameter("id", idUser);
		
		return carros.getResultList();
	}

}
