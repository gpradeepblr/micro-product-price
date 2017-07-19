package com.pg.pp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepository extends CrudRepository<ProductPrice, Long> {
	
	ProductPrice findByProductId(Long productId);
}