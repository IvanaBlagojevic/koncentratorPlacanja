package com.example.kpService.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.kpService.domain.MethodOfPayment;

@Repository
public interface MethodOfPaymentRepository extends JpaRepository<MethodOfPayment, Long>{
	
	List<MethodOfPayment> findAllByName(String name);
	
	List<MethodOfPayment> findAll();

	MethodOfPayment findOneById(Long id);

}



