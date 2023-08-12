package com.example.sales.repositories;

import com.example.sales.entities.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalesRepository extends JpaRepository<Sales, Long> {
    Optional<Sales> findByRecno(int recno);

}
