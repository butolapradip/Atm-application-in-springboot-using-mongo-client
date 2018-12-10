package com.example.ReadingText;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
@Component
public class AccountGenerate {
	
	@Autowired
	AtmDao dao;
	private long accountNo;
	private int pin;
	private long addNo = 0;

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public AccountGenerate(long accountNo, int pin) {
		super();
		this.accountNo = accountNo;
		this.pin = pin;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public AccountGenerate() {

	}

	public int pinGenerate() {
		pin = (int) (Math.random() * 9000) + 1000;
		return pin;
	}

	public long accountGenerate() {

		// get last value from database
		// accountNo =lastAccountNo + 1;
		addNo = dao.getCountNo();
		long accountNo = 10000000000000l + addNo;
		System.out.println(accountNo);
		this.accountNo = accountNo;
		// addNo++;
		// get last value from database

		return accountNo;
	}
	
/*	@Bean
	 public ValidatingMongoEventListener validatingMongoEventListener() {
	  return new ValidatingMongoEventListener(validator());
	 }

	 @Bean
	 public LocalValidatorFactoryBean validator() {
	  return new LocalValidatorFactoryBean();
	 }*/
}
