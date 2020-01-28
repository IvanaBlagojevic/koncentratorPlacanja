package com.example.authService.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import com.example.authService.domain.User;
import com.example.authService.domain.UserRole;



public class UserDTO {

	private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private List<UserRoleDTO> roles;
    
    
    public UserDTO() {}

    public UserDTO(User user){
		this.id = user.getId();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.roles = new ArrayList<>();
		for(UserRole role : user.getRoles()){
			this.roles.add(new UserRoleDTO(role));
		}
		
	}
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public List<UserRoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRoleDTO> roles) {
		this.roles = roles;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
