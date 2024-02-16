package com.example.qpassessment.grocery.repository;

import java.util.Optional;

import com.example.qpassessment.grocery.entity.SaleOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<SaleOrder, Long> {

    Optional<SaleOrder> findByCode(String code);

}
