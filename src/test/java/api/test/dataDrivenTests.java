package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class dataDrivenTests {
	
	@Test(priority=1,dataProvider="Data", dataProviderClass=DataProviders.class) //name of getAllData method is Data and its in dataProviders class
	public void testPostUser(String userID, String uname, String fname, String lname,String email, String pass, String phone ) 
	{ 
		//parameters here should be in the same order as in the excel file and by using this we need to create payload
		//instead of faker, we will get data from data providers.
		//for post req we have to refer first meth getAllData from DataProviders class
		
		//creating payload
		User userPayload = new User(); //user is our pojo class object
		userPayload.setId(Integer.parseInt(userID)); //converting userid from string to int
		userPayload.setUsername(uname);
		userPayload.setFirstname(fname);
		userPayload.setLastname(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(pass);
		userPayload.setPhone(phone);
		
		Response res=UserEndPoints.createUser(userPayload);
		Assert.assertEquals(res.getStatusCode(),200);
		
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String uname)
	{
		Response res=UserEndPoints.deleteUser(uname);
		Assert.assertEquals(res.getStatusCode(),200);
	}

}
