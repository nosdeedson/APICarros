package br.com.APICarros.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.activation.DataSource;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.APICarros.model.Carro;
import br.com.APICarros.model.Favorito;
import br.com.APICarros.model.User;
import br.com.APICarros.repository.Favoritos;
import br.com.APICarros.repository.Users;

@RestController
@RequestMapping("/favoritos")
public class FavoritosResource {
	
	@Autowired
	private Favoritos favoritos;
	
	@Autowired
	private Users users;
	
		
	@PostMapping
	public Favorito save(@RequestBody Favorito favorito) {
		
		return favoritos.save(favorito);
	}
	
	@GetMapping("/{id}") // id do usuario
	public List<Carro> getFavoritos( @PathVariable Long id){
		
		User user = users.getOne(id);
		List<Favorito> listaFavoritos = favoritos.findAll();
		
		List<Carro> retorno = new ArrayList<Carro>();
		
		for (Favorito favorito : listaFavoritos) {
			if ( favorito.getUser().getId() == user.getId()) {
				retorno.add(favorito.getCarro());
			}
		}
		
		
		return retorno;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletar(@PathVariable Long id){
		Favorito favorito = favoritos.getOne(id);
		
		if( favorito == null) {
			return ResponseEntity.notFound().build();
		}
		favoritos.delete(favorito);
		
		return ResponseEntity.ok("Deletado com sucesso!!");
		
	}

}
