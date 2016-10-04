package com.retail.app.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Tester {
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = 
		  "https://api.target.com/products/v3/15117729?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz";
		ResponseEntity<String> response = 
		  restTemplate.getForEntity(fooResourceUrl, String.class);
		assertEquals(HttpStatus.OK , response.getStatusCode() );

		ObjectMapper mapper = new ObjectMapper();
		JsonNode root;
		try {
			root = mapper.readTree(response.getBody());
			JsonNode product_composite_response = root.path("product_composite_response");
			JsonNode items = product_composite_response.path("items");
			
			 System.out.println(items.get(0).get("general_description"));
//			assertEquals("bar", product_composite_response.get);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
