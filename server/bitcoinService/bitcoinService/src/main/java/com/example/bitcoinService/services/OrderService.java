package com.example.bitcoinService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bitcoinService.domain.MyOrder;
import com.example.bitcoinService.domain.OrderStatusEnum;
import com.example.bitcoinService.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository or;

	public MyOrder save(MyOrder o) {
		// TODO Auto-generated method stub
		return or.save(o);
	}

	public List<MyOrder> findAll() {
		// TODO Auto-generated method stub
		return or.findAll();
	}

	public List<MyOrder> findAllByStatus(OrderStatusEnum status) {
		// TODO Auto-generated method stub
		return or.findAllByStatus(status);
	}

	public MyOrder findOneByRandomUniqueID(String oid) {
		// TODO Auto-generated method stub
		return or.findOneByRandomUniqueID(oid);
	}
	
	

}
