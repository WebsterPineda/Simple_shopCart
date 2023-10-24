package com.webster.repository;

import com.webster.models.OrdenCompraE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenCompraERepository extends JpaRepository<OrdenCompraE, Long> {
}
