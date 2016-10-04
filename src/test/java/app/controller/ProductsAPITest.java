package app.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.retail.app.RetailApplication;
import com.retail.app.repository.ProductPriceInfoRepository;
import com.retail.app.to.CurrentPrice;
import com.retail.app.to.ProductPriceInfo;
import com.retail.app.to.ProductResponseTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RetailApplication.class)
@WebAppConfiguration
public class ProductsAPITest {

	// Required to Generate JSON content from Java objects
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	// Required to delete the data added for tests.
	// Directly invoke the APIs interacting with the DB
	@Autowired
	private ProductPriceInfoRepository productPriceInfoRepository;

	// Test RestTemplate to invoke the APIs.
	private RestTemplate restTemplate = new TestRestTemplate();

	// This is to test the creation of product price details.
	@Before
	@Test
	public void testCreateProductPriceApi() throws JsonProcessingException {
		System.out.println("testCreateProductPriceApi");
		// Building the Request body data
		Map<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("productId", 15117729);
		requestBody.put("productPrice", new BigDecimal("11.23"));
		requestBody.put("currencyCode", "USD");
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);

		// Creating http entity object with request body and headers
		HttpEntity<String> httpEntity = new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody),
				requestHeaders);

		// Invoking the API
		Map<String, Object> apiResponse = restTemplate.postForObject("http://localhost:8082/products/price", httpEntity,
				Map.class, Collections.EMPTY_MAP);

		assertNotNull(apiResponse);

		// Asserting the response of the API.
		String message = apiResponse.get("message").toString();
		assertEquals("Product Price Info created successfully", message);
		String productId = ((Map<String, Object>) apiResponse.get("productpriceinfo")).get("productId").toString();

		assertNotNull(productId);

		// Fetching the Product details directly from the DB to verify the API
		// succeeded
		ProductPriceInfo ProductPriceInfoFromDB = productPriceInfoRepository.findOne(productId);
		assertEquals("15117729", ProductPriceInfoFromDB.getProductId());
		assertEquals(new BigDecimal("11.23"), ProductPriceInfoFromDB.getProductPrice());
		assertEquals("USD", ProductPriceInfoFromDB.getCurrencyCode());

		// Delete the data added for testing
		 productPriceInfoRepository.delete(productId);

	}

	// This is to test the product API to get the whole product details.
	// This involves mocking the external API
	// This involves testcase for product price details from mongodb , NOSQL
	// datastore.
	//@After
	@Test
	public void testGetProductAndPriceDetails() {

		System.out.println("testGetProductAndPriceDetails");

         String productIdInput = "15117729";
         String productNameFromAPI = null;
		// Test external API - START
		// https://api.target.com/products/v3/13860428?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz
		RestTemplate restTemplate = new RestTemplate();
		String externalAPIUrl = "https://api.target.com/products/v3/"+ productIdInput +"?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz";
		ResponseEntity<String> response = restTemplate.getForEntity(
				externalAPIUrl,
				String.class);

		if (HttpStatus.OK == response.getStatusCode()) {

			assertEquals(HttpStatus.OK , response.getStatusCode() );

			ObjectMapper mapper = new ObjectMapper();
			JsonNode root;
			try {
				root = mapper.readTree(response.getBody());
				JsonNode product_composite_response = root.path("product_composite_response");
				JsonNode items = product_composite_response.path("items");
				productNameFromAPI = items.get(0).get("general_description").toString();
				productNameFromAPI=	productNameFromAPI.substring(1, productNameFromAPI.length()-1);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//start test case for accessing productId 15117729
			
			//Create a new Product price using the productPriceInfoRepository API
			 ProductPriceInfo productPriceInfo = new ProductPriceInfo("15117729", new BigDecimal("22.47"),
					 "USD");
			 productPriceInfoRepository.save(productPriceInfo);
			
			 String productId = productPriceInfo.getProductId();
			
			 //Now make a call to the API to get details of the product
			 ProductResponseTO apiResponse =
			 restTemplate.getForObject("http://localhost:8082/products/"+ productId,
					 ProductResponseTO.class);
			
			 //Verify that the data from the API and data saved in the DB are same
			 assertNotNull(apiResponse);
			 assertEquals(productPriceInfo.getProductId(), apiResponse.getId().toString());
             String productNameToBeTested = "Apple iPad Air 2 Wi-Fi 16GB, Gold";
			 //Need to see how to get name from external API call
			 assertEquals(productNameToBeTested, productNameFromAPI);
			 assertEquals(productPriceInfo.getProductPrice(), apiResponse.getCurrentPrice().getValue());
			 assertEquals(productPriceInfo.getCurrencyCode(), apiResponse.getCurrentPrice().getCurrencyCode());
			
			 //Delete the Test data created
			 productPriceInfoRepository.delete(productId);			
		} else {
			System.out.println("Error in accessing product name from external API");
		}

	}
	 
	 @Test
	 public void testupdateProductPriceInfo() throws JsonProcessingException{
     System.out.println("<<<<<<testupdateProductPriceInfo>>>>");
     
	 //Create a new Product price using the productPriceInfoRepository API
	 ProductPriceInfo productPriceInfo = new ProductPriceInfo("15117729", new BigDecimal("22.47"), "USD");
	 productPriceInfoRepository.save(productPriceInfo);
	
	 String productId = productPriceInfo.getProductId();

	 //Create a product update request
     ProductResponseTO productRequestTO = new ProductResponseTO();
     productRequestTO.setId(Integer.parseInt(productId));
     productRequestTO.setName("Apple iPad Air 2 Wi-Fi 16GB, Gold");
     CurrentPrice currentPrice = new CurrentPrice();
	 currentPrice.setValue(new BigDecimal(99.99).setScale(2, RoundingMode.CEILING));	 
	 currentPrice.setCurrencyCode("USD");	
	 productRequestTO.setCurrentPrice(currentPrice);
	 		
     HttpHeaders requestHeaders = new HttpHeaders();
     requestHeaders.setContentType(MediaType.APPLICATION_JSON);

	 //Creating http entity object with request body and headers
     HttpEntity<String> httpEntity = 
        new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(productRequestTO), requestHeaders);
     System.out.println("httpEntity>>>"+httpEntity);
     String url= "http://localhost:8082/products/" + productId;
     System.out.println("url>>>"+url);
    //Invoking the API
     Map<String, Object> apiResponse = (Map)restTemplate.exchange(url, 
        HttpMethod.PUT, httpEntity, Map.class, Collections.EMPTY_MAP).getBody();

	 System.out.println("apiResponse"+apiResponse);
	
	 assertNotNull(apiResponse);
	 assertTrue(!apiResponse.isEmpty());
	
	 //Asserting the response of the API.
	 String message = apiResponse.get("message").toString();
	 assertEquals("Product Price Info Updated successfully", message);
	
	 //Fetching the Product price details directly from the DB to verify the API
	 //succeeded in updating the product price details
	 ProductPriceInfo productPriceInfoFromDb = productPriceInfoRepository.findOne(productRequestTO.getId().toString());
	 assertEquals(productRequestTO.getId().toString(), productPriceInfoFromDb.getProductId());
	 assertEquals(productRequestTO.getCurrentPrice().getValue(), productPriceInfoFromDb.getProductPrice());
	 assertEquals(productRequestTO.getCurrentPrice().getCurrencyCode(), productPriceInfoFromDb.getCurrencyCode());
	
	 //Delete the data added for next testing iteration
	 //productPriceInfoRepository.delete(productRequestTO.getId().toString());
	
	 }

}
