package com.example.qpassessment.grocery.repository;

import java.util.List;
import java.util.Optional;

import com.example.qpassessment.grocery.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByCode(String code);

    void deleteByCode(String code);

    List<Item> findAllByCodeIn(List<String> codes);

}
