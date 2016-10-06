package com.retail.app.processor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoSocketOpenException;
import com.retail.app.repository.ProductPriceInfoRepository;
import com.retail.app.to.Item;
import com.retail.app.to.ProductPriceInfo;
import com.retail.app.util.Constants;
import com.retail.app.util.WebServiceUtil;

/**
 * @author Libin
 * This is the helper class 
 */
@Service
public class ProductDetailsManager {
	private static final Logger logger = LoggerFactory.getLogger(ProductDetailsManager.class);

	@Autowired
	WebServiceUtil webServiceUtil;

	@Autowired
	private ProductPriceInfoRepository productPriceInfoRepository;

	/*
	 * Retrieve the product name from external API
	 * based on product id
	 */
	public String getProductName(Integer id) throws Exception {
		String productName = null;
		String errorMessage = null;
		String serviceURL = "https://" + Constants.HOST_NAME + Constants.REQUEST_PATH_URL + id
				+ Constants.PARAMETERS_URL;
		List<Item> itemList = webServiceUtil.generateWebServiceCallForProductName(serviceURL);
		if (itemList != null && itemList.size() > 0) {
			if (itemList.get(0).getErrors().size() > 0) {
				errorMessage = itemList.get(0).getErrors().get(0).getMessage();
				return "error :" + errorMessage;
			}
			logger.debug("Product Name => " + itemList.get(0).getGeneralDescription());
			productName = itemList.get(0).getGeneralDescription();
		}
		return productName;
	}
	
	/*
	 * Retrieve the product id ,product price and currency code from MongoDB
	 * based on product id
	 */
	public ProductPriceInfo getProductPriceInfo(String id) throws IllegalArgumentException, MongoSocketOpenException{
		ProductPriceInfo productPriceInfo = null;
        try{
		productPriceInfo = productPriceInfoRepository.findOne(id);
        }catch(IllegalArgumentException iaex){
        	iaex.printStackTrace();
        	throw new IllegalArgumentException(Constants.INVALID_PRODUCT_ID);
        }catch(MongoSocketOpenException mse){
        	mse.printStackTrace();
        	throw new MongoSocketOpenException(Constants.ERR_MSG_DB_DOWN, null, mse);
        }
		return productPriceInfo;
	}

}
