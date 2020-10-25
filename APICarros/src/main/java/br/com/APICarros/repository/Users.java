package br.com.APICarros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.APICarros.model.User;

public interface Users extends JpaRepository<User, Long> {

}
