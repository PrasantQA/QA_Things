package api.endpoints;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.response.Response;


public class UserEndPoints {
	
	public static Response createUser(User Payload)
	{
	Response response=	given()
		.contentType("application/json")
		.accept("application/json")
		.body(Payload)
		.when()
		.post(Routes.post_url);
		
		return response;
		
	}
	
	public static Response getUser(String userName)
	{
		Response response=	given()
				.accept("application/json")
				.pathParam("username", userName)
				.when()
				.get(Routes.get_url);
				
				return response;
		
	}
	public static Response updateUser(String username,User Payload)
	{
	Response response=	given()
		.contentType("application/json")
		.accept("application/json")
		.pathParam("username", username)
		.body(Payload)
		.when()
		.put(Routes.update_url);
		
		return response;
		
	}
	
	public static Response deleteUser(String userName)
	{
		Response response=	given()
				.accept("application/json")
				.pathParam("username", userName)
				.when()
				.delete(Routes.delete_url);
				
				return response;
		
	}

}
