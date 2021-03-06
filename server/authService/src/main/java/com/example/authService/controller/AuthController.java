package com.example.authService.controller;

import java.io.IOException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.authService.domain.User;
import com.example.authService.dto.UserDTO;
import com.example.authService.security.JwtProvider;
import com.example.authService.security.JwtResponse;
import com.example.authService.security.LoginForm;
import com.example.authService.service.UserRoleService;
import com.example.authService.service.UserService;


@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "https://localhost:1234")
public class AuthController {


	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired 
	private UserService userServ;

	@Autowired
	private UserRoleService roleServ;
	
	
	@Autowired
    private PasswordEncoder encoder;
	
	@Autowired
    private JwtProvider jwtProvider;
	

	
	@PostMapping(value = "/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm login) throws IOException{
		
		Optional<User> user = userServ.getByEmail(login.getEmail());
		
		if(user.isPresent())
		{
			try {
				
				Authentication authentication = authenticationManager.authenticate(
		                new UsernamePasswordAuthenticationToken(
		                		login.getEmail(),
		                        login.getPassword()
		                )
		        );
				SecurityContextHolder.getContext().setAuthentication(authentication);
				String jwt = jwtProvider.generateJwtToken(authentication);
		        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		        System.out.println("Stigao token : "+jwt);
		        System.out.println("Username: " + userDetails.getUsername());
		        System.out.println("Password: " + userDetails.getPassword());
		        return new ResponseEntity<>(new JwtResponse(jwt), HttpStatus.OK);
		        
			}catch(AuthenticationException e) {
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
			
		}else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
   }
	
	@RequestMapping(value = "/logout/{id}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> logout(@PathVariable("id") String email) {
		Optional<User> user = userServ.getByEmail(email);
		return new ResponseEntity<>(new UserDTO(user.get()), HttpStatus.OK);
	}
	
	
	
}
