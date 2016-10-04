
package com.retail.app.to;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class ProcessStatus {

    @SerializedName("is_ready")
    @Expose
    private Boolean isReady;
    @SerializedName("operation_description")
    @Expose
    private String operationDescription;
    @SerializedName("operation_code")
    @Expose
    private String operationCode;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ProcessStatus() {
    }

    /**
     * 
     * @param operationDescription
     * @param isReady
     * @param operationCode
     */
    public ProcessStatus(Boolean isReady, String operationDescription, String operationCode) {
        this.isReady = isReady;
        this.operationDescription = operationDescription;
        this.operationCode = operationCode;
    }

    /**
     * 
     * @return
     *     The isReady
     */
    public Boolean getIsReady() {
        return isReady;
    }

    /**
     * 
     * @param isReady
     *     The is_ready
     */
    public void setIsReady(Boolean isReady) {
        this.isReady = isReady;
    }

    /**
     * 
     * @return
     *     The operationDescription
     */
    public String getOperationDescription() {
        return operationDescription;
    }

    /**
     * 
     * @param operationDescription
     *     The operation_description
     */
    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }

    /**
     * 
     * @return
     *     The operationCode
     */
    public String getOperationCode() {
        return operationCode;
    }

    /**
     * 
     * @param operationCode
     *     The operation_code
     */
    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
