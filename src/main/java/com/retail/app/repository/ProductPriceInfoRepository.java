package com.retail.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.retail.app.to.ProductPriceInfo;

/**
 * @author Libin
 * This interface provides ProductPriceInfoRepository
 */
public interface ProductPriceInfoRepository extends MongoRepository<ProductPriceInfo, String> {

}
