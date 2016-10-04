
package com.retail.app.to;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class ProductCompositeResponse {

    @SerializedName("request_attributes")
    @Expose
    private List<RequestAttribute> requestAttributes = new ArrayList<RequestAttribute>();
    @SerializedName("items")
    @Expose
    private List<Item> items = new ArrayList<Item>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public ProductCompositeResponse() {
    }

    /**
     * 
     * @param items
     * @param requestAttributes
     */
    public ProductCompositeResponse(List<RequestAttribute> requestAttributes, List<Item> items) {
        this.requestAttributes = requestAttributes;
        this.items = items;
    }

    /**
     * 
     * @return
     *     The requestAttributes
     */
    public List<RequestAttribute> getRequestAttributes() {
        return requestAttributes;
    }

    /**
     * 
     * @param requestAttributes
     *     The request_attributes
     */
    public void setRequestAttributes(List<RequestAttribute> requestAttributes) {
        this.requestAttributes = requestAttributes;
    }

    /**
     * 
     * @return
     *     The items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * 
     * @param items
     *     The items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
