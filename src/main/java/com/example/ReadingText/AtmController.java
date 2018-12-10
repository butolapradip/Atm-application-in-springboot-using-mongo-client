package com.example.ReadingText;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController

public class AtmController {
	@Autowired
	AccountGenerate generate;
	@Autowired
	AtmDao dao;

	@PostMapping(value = "/register", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<AccountDetails> createAccount(@Valid @RequestBody Profile profile) {

		long counts = dao.getCountNo();
		long accountNo = generate.accountGenerate();
		int pinNo = generate.pinGenerate();
		dao.setProfile(profile, accountNo);
		dao.accountInsert(accountNo, pinNo);
		return new ResponseEntity<AccountDetails>(new AccountDetails(accountNo, pinNo), HttpStatus.OK);
	}

	@PostMapping("/deposit-amount")
	public ResponseEntity<AccountResponse> amountDeposit(@RequestBody Account deposit) {
		String balance = dao.deposit(deposit.getAccountNo(), deposit.getPin(), deposit.getBalance());
		return new ResponseEntity<AccountResponse>(new AccountResponse(balance), HttpStatus.OK);

	}

	@PostMapping("/withdraw-amount")
	public ResponseEntity<AccountResponse> amountDraw( @RequestBody Account withDraw) {
		String balance = dao.withDraw(withDraw.getAccountNo(), withDraw.getPin(), withDraw.getBalance());
		return new ResponseEntity<AccountResponse>(new AccountResponse(balance), HttpStatus.OK);

	}

	@PutMapping("/update-profile")
	public @ResponseBody ResponseEntity<String> updateProfile(@RequestBody Profile profile,@RequestHeader int pin) {
		String Update =dao.profileUpdate(profile.getName(), profile.getEmail(), profile.getMobileNo(),profile.getPancardNo(), profile.getAdharcardNo(),profile.getDob(),profile.getAccountNo(),pin);
		return new ResponseEntity<>(profile.toString(), HttpStatus.OK);
	}

	@GetMapping(value = "/check-balance/account-no/{accountNo}")

	public ResponseEntity<AccountResponse> getAccount(@Valid @RequestHeader int pin,
			@PathVariable("accountNo") long accountNo) {
		String balance = dao.checkBalance(accountNo, pin);
		return new ResponseEntity<>(new AccountResponse(balance), HttpStatus.OK);
	}

}
