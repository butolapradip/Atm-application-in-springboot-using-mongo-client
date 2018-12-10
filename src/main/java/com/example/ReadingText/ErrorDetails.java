package com.example.ReadingText;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class ErrorDetails {
	@JsonProperty("field")
	private String field = null;

	@JsonProperty("errorMessage")
	private String errorMessage = null;

	public ErrorDetails field(String field) {
		this.field = field;
		return this;
	}

	/**
	 * Get field
	 * 
	 * @return field
	 **/
	@ApiModelProperty(value = "")

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public ErrorDetails errorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}

	/**
	 * Get errorMessage
	 * 
	 * @return errorMessage
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ErrorDetails(String field, String errorMessage) {
		this.field = field;
		this.errorMessage = errorMessage;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ErrorDetails errorDetails = (ErrorDetails) o;
		return Objects.equals(this.field, errorDetails.field) && Objects.equals(this.errorMessage, errorDetails.errorMessage);
	}

	@Override
	public int hashCode() {
		return Objects.hash(field, errorMessage);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ErrorDetails {\n");

		sb.append("    field: ").append(toIndentedString(field)).append("\n");
		sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
