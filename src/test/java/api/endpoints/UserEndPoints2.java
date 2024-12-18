package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.response.Response;
import io.restassured.http.ContentType;


//these are not test methods. These methods will be classed by the test methods
//created to perform crud requests to user API
public class UserEndPoints2 {
	
	//meth to get the url from properties file
	static ResourceBundle getURL(){
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //load properties file; name of the properties file
		return routes;
		//reads the properties file and returns the resource bundle obj
	}
	
	public static Response createUser(User payload){ //payload is request body and will be called from the test case
		
		String post_url=getURL().getString("post_url");//get post_url key from the properties file
		Response res= given()
			.contentType(ContentType.JSON) //this is mentioned in swagger io curl
			.accept(ContentType.JSON)
			.body(payload)
			
		.when()
			.post(post_url);
		
		return res;
	}
	
	public static Response readUser(String userName){ //we dont need payload coz we are not passing anything inside req body. username we'll get from test cases data
		
		String get_url=getURL().getString("get_url");
		Response res = given()
				.pathParam("username", userName)
				
				.when()
					.get(get_url);
				
				return res;
	}
	
	public static Response updateUser(String userName, User payload) {	
		
		String put_url=getURL().getString("update_url");
		Response res = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
			
			.when()
				.put(put_url);
		
			return res;	
	}
	
	public static Response deleteUser(String userName) {
		
		String delete_url=getURL().getString("delete_url");
		Response res=given()
				.pathParam("username", userName)
		
			.when()
				.delete(delete_url);
		
		return res;
	}
}
