package com.example.ReadingText;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
//import com.forset.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

public class ErrorDetailsList {
	@JsonProperty("errors")
	@Valid
	private List<ErrorDetails> errors = new ArrayList<>();

	public ErrorDetailsList errors(List<ErrorDetails> errors) {
		this.errors = errors;
		return this;
	}

	public ErrorDetailsList addErrorsItem(ErrorDetails errorsItem) {
		this.errors.add(errorsItem);
		return this;
	}

	public List<ErrorDetails> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorDetails> errors) {
		this.errors = errors;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ErrorDetailsList errorResponseList = (ErrorDetailsList) o;
		return Objects.equals(this.errors, errorResponseList.errors);
	}

	@Override
	public int hashCode() {
		return Objects.hash(errors);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ErrorResponseList {\n");

		//sb.append("    errors: ").append(StringUtils.toIndentedString(errors)).append("\n");
		sb.append("}");
		return sb.toString();
	}

}