package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.response.Response;
import io.restassured.http.ContentType;


//these are not test methods. These methods will be classed by the test methods
//created to perform crud requests to user API
public class UserEndPoints {
	
	public static Response createUser(User payload){ //payload is request body and will be called from the test case
		
		Response res= given()
			.contentType(ContentType.JSON) //this is mentioned in swagger io curl
			.accept(ContentType.JSON)
			.body(payload)
			
		.when()
			.post(Routes.post_url);
		
		return res;
	}
	
	public static Response readUser(String userName){ //we dont need payload coz we are not passing anything inside req body. username we'll get from test cases data
		
		Response res = given()
				.pathParam("username", userName)
				
				.when()
					.get(Routes.get_url);
				
				return res;
	}
	
	public static Response updateUser(String userName, User payload) {	
		Response res = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
			
			.when()
				.put(Routes.put_url);
		
			return res;	
	}
	
	public static Response deleteUser(String userName) {
		Response res=given()
				.pathParam("username", userName)
		
			.when()
				.delete(Routes.delete_url);
		
		return res;
	}
}
