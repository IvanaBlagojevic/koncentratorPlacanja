package com.example.scientificCenter.service;

import java.util.Set;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.scientificCenter.domain.Recenzent;
import com.example.scientificCenter.domain.User;
import com.example.scientificCenter.domain.UserRole;
import com.example.scientificCenter.domain.UserRoleName;
import com.example.scientificCenter.repository.RecenzentRepository;


@Service
public class ConfirmationRecenzentService implements JavaDelegate{

	@Autowired
	IdentityService identityService;
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private JavaMailSender javaMailSender;
		
	@Autowired
	private UserService userService;
		
	@Autowired
	private ScientificAreaService areaService;
	
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RecenzentRepository recService;
		
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		User user = this.userService.findByUsername(execution.getVariable("username").toString());
		
		if(execution.getVariable("recenzent").toString().equals("true")) {
			UserRole role = this.userRoleService.findRoleByName(UserRoleName.ROLE_RECENZENT);
			user.setIsRecenzent(true);
			Set<UserRole> roles =user.getRoles();
			roles.clear();
			user.getRoles().add(role);
			Recenzent rec = new Recenzent(user);
			this.userService.delete(user);
			this.userService.save(rec);
		}
	}
	
}
