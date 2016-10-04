
package com.retail.app.to;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Libin
 *
 */
@Generated("org.jsonschema2pojo")
public class Errors {

    @SerializedName("message")
    @Expose
    private String message;
    
    public Errors(String message) {
        this.message = message;
    } 
    
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
