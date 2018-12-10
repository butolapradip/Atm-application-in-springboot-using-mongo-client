package com.example.ReadingText;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.core.query.Criteria;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
//import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@EnableAutoConfiguration(exclude = { org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class })
@Configuration
public class AtmDao {
	@Autowired
	AccountGenerate generate;
	private int balance = 0;
	private int userBalance;
	private long DbAccountNo = 0;
	private int DbPin = 0;
	private String BalanceDetails;
	String totals;

	// for new user register
	public void setProfile(Profile profileData, long accountNo) {
		try {

			MongoClient mongo = new MongoClient("localhost", 27017);
			MongoDatabase database = mongo.getDatabase("ATM");
			MongoCollection collection = database.getCollection("profile");
			long count = collection.count();
			generate.setAccountNo(count);
			System.out.println(count);
			String account = Long.toString(accountNo);
			Gson gson = new Gson();
			String json = gson.toJson(profileData);
			Document doc = Document.parse(json);
			doc.put("accountNo", account);
			collection.insertOne(doc);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// to get number of account in database
	public long getCountNo() {

		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase database = mongo.getDatabase("ATM");
		MongoCollection collection = database.getCollection("profile");
		long count = collection.count();
		count = count + 1;
		return count;

	}

	// to store a account and pin in account collection
	void accountInsert(long accountNo, int pin) {
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase database = mongo.getDatabase("ATM");
		MongoCollection collection = database.getCollection("account");
		Document obj1 = new Document();
		obj1.put("accountNo", accountNo);
		obj1.put("pin", pin);
		obj1.put("balance", balance);
		collection.insertOne(obj1);

	}

	// for check balance
	public String checkBalance(long accountNo, int pin) {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB database = mongo.getDB("ATM");
		DBCollection collection = database.getCollection("account");
		BasicDBObject doc = new BasicDBObject("accountNo", accountNo);
		DBCursor cursor = collection.find(doc);
		if (cursor.count() == 0) {
			return "wrong enter account no";

		}

		while (cursor.hasNext()) {
			BasicDBObject dbo = (BasicDBObject) cursor.next();
			Integer Userpin = (Integer) dbo.get("pin");
			Long UseraccountNo = (Long) dbo.get("accountNo");
			DbAccountNo = UseraccountNo.longValue();
			DbPin = Userpin.intValue();
			Integer bal = (Integer) dbo.get("balance");
			userBalance = bal.intValue();
			if (pin != DbPin) {
				return "wrong password";
			}
		}
		return "" + userBalance;
	}

	// deposit amount
	public String deposit(long accountNo, int pin, int ammount) {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB database = mongo.getDB("ATM");
		DBCollection collection = database.getCollection("account");
		BasicDBObject doc = new BasicDBObject("accountNo", accountNo);
		DBCursor cursor = collection.find(doc);
		if (cursor.count() == 0) {
			return "wrong enter account no";

		}

		while (cursor.hasNext()) {
			BasicDBObject dbo = (BasicDBObject) cursor.next();
			Integer Userpin = (Integer) dbo.get("pin");
			Long UseraccountNo = (Long) dbo.get("accountNo");
			DbAccountNo = UseraccountNo.longValue();
			DbPin = Userpin.intValue();
			Integer bal = (Integer) dbo.get("balance");
			int lastBalance = bal.intValue();
			if (pin != DbPin) {
				return "wrong password";
			} else {

				int updateBalance = lastBalance + ammount;
				BasicDBObject newDocument = new BasicDBObject();
				newDocument.append("$set", new BasicDBObject().append("balance", updateBalance));

				collection.update(new BasicDBObject().append("accountNo", accountNo), newDocument);
				totals = String.valueOf(updateBalance);

			}
		}
		return totals;
	}

	// with draw amount
	public String withDraw(long accountNo, int pin, int ammount) {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB database = mongo.getDB("ATM");
		DBCollection collection = database.getCollection("account");
		BasicDBObject doc = new BasicDBObject("accountNo", accountNo);
		DBCursor cursor = collection.find(doc);
		if (cursor.count() == 0) {
			return "wrong enter account no";
		}

		while (cursor.hasNext()) {
			BasicDBObject dbo = (BasicDBObject) cursor.next();
			Integer Userpin = (Integer) dbo.get("pin");
			Long UseraccountNo = (Long) dbo.get("accountNo");
			DbAccountNo = UseraccountNo.longValue();
			DbPin = Userpin.intValue();
			Integer bal = (Integer) dbo.get("balance");
			int lastBalance = bal.intValue();
			if (pin != DbPin) {
				return "wrong password";
			} else if (lastBalance >= ammount) {
				int updateBalance = lastBalance - ammount;
				BasicDBObject newDocument = new BasicDBObject();
				newDocument.append("$set", new BasicDBObject().append("balance", updateBalance));
				collection.update(new BasicDBObject().append("accountNo", accountNo), newDocument);
				totals = String.valueOf(updateBalance);

			} else
				return "the amount is more then total balance ";
		}
		return totals;
	}

	public String profileUpdate(String name, String email, String mobileNo, String pancardNo, String adharcardNo,
			String dob, long accountNo, int pin) {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB database = mongo.getDB("ATM");
		DBCollection collection = database.getCollection("account");
		BasicDBObject doc = new BasicDBObject("accountNo", accountNo);
		DBCursor cursor = collection.find(doc);
		if (cursor.count() == 0) {
			return "wrong enter account no";
		}

		while (cursor.hasNext()) {
			BasicDBObject dbo = (BasicDBObject) cursor.next();
			Integer Userpin = (Integer) dbo.get("pin");
			Long UseraccountNo = (Long) dbo.get("accountNo");
			DbAccountNo = UseraccountNo.longValue();
			DbPin = Userpin.intValue();
			Integer bal = (Integer) dbo.get("balance");
			int lastBalance = bal.intValue();
			if (pin != DbPin) {
				return "wrong password";
			} else {
				BasicDBObject searchQuery = new BasicDBObject("accountNo", accountNo);
				BasicDBObject newDocument = new BasicDBObject()
				.append("name", name)
				.append("email", email)
				.append("mobileNo", mobileNo)
				.append("pancardNo", pancardNo)
				.append("adharcardNo", adharcardNo)
				.append("dob", dob)
				.append("accountNo", accountNo);
				System.out.println("in update");
				BasicDBObject setQuery = new BasicDBObject();
				setQuery.append("$set", newDocument);
				collection.update(searchQuery, setQuery);

			}
		}
		return "update compeleted";
	}
}
