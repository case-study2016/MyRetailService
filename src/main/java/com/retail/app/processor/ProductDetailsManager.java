package com.retail.app.processor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.app.repository.ProductPriceInfoRepository;
import com.retail.app.to.Item;
import com.retail.app.to.ProductPriceInfo;
import com.retail.app.util.Constants;
import com.retail.app.util.WebServiceUtil;

@Service
public class ProductDetailsManager {

	@Autowired
	WebServiceUtil webServiceUtil;
	
    @Autowired
    private ProductPriceInfoRepository productPriceInfoRepository; 
    
	public String getProductName(Integer id) throws Exception {
		String productName = null;
		String errorMessage = null;
        String serviceURL = "https://"+Constants.HOST_NAME + Constants.REQUEST_PATH_URL + id + Constants.PARAMETERS_URL;
        List<Item> itemList = webServiceUtil.generateWebServiceCallForProductName(serviceURL);
        if(itemList != null && itemList.size() > 0){
        	if(itemList.get(0).getErrors().size() >0){
        		errorMessage = itemList.get(0).getErrors().get(0).getMessage();
                return "error :"+errorMessage;
        	}
        	System.out.println("Product Name >>>" +itemList.get(0).getGeneralDescription());
        	productName = itemList.get(0).getGeneralDescription();
        }
        return productName;
	}
	public ProductPriceInfo getProductPriceInfo(String id) {
		ProductPriceInfo productPriceInfo = null;
		/* TODO 
			Create  a helper service to insert following records to mongodb
			1. product id 
		    2. product price
		    3. currency code
		    
		    Retrieve the above information 2, 3 from db based on product id
		    
		    
		    
		 * */
		
		productPriceInfo = productPriceInfoRepository.findOne(id);
		return productPriceInfo;
	}


}
