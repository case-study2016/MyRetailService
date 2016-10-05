package com.retail.app.services;

import org.springframework.web.bind.annotation.RestController;

import com.mongodb.MongoSocketOpenException;
import com.retail.app.exceptions.BadRequestException;
import com.retail.app.exceptions.NotFoundException;
import com.retail.app.processor.ProductDetailsManager;
import com.retail.app.repository.ProductPriceInfoRepository;
import com.retail.app.to.CurrentPrice;
import com.retail.app.to.ErrorResponseTO;
import com.retail.app.to.ProductPriceInfo;
import com.retail.app.to.ProductResponseTO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Libin
 * This class exposes Products API
 * Added @CrossOrigin to avoid cross domain
 * issues to client
 */

@CrossOrigin
@RestController
public class ProductsAPI {
	private static final Logger logger = LoggerFactory.getLogger(ProductsAPI.class);

	@Autowired
	ProductDetailsManager productDetailsManager;

	@Autowired
	private ProductPriceInfoRepository productPriceInfoRepository;
	
	/**
	 * This method retrieves product details 
	 * It calls external API to retrieve product name
	 * and invokes price details from NOSQL data store MongoDB
	 * @param id
	 * @return responseEntity
	 */
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getProductAndPriceDetails(@PathVariable Integer id) throws Exception, IllegalArgumentException, MongoSocketOpenException {
		ResponseEntity<?> responseEntity = null;
		ProductPriceInfo productPriceInfo = null;
		String output = null;
		logger.debug("productId>>>>" + id);
		// call api.target.com external API through ProductDetailsManager
		// to get product name
		ErrorResponseTO errorResponseTO = new ErrorResponseTO();
		ProductResponseTO productResponseTO = new ProductResponseTO();

		if (id != null) {
			output = productDetailsManager.getProductName(id);
			if (output.indexOf("error :") != -1) {
				errorResponseTO.setErrorDesc(output.substring(7, output.length()));
				responseEntity = new ResponseEntity<>(errorResponseTO, HttpStatus.OK);
				throw new NotFoundException("No product name found for product id "+id+" in external API");
			} else {
				productResponseTO.setId(id);
				productResponseTO.setName(output);
				responseEntity = new ResponseEntity<>(productResponseTO, HttpStatus.OK);
			}
		}

		// Call pricing information from a NoSQL data store, MongoDB
		productPriceInfo = productDetailsManager.getProductPriceInfo(id.toString());
		if(productPriceInfo == null){
			throw new NotFoundException("No value found in MongoDB for product id "+id.toString());
		}
		logger.debug("product Price from Mongo DB--" + productPriceInfo.getProductPrice());
		if (productPriceInfo.getProductPrice() != null && productPriceInfo.getCurrencyCode() != null) {
			CurrentPrice currentPrice = new CurrentPrice();
			currentPrice.setCurrencyCode(productPriceInfo.getCurrencyCode());
			currentPrice.setValue(productPriceInfo.getProductPrice());
			productResponseTO.setCurrentPrice(currentPrice);
		}
		return responseEntity;

	}
	
	/**
	 * This method is used to create test data in MongoDB
	 * @param productPriceMap
	 * @return response
	 */
	@RequestMapping(value = "/products/price", method = RequestMethod.POST)
	public Map<String, Object> createProductPriceInfo(@RequestBody Map<String, Object> productPriceMap) {
		ProductPriceInfo productpriceinfo = new ProductPriceInfo(productPriceMap.get("productId").toString(),
				new BigDecimal(productPriceMap.get("productPrice").toString()),
				productPriceMap.get("currencyCode").toString());

		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", "Product Price Info created successfully");
		response.put("productpriceinfo", productPriceInfoRepository.save(productpriceinfo));
		return response;
	}

	/**
	 * This method updates product price details in NOSQL data store - MongoDB
	 * @param id
	 * @return response
	 */
	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	public Map<String, Object> updateProductPriceInfo(@PathVariable Integer id,
			@RequestBody ProductResponseTO productRequestTO) {
		CurrentPrice currentPrice = null;
		currentPrice = productRequestTO.getCurrentPrice();
		BigDecimal value = currentPrice.getValue();
		String currencyCode = currentPrice.getCurrencyCode();
		ProductPriceInfo productpriceinfo = null;
		if (id != null && value != null && currencyCode != null) {
			productpriceinfo = new ProductPriceInfo(id.toString(), value, currencyCode);
		}

		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", "Product Price Info Updated successfully");
		response.put("productpriceinfo", productPriceInfoRepository.save(productpriceinfo));
		return response;
	}
	
	/**
	 * This method deletes product price details in NOSQL data store - MongoDB
	 * @param id
	 * @return response
	 */	
	  @RequestMapping(method = RequestMethod.DELETE, value="/products/{id}")
	  public Map<String, String> deleteProductPriceInfo(@PathVariable("id") String id){
		productPriceInfoRepository.delete(id);
	    Map<String, String> response = new HashMap<String, String>();
	    response.put("message", "Product Price deleted successfully");
	    
	    return response;
	  }	
	
	//Exception handling  
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponseTO> validationErrorHandler(Exception ex) throws IOException {
		ErrorResponseTO responseTO = new ErrorResponseTO();
		logger.error("RuntimeException Error");
	    responseTO.setErrorDesc("" + ex.getMessage());
		//This is to handle invalid data type in the json as a bad request.	
		if(ex instanceof NullPointerException){
			throw new BadRequestException(ex.getMessage());
		}else{
			responseTO.setErrorCode(500);
			if(ex instanceof DataAccessResourceFailureException){
				responseTO.setErrorDesc("Mongo DB is down due to MongoSocket Connection Exception");
			}
			return new ResponseEntity<ErrorResponseTO>(responseTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponseTO> validationBadRequestHandler(Exception ex) throws IOException {
		 ErrorResponseTO responseTO = new ErrorResponseTO();
		 logger.error("Bad Request");
	     responseTO.setErrorDesc("Bad Request" + ex.getMessage());
		
		return new ResponseEntity<ErrorResponseTO>(responseTO, HttpStatus.BAD_REQUEST);
	}	
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponseTO> validationErrorHandler(Exception ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ErrorResponseTO responseTO = new ErrorResponseTO();
		if (response.getStatus() == 200) {
			responseTO.setErrorDesc(ex.getMessage());
			responseTO.setErrorCode(404);

		} else {
			responseTO.setErrorDesc("Invalid Request");
			responseTO.setErrorCode(1);
		}
		return new ResponseEntity<ErrorResponseTO>(responseTO, HttpStatus.NOT_FOUND);
	}
}
