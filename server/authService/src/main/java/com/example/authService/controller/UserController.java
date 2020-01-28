package com.example.authService.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.authService.domain.User;
import com.example.authService.dto.UserDTO;
import com.example.authService.service.UserService;



@RestController
@RequestMapping("user")
@CrossOrigin(origins = "https://localhost:1234")
public class UserController {

	@Autowired
	private UserService userServ;
	
	
	
	@RequestMapping(value = "/email/{id}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> getUserByEmail(@PathVariable("id") String email) {

		System.out.println("Pogodio get po mailu");
		System.out.println("Stigao mejl: " + email);
		Optional<User> user = userServ.getByEmail(email);
		if (!user.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(new UserDTO(user.get()), HttpStatus.OK);
		}
	}
	
	/*@RequestMapping(value = "/add",method=RequestMethod.POST,consumes="application/json")
	public ResponseEntity<?> newUser(@RequestBody UserDTO user)
	{
		System.out.println("stiglo, email: " + user.getEmail() + ", " + user.getSurname() + ", " + user.getName());
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setSurname(user.getSurname());
		newUser.setName(user.getName());
		//userServ.addNewUser(newUser);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get",method=RequestMethod.GET)
	public ResponseEntity<List<User>> getByEmail()
	{
		List<User> users = userServ.findUserByEmail();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}*/
}
