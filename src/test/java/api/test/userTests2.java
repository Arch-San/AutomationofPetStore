package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class userTests2 {
	
	Faker faker; //global var
	User userPayload;
	
	public Logger logger; //to create logs
	
	@BeforeClass //this will execute before starting all the test methods
	public void setupData() {
		//using faker lib we will generate data here and this data will be passed to pojo class
		
		faker = new Faker(); //create faker obj
		userPayload = new User(); //whatever data we prepare, we need to pass to pojo class
		//setting data for pojo classes
		userPayload.setId(faker.idNumber().hashCode());//hashcode -generating random id numbers
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setUsername(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());	
		
		//logs
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	//creating test case
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("--------- Creating user ---------"); //log msg
		Response res=UserEndPoints2.createUser(userPayload);
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(),200);  //validation
		logger.info("--------- User is created ---------");
	}
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("---------- Reading user details ----------");
		Response res=UserEndPoints2.readUser(this.userPayload.getUsername()); //getUsername is a getter meth
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
		logger.info("---------- User details displayed ----------");
	}
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info("---------- Updating user --------------");
		//update data using payload
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setUsername(faker.internet().safeEmailAddress());
		
		Response res= UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);//parameters are existing username and updated data
		res.then().log().body();
		
		Assert.assertEquals(res.getStatusCode(),200);//test ng assertion
		
		logger.info("------------ User updated ------------");
		//checking data after updation
		Response resAfterUpdate = UserEndPoints2.readUser(this.userPayload.getUsername());
		Assert.assertEquals(resAfterUpdate.getStatusCode(), 200);
	}
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("----------- Deleting user -----------");
		Response res=UserEndPoints2.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(res.getStatusCode(),200);
		logger.info("----------- User deleted --------------");
	}
}
