
package com.retail.app.to;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class BusinessProcessStatu {

    @SerializedName("process_status")
    @Expose
    private ProcessStatus processStatus;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BusinessProcessStatu() {
    }

    /**
     * 
     * @param processStatus
     */
    public BusinessProcessStatu(ProcessStatus processStatus) {
        this.processStatus = processStatus;
    }

    /**
     * 
     * @return
     *     The processStatus
     */
    public ProcessStatus getProcessStatus() {
        return processStatus;
    }

    /**
     * 
     * @param processStatus
     *     The process_status
     */
    public void setProcessStatus(ProcessStatus processStatus) {
        this.processStatus = processStatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
