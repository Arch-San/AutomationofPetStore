package api.endpoints;
/* Swagger URI  ->  https://petstore.swagger.io/
 *                           base url                  end point
 * Create user(POST)  ->  https://petstore.swagger.io/v2/user
 * Get User(GET)  ->  https://petstore.swagger.io/v2/user/{username}
 * Update user(PUT)  ->  https://petstore.swagger.io/v2/user/{username}
 * Delete user(DELETE)  ->  https://petstore.swagger.io/v2/user/{username}
 */
public class Routes {
	
	public static String base_url="https://petstore.swagger.io/v2";
	
	//User module urls
	public static String post_url=base_url+"/user";
	public static String get_url=base_url+"/user/{username}";
	public static String put_url=base_url+"/user/{username}";
	public static String delete_url=base_url+"/user/{username}";
	
	//store module urls
	
	//pet module urls
	
}
