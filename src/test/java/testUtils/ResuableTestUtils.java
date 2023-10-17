package testUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ResuableTestUtils {


	static RequestSpecification req;
	static ResponseSpecification res;
	PrintStream log;
	
	//Function to get values from global properties file
		public static String getGlobalPropertiesValue(String key) throws IOException {
			Properties prop=new Properties();
			FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\config.properties");
			prop.load(fis);
			return prop.getProperty(key);
			
		}

		//Create Request Specification
		public RequestSpecification requestSpecification() throws IOException{
		if(req==null){
			log=new PrintStream(new FileOutputStream("logs.txt"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalPropertiesValue("baseURL")).addPathParam("projectId", getGlobalPropertiesValue("project_Id"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
			return req;
		}
			return req;		
		}
		
		//Create Response Specification
		public ResponseSpecification responseSpecification()
		{
			res = new ResponseSpecBuilder().expectHeader("server", "cloudflare").expectContentType(ContentType.JSON).build();
			return res;
		}
		
		
		//Convert raw response to JSON
		public static JsonPath rawToJson(String response) {
			JsonPath js = new JsonPath(response);
			return js;
		}
		
		


		

}
