package com.retail.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.retail.app.to.ProductPriceInfo;

public interface ProductPriceInfoRepository extends MongoRepository<ProductPriceInfo, String>{

}
