package com.retail.app.services;

import org.springframework.web.bind.annotation.RestController;

import com.retail.app.processor.ProductDetailsManager;
import com.retail.app.repository.ProductPriceInfoRepository;
import com.retail.app.to.CurrentPrice;
import com.retail.app.to.ErrorResponseTO;
import com.retail.app.to.ProductPriceInfo;
import com.retail.app.to.ProductResponseTO;


import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@CrossOrigin
@RestController
public class ProductsAPI {
    @Autowired
    ProductDetailsManager productDetailsManager;
    
    @Autowired
    private ProductPriceInfoRepository productPriceInfoRepository;    
    
    @RequestMapping(value="/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProductAndPriceDetails(@PathVariable Integer id) throws Exception {
    	ResponseEntity<?> responseEntity = null;
    	ProductPriceInfo productPriceInfo = null;
    	String output = null;
    	System.out.println("productId>>>>"+id);
        //call api.target.com external API through ProductDetailsManager
        //to get product name
        ErrorResponseTO errorResponseTO = new ErrorResponseTO();
        ProductResponseTO productResponseTO = new ProductResponseTO();

        if(id != null){
        	output = productDetailsManager.getProductName(id);
        	if(output.indexOf("error :") != -1){
    	       errorResponseTO.setErrorDesc(output.substring(7, output.length()));
    	       responseEntity = new ResponseEntity<>(errorResponseTO, HttpStatus.OK); 
        	}else{
    	       productResponseTO.setId(id);
    	       productResponseTO.setName(output);
    	       responseEntity = new ResponseEntity<>(productResponseTO, HttpStatus.OK);     	       
        	}        	
        }
        
        // Call pricing information from a NoSQL data store , MongoDB
        
       productPriceInfo = productDetailsManager.getProductPriceInfo(id.toString());
       System.out.println(productPriceInfo.getProductPrice());
       if(productPriceInfo.getProductPrice() != null && productPriceInfo.getCurrencyCode()!= null){
    	   CurrentPrice currentPrice = new CurrentPrice();
    	   currentPrice.setCurrencyCode(productPriceInfo.getCurrencyCode());
    	   currentPrice.setValue(productPriceInfo.getProductPrice());
    	   productResponseTO.setCurrentPrice(currentPrice);
       }
       return responseEntity;
        
        
    }
    //This created product price details in DB
    @RequestMapping(value="/products/price", method = RequestMethod.POST)
    public Map<String, Object> createProductPriceInfo(@RequestBody Map<String, Object> productPriceMap){
    	ProductPriceInfo productpriceinfo = new ProductPriceInfo(
    		  productPriceMap.get("productId").toString(), 
    		  new BigDecimal(productPriceMap.get("productPrice").toString()),
    		  productPriceMap.get("currencyCode").toString());
      
      Map<String, Object> response = new LinkedHashMap<String, Object>();
      response.put("message", "Product Price Info created successfully");
      response.put("productpriceinfo", productPriceInfoRepository.save(productpriceinfo));
      return response;
    }
    
    //This updated product price details in DB
    @RequestMapping(value="/products/{id}", method = RequestMethod.PUT)
    public Map<String, Object> updateProductPriceInfo(@PathVariable Integer id, 
        @RequestBody ProductResponseTO productRequestTO){
    	CurrentPrice currentPrice = null;
        currentPrice = productRequestTO.getCurrentPrice();
        BigDecimal value = currentPrice.getValue();
        String currencyCode = currentPrice.getCurrencyCode();
        ProductPriceInfo productpriceinfo = null;
        if(id != null && value != null && currencyCode != null){
        	productpriceinfo = new ProductPriceInfo(
        			id.toString(),
        			value,
        			currencyCode);
        }
      
      Map<String, Object> response = new LinkedHashMap<String, Object>();
      response.put("message", "Product Price Info Updated successfully");
      response.put("productpriceinfo", productPriceInfoRepository.save(productpriceinfo));
      return response;
    }    
    
}
