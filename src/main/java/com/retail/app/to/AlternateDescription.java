
package com.retail.app.to;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class AlternateDescription {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("type_description")
    @Expose
    private String typeDescription;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AlternateDescription() {
    }

    /**
     * 
     * @param typeDescription
     * @param value
     * @param type
     */
    public AlternateDescription(String type, String value, String typeDescription) {
        this.type = type;
        this.value = value;
        this.typeDescription = typeDescription;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The value
     */
    public String getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 
     * @return
     *     The typeDescription
     */
    public String getTypeDescription() {
        return typeDescription;
    }

    /**
     * 
     * @param typeDescription
     *     The type_description
     */
    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
