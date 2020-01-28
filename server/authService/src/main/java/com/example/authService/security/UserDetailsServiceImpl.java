package com.example.authService.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.authService.domain.Permission;
import com.example.authService.domain.User;
import com.example.authService.domain.UserRole;
import com.example.authService.repository.UserRepository;




@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRep;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRep.findByEmail(arg0)
				.orElseThrow(() -> 
                new UsernameNotFoundException("User Not Found with -> email : " + arg0)
				);
		return getUserPrincipal(user);
	}
	
	private UserPrinciple getUserPrincipal(User user) {
		Stream<String> roles = user.getRoles().stream()
				.map(UserRole::getName)
				.map(Enum::name);

		Stream<String> permissions = user.getRoles().stream()
				.map(UserRole::getPermissions)
				.flatMap(Collection::stream)
				.map(Permission::getName);

		List<GrantedAuthority> authorities = Stream.concat(roles, permissions)
				.distinct()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		return new UserPrinciple(user.getId(),user.getName(),user.getSurname(), user.getPassword(), user.getEmail(), authorities);
	}

}
