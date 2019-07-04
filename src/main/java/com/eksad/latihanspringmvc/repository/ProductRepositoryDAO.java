package com.eksad.latihanspringmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eksad.latihanspringmvc.model.Product;

public interface ProductRepositoryDAO extends JpaRepository<Product, Long>{

}
