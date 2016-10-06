package com.retail.app.util;

/**
 * @author Libin 
 * Constants for the application
 */
public final class Constants {
	public static final String HOST_NAME = "api.target.com";
	public static final String REQUEST_PATH_URL = "/products/v3/";
	public static final String PARAMETERS_URL = "?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz";
	public static final int TIME_OUT = 5000;
	public static final String NO_PRODUCT_NAME_FOUND = "In external API, No product name found for product id ";
	public static final String NO_PRODUCT_PRICE_FOUND_DB = "No value found in MongoDB for product id ";
	public static final String MSG_PRICE_CREATED_SUCCESS = "Product Price Info Created successfully";
	public static final String MSG_PRICE_UPDATED_SUCCESS = "Product Price Info Updated successfully";
	public static final String MSG_PRICE_DELETED_SUCCESS = "Product Price Info Deleted successfully";
	public static final String ERR_MSG_DB_DOWN = "Mongo DB is down due to MongoSocket Connection Exception";
	public static final String BAD_REQUEST = "Bad Request";
	public static final String INVALID_REQUEST = "Invalid Request";
	public static final String INVALID_PRODUCT_ID = "Product id searched is null";
	public static final String API_NOT_REACHABLE = "api.target.com is unavailable due to java.net.UnknownHostException";

}
