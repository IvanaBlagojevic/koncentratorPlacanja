package com.example.payPalService.converter;

import org.springframework.stereotype.Component;

import com.example.payPalService.domain.UserPayPal;
import com.example.payPalService.dto.UserPayPalDTO;

@Component
public class PayPalConverter {

	public UserPayPal convert(UserPayPalDTO user)
	{
		return new UserPayPal(user.getUsername(),user.getClientId(), user.getClientSecret());
	}
	
	public UserPayPalDTO convert(UserPayPal user)
	{
		return new UserPayPalDTO(user);
	}
}
