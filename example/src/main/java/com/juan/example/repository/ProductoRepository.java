package com.juan.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.juan.example.entity.Product;

public interface ProductoRepository extends JpaRepository<Product,Long>{ }