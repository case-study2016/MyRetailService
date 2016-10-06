package com.retail.app.util;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.retail.app.to.Item;
import com.retail.app.to.ProductsTO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * @author Libin
 *
 */
@Service
public final class WebServiceUtil {

	private static final Logger logger = Logger.getLogger(WebServiceUtil.class);

	@HystrixCommand(fallbackMethod ="generateWebServiceCallForProductNameFallBack")
	public List<Item> generateWebServiceCallForProductName(String url) throws Exception {
		List<Item> itemTOList = new ArrayList<Item>();
		logger.debug("generateWebServiceCallForProductName starts");
		ClientResponse clientResponse = null;
		Gson gson = new Gson();
		long startTime = System.currentTimeMillis();
		long endTime;
		try {

			ClientConfig config = new DefaultClientConfig();
			config.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, Constants.TIME_OUT);
			Client client = Client.create(config);
			WebResource webResource = client.resource(url);

			clientResponse = webResource.type("application/json").get(ClientResponse.class);

			String serviceResponse = clientResponse.getEntity(String.class);

			if (serviceResponse != null) {
				ProductsTO productsTO = gson.fromJson(serviceResponse, ProductsTO.class);
				logger.debug("Name Of productsTO: " + productsTO.getProductCompositeResponse().getItems().size());
				itemTOList = productsTO.getProductCompositeResponse().getItems();
			}

			logger.debug("Client response :::" + clientResponse);

			endTime = System.currentTimeMillis();

			logger.debug("generateWebServiceCallForProductName ends");
			return itemTOList;
		} catch (Exception e) {
			if(e instanceof ClientHandlerException){
				throw new ClientHandlerException(Constants.API_NOT_REACHABLE);
			}else{
				throw new Exception(Constants.BAD_REQUEST);
			}
		}

	}
	
	public ClientResponse triggerMasterDataServiceFallBack(String serviceURL, String hostURI,
			MultivaluedMap<String, String> queryParams)
	{
		commonfallBack(serviceURL);
		return null;
	}
	
	public void commonfallBack(String serviceURL) 
	{
		logger.error("This webservice url is not reachable :: "+serviceURL);
	}

}
