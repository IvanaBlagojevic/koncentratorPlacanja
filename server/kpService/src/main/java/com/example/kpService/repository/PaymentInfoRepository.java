package com.example.kpService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kpService.domain.PaymentInfo;
import com.example.kpService.domain.PaymentStatus;

@Repository
public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Long>{

	PaymentInfo findOneByOrderNumberIdAndPaymentMethod(Long id, String method);

	List<PaymentInfo> findAllByIsPaidNot(PaymentStatus paid);

	List<PaymentInfo> findAllByIsPaid(PaymentStatus paid);

}
