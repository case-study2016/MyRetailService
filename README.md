# README for MyRetailApp which exposes Products API for the below scenarios.

1. GET API call to retrieve product details including product name and price details.
2. The GET call to retrieve product details invokes external API for Product name and
   and accesses the product price details from MongoDB(NoSQL data store). 
3. There is PUT API call to update the price details as well.
4. For sample data insertion deletion for price details in MongoDB, this
   app also exposes POST and DELETE APIs.   
5. All the API specifications are available through Swagger UI.
   Swagger UI can be accessed via http://localhost:8082/swagger-ui.html
6. Test case driven development is followed and all test cases are available in  
   src/test/java source folder.   
   

# Please follow the instructions below to run this application.

1. Install Java 8. 
1. Install Spring Tool Suite (STS) 3.8.1 from http://spring.io/tools/sts
   STS has an embedded tomcat server and the project can be run in this server.
2. Install MongoDB by using link https://www.mongodb.com/download-center#community
3. https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/
4. Use the below commands to start mongodb
   a. Open windows command line as administrator
   b. The following command will start mongodb connection locally
   c. C:\mongodb\bin>mongod.exe --dbpath="c:\mongodb\data\db"
   d. Open windows command line as administrator
   e.The following will start mongodb terminal to execute monogodb commands
   f. C:\mongodb\bin> mongo.exe
      For Eg: We can run the db commands as follows.
	  > use ProductPrice
	  > db.productPriceInfo.find().pretty();
   g. In this app we have the MongoDB details in the application.properties file.
      So the app connects to the db accordingly.	  

5. This project is stored in GitHub repository
6. The project can be downloaded by https://github.com/case-study2016/MyRetailService.git
7. Import project into SpringToolSuite using Git Clone option
   (File>Import>Git>Projects From Git)
8. This is a Maven project. So please make sure that you update
   the maven dependencies by Maven>Update Project option in STS.
   Also do a Project>clean option.
9. The project should be under local server under Boot dashboard now.
10. Launch the App by startng the server. 
11. Cross origin requests capability is given to each endpoint. So there will not be any cross domain issues
    for the clients.
12. Has added Hystrix fallback method for latency and fault tolerance.
13. All the API end points can be tested using Swagger UI link, http://localhost:8082/swagger-ui.html.
14. In addition to that all API endpoints informations are given below.

1. Get Request to retrieve product details including product name and price.

   Request URL
   
   http://localhost:8080/MyRetailApp/products/15117729
   
   Response
   {
	  "id": 15117729,
	  "name": "Apple iPad Air 2 Wi-Fi 16GB, Gold",
	  "currentPrice": {
	    "value": 55.45,
	    "currencyCode": "USD"
	  }
   }

2. PUT Request to update product price.

   Request URL 
   
   http://localhost:8080/MyRetailApp/products/15117729
   
   Request body
	{
	  "currentPrice": {
	    "currencyCode": "USD",
	    "value": 75.45
	  },
	  "id": 15117729,
	  "name": "Apple iPad Air 2 Wi-Fi 16GB, Gold"
	}    
	     
	
	
   Response
	{
	  "message": "Product Price Info Updated successfully",
	  "productpriceinfo": {
	    "productId": "15117729",
	    "productPrice": 75.45,
	    "currencyCode": "USD"
	  }
	}
3. To create a price record for a product in DB, please use the POST method

   Request URL
   http://localhost:8082/products/price
   
   Request body
	{
	  "productId" : 15117729,
	  "productPrice" : 41.45,
	  "currencyCode" : "USD"
	}
	
   Response
	{
	  "message": "Product Price Info Created successfully",
	  "productpriceinfo": {
	    "productId": "15117729",
	    "productPrice": 41.45,
	    "currencyCode": "USD"
	  }
	}
	
4. To delete a price record for a product in DB, please use the DELETE method
	
   Request URL	
   http://localhost:8080/MyRetailApp/products/15117729
   
   Response
   {
  	"message": "Product Price Info Deleted successfully"
   }
   
 15. You may use different dbs for real application and test case execution by changing the 
     db properties in application.properties file as shown below.
 
  <Use this for Real Application>
  
   spring.data.mongodb.database=ProductPrice
   
  <Use this for Test Case>
  
  spring.data.mongodb.database=ProductPriceTest  //comment this while running real application
	  
 
 
 
   