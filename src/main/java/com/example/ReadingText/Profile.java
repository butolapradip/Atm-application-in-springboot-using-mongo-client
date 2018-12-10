package com.example.ReadingText;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

//@Component
//@EntityScan
public class Profile {
	@NotNull
	@Size(min = 2, message = "Name should have atleast 2 characters")
	private String name;
	// @NotNull
	@Size(min = 4, message = "Name should have atleast 2 characters")
	@Email
	private String email;
	@NotNull
	@Size(min = 10, message = "Name should have atleast 10 characters")
	private String mobileNo;
	// @NotNull
	@Size(min = 8, message = "Name should have atleast 2 characters")
	private String pancardNo;
	// @NotNull
	@Size(min = 10, message = "Name should have atleast 2 characters")
	private String adharcardNo;
	// @NotNull
	@DateTimeFormat
	private String dob;
	private long accountNo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "Profile [name:" + name + ", email:" + email + ", mobileNo:" + mobileNo + ", pancardNo:" + pancardNo
				+ ", adharcardNo:" + adharcardNo + ", dob:" + dob + ", accountNo:" + accountNo + "]";
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPancardNo() {
		return pancardNo;
	}

	public void setPancardNo(String pancardNo) {
		this.pancardNo = pancardNo;
	}

	public String getAdharcardNo() {
		return adharcardNo;
	}

	public void setAdharcardNo(String adharcardNo) {
		this.adharcardNo = adharcardNo;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

}
