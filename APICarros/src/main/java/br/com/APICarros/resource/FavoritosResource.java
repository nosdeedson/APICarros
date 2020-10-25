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
import br.com.APICarros.service.NativeScriptFavoritosService;

@RestController
@RequestMapping("/favoritos")
public class FavoritosResource {
	
	@Autowired
	private Favoritos favoritos;
	
	@Autowired
	private Users users;
	
	@Autowired
	private NativeScriptFavoritosService nsfs= new NativeScriptFavoritosService();
	
		
	@PostMapping
	public Favorito save(@RequestBody Favorito favorito) {
		
		return favoritos.save(favorito);
	}
	
	@GetMapping("/{id}") // id do usuario
	public List<Carro> getFavoritos( @PathVariable Long id){
		
		User user = users.getOne(id);
		String sql = "select c from Favorito f, Carro c where f.user.id= :id and f.carro.id=c.id";
		List<Carro> carros = nsfs.listaFavorito(user.getId(), sql);		
		
		return carros;
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
