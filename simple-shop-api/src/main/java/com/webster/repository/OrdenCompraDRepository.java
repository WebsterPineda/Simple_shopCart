package com.webster.repository;

import com.webster.models.OrdenCompraD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenCompraDRepository extends JpaRepository<OrdenCompraD, Long> {
}
