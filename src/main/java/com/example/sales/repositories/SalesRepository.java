package com.example.sales.repositories;

import com.example.sales.SalesSummary;
import com.example.sales.entities.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SalesRepository extends JpaRepository<Sales, Long> {
    Optional<Sales> findByRecno(int recno);

}
