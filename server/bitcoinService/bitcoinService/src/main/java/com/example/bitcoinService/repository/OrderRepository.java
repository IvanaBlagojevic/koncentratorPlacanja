package com.example.bitcoinService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bitcoinService.domain.MyOrder;
import com.example.bitcoinService.domain.OrderStatusEnum;

@Repository
public interface OrderRepository extends JpaRepository<MyOrder, Long> {

	List<MyOrder> findAllByStatus(OrderStatusEnum status);

	MyOrder findOneByRandomUniqueID(String oid);

	MyOrder findOneById(Long oid);

	MyOrder findOneByPaymentId(String string);

}
