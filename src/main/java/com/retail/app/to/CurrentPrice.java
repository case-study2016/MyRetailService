
package com.retail.app.to;

import java.math.BigDecimal;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class CurrentPrice {

    @SerializedName("value")
    @Expose
    private BigDecimal value;
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;

    /**
     * 
     * @return
     *     The value
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * 
     * @return
     *     The currencyCode
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * 
     * @param currencyCode
     *     The currency_code
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
