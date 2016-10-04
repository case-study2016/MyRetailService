
package com.retail.app.to;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class ProductsTO {

    @SerializedName("product_composite_response")
    @Expose
    private ProductCompositeResponse productCompositeResponse;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ProductsTO() {
    }

    /**
     * 
     * @param productCompositeResponse
     */
    public ProductsTO(ProductCompositeResponse productCompositeResponse) {
        this.productCompositeResponse = productCompositeResponse;
    }

    /**
     * 
     * @return
     *     The productCompositeResponse
     */
    public ProductCompositeResponse getProductCompositeResponse() {
        return productCompositeResponse;
    }

    /**
     * 
     * @param productCompositeResponse
     *     The product_composite_response
     */
    public void setProductCompositeResponse(ProductCompositeResponse productCompositeResponse) {
        this.productCompositeResponse = productCompositeResponse;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
