package api.endpoints;

/*
 Swagger URI :
 Create User : https://petstore.swagger.io/#/user/createUser
 Get USer : https://petstore.swagger.io/#/user/getUserByName
 Update User : https://petstore.swagger.io/#/user/updateUser
 Delete User : https://petstore.swagger.io/#/user/deleteUser

 */

public class Routes {
	
	public static String base_url=" https://petstore.swagger.io/v2";
	
	//User Module
	
	public static String post_url=base_url+"/user";
	public static String get_url=base_url+"/user/{username}";
	public static String update_url=base_url+"/user/{username}";
	public static String delete_url=base_url+"/user/{username}";
	

}
