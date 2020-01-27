package tracfonerestapi;

import static io.restassured.RestAssured.get;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GethrefTest {
	
	@Test
	public void testOUt(){

		HashMap<String,String> headerMap=new HashMap<String,String>();
		headerMap.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
		headerMap.put("cookie","JSESSIONID=node019z58cyn3yi4z68wr8vi2fxx531045.node0");
		
		Response response = RestAssured.given().headers(headerMap)
		.get("https://signin.simplemobilecloud.com/api");
		
		JsonPath jsonPathEvaluator = new JsonPath(response.body().asString());
		
		
		//to extract all the list containing href and other value
	 List<HashMap<String,String>> objectArray = jsonPathEvaluator.getList("_links.service");
	 objectArray.addAll(jsonPathEvaluator.getList("_links.new"));
	 objectArray.addAll(jsonPathEvaluator.getList("_links.no-follow"));
	 objectArray.addAll(jsonPathEvaluator.getList("_links.self"));
	 objectArray.addAll(jsonPathEvaluator.getList("_links.next"));
	 objectArray.addAll(jsonPathEvaluator.getList("_links.glossary"));
	 List<String> allhrefList=new ArrayList<String>();
		
	 
	 //code to fetch only href
	 	for(HashMap<String,String> e: objectArray){
			allhrefList.add(e.get("href"));
		}
	 	
	 	
	 	System.out.println(allhrefList.size()+"\n"+allhrefList);
	 	
	 	
	 	for(String string:allhrefList){
	 		Assert.assertTrue(string.startsWith("https://signin.simplemobilecloud.com"));
	 	}
		
	}
}