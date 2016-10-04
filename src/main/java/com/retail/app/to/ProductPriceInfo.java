package com.retail.app.to;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

public class ProductPriceInfo {


  @Id
  private String productId;
  private BigDecimal productPrice;
  private String currencyCode;
  
  public ProductPriceInfo(){}
  
  public ProductPriceInfo(String productId, BigDecimal productPrice, String currencyCode){
	this.productId = productId;  
    this.productPrice = productPrice;
    this.currencyCode = currencyCode;
  }
  
public String getProductId() {
	return productId;
}

public void setProductId(String productId) {
	this.productId = productId;
}

public BigDecimal getProductPrice() {
	return productPrice;
}

public void setProductPrice(BigDecimal productPrice) {
	this.productPrice = productPrice;
}

public String getCurrencyCode() {
	return currencyCode;
}

public void setCurrencyCode(String currencyCode) {
	this.currencyCode = currencyCode;
}
  
}
