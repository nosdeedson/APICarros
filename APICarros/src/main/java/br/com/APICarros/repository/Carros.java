package br.com.APICarros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.APICarros.model.Carro;

public interface Carros extends JpaRepository<Carro, Long>{

}
