package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserDDTests {
	
	public Logger logger; //For Logs
	public Logger errorLogger;
	

	@BeforeClass
	public void setupLogs()
	{
	logger=LogManager.getLogger(this.getClass());
	errorLogger = LogManager.getLogger("API_ERROR");
	
	logger.debug("Debugging......");
		}
	
	
	@Test(priority=0,dataProvider="data",dataProviderClass=DataProviders.class)
	public void testPostUser(String userId,String userName,String fName,String lName,String email,String pwd,String ph)
	{
		
		logger.info("***********Creating User******");
		User usrpld= new User();
		usrpld.setId(Integer.parseInt(userId));
		usrpld.setUsername(userName);
		usrpld.setFirstName(fName);
		usrpld.setLastName(lName);
		usrpld.setEmail(email);
		usrpld.setPassword(pwd);
		usrpld.setPhone(ph);
		
		Response response=UserEndPoints.createUser(usrpld);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	

	        // Log errors separately
	        if (response.getStatusCode() >= 400) {
	            errorLogger.error("Error Response - Status Code: " + response.getStatusCode());
	            errorLogger.error("Response Body: " + response.getBody().asString());
	        }
	        else
	        {

		        logger.info("Response Status Code: " + response.getStatusCode());
		        logger.info("Response Body: " + response.getBody().asString());
	        }
	            
		
		logger.info("******User Created***********");
		
	
	}
	
	@Test(priority=1,dataProvider="username",dataProviderClass=DataProviders.class)
	public void testDelUser(String Username){
		
		logger.info("*************Deleting Created User***********");
		
		Response response=UserEndPoints.deleteUser(Username);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	   

        // Log errors separately
        if (response.getStatusCode() >= 400) {
            errorLogger.error("Error Response - Status Code: " + response.getStatusCode());
            errorLogger.error("Response Body: " + response.getBody().asString());
        }
        else
        {
        	 logger.info("Response Status Code: " + response.getStatusCode());
             logger.info("Response Body: " + response.getBody().asString());
        }
        
		logger.info("*************Deleted Created User***********");
		

}
}
