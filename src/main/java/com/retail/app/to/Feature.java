
package com.retail.app.to;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Feature {

    @SerializedName("feature")
    @Expose
    private String feature;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Feature() {
    }

    /**
     * 
     * @param feature
     */
    public Feature(String feature) {
        this.feature = feature;
    }

    /**
     * 
     * @return
     *     The feature
     */
    public String getFeature() {
        return feature;
    }

    /**
     * 
     * @param feature
     *     The feature
     */
    public void setFeature(String feature) {
        this.feature = feature;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
