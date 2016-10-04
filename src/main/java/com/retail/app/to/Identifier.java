
package com.retail.app.to;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Identifier {

    @SerializedName("id_type")
    @Expose
    private String idType;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("is_primary")
    @Expose
    private Object isPrimary;
    @SerializedName("source")
    @Expose
    private String source;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Identifier() {
    }

    /**
     * 
     * @param id
     * @param idType
     * @param isPrimary
     * @param source
     */
    public Identifier(String idType, String id, Object isPrimary, String source) {
        this.idType = idType;
        this.id = id;
        this.isPrimary = isPrimary;
        this.source = source;
    }

    /**
     * 
     * @return
     *     The idType
     */
    public String getIdType() {
        return idType;
    }

    /**
     * 
     * @param idType
     *     The id_type
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The isPrimary
     */
    public Object getIsPrimary() {
        return isPrimary;
    }

    /**
     * 
     * @param isPrimary
     *     The is_primary
     */
    public void setIsPrimary(Object isPrimary) {
        this.isPrimary = isPrimary;
    }

    /**
     * 
     * @return
     *     The source
     */
    public String getSource() {
        return source;
    }

    /**
     * 
     * @param source
     *     The source
     */
    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
