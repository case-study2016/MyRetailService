package com.retail.app.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.retail.app.to.Item;
import com.retail.app.to.ProductsTO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@Service
public final class WebServiceUtil {

	private static final Logger logger = Logger.getLogger(WebServiceUtil.class);

	public static void printLoggerWhenDebugEnabled(Logger logger, String log) {
		if (logger.isDebugEnabled()) {
			logger.debug(log);
		}
	}

	public List<Item> generateWebServiceCallForProductName(String url) throws Exception {
		List<Item> itemTOList = new ArrayList<Item>();
		printLoggerWhenDebugEnabled(logger, "generateWebServiceCallForProductName starts");
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

			printLoggerWhenDebugEnabled(logger, "Client response :::" + clientResponse);

			endTime = System.currentTimeMillis();

			printLoggerWhenDebugEnabled(logger, "generateWebServiceCallForProductName ends");
			return itemTOList;
		} catch (Exception e) {
			throw new Exception("Bad Request");
		}

	}

}
