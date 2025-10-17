package APIs;

import Utils.FileProcessing;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.io.IOException;
import java.util.Properties;

public class listGET {

	FileProcessing processing = new FileProcessing();
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;
	
	public void listAPI() throws IOException {

		Properties prop = processing.readPropertiesFile("TestData.properties");
		String apiURL = prop.getProperty("apiURL");
		String json="";

		RestAssured.baseURI = apiURL;
		RestAssured.basePath = "/objects";

		jsonresponse = new JsonPath(RestAssured.given(). //log().all().
				contentType("application/json").
				//header("Timezone","Asia/Dhaka").
				when().get().asString());


		System.out.println("\nAPI Response for API: "+RestAssured.basePath+"\n");
		jsonresponse.prettyPrint();

	}

	public String getId(int index) {
		return jsonresponse.getString("id["+index+"]").toString();
	}

	public boolean getResponseSuccess()
	{
		return jsonresponse.getBoolean("success");
	}

	public String getErrorMessage()
	{
		return jsonresponse.getString("errorMessage").toString();
	}

}
