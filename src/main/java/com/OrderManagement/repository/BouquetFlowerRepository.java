package com.OrderManagement.repository;

import com.OrderManagement.model.BouquetFlower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BouquetFlowerRepository extends JpaRepository<BouquetFlower, Long> {
}
