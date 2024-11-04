package com.juan.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.juan.example.entity.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}