package br.com.APICarros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.APICarros.model.Favorito;

public interface Favoritos extends JpaRepository<Favorito, Long>  {

}
