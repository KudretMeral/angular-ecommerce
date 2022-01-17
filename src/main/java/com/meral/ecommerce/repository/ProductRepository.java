package com.meral.ecommerce.repository;

import com.meral.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {



//Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);

    List<Product> findByCategoryId(Long id);

    List<Product> findByNameContaining(String name);

}
