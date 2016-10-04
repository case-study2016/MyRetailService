package com.retail.app.to;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Used by exception handler
 **/
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2016-03-01T11:23:21.369-05:00")
public class ErrorResponseTO {

	private Integer errorCode = null;
	private String errorDesc = null;

	/**
	 * ErrorCode
	 **/
	@JsonProperty("ErrorCode")
	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * ErrorDesc
	 **/
	@JsonProperty("ErrorDesc")
	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ErrorResponseTO {\n");

		sb.append("  errorCode: ").append(errorCode).append("\n");
		sb.append("  errorDesc: ").append(errorDesc).append("\n");
		sb.append("}\n");
		return sb.toString();
	}
}
