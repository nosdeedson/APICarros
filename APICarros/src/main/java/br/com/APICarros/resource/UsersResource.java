package br.com.APICarros.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.APICarros.model.User;
import br.com.APICarros.repository.Users;

@RestController
@RequestMapping("/users")
public class UsersResource {
	
	@Autowired
	private Users users;
	
	@PostMapping
	public User save(@RequestBody User user) {
		return users.save(user);
	}
	
}
