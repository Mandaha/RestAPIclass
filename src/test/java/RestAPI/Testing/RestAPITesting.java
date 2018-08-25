package RestAPI.Testing;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.* ;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.internal.thread.ThreadExecutionException;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RestAPITesting {

	@BeforeClass
	public void setUp() {
		
		RestAssured.baseURI = "https://www.batch8-api-site.dev.cc/wp-json" ;
		basePath = "/wp/v2" ; 
		
	}
	
	//@Test
	public void simpleGetRequest() {
		
		given()
			.relaxedHTTPSValidation()
		.when()
			.log().all()
			//.queryParam("per_page",2)
			.get("/posts")
		.then()
			.log().all() 
			.assertThat()
			.statusCode(200)
			.and()
			.body("id", hasItem(24) )
//			.body("title.rendered", is("Vadym title") )
//			.body("sticky", is(false))
			
			;
		
		// TASK . CHECK YOUR RESPONSE ID AND TITLE IS AS EXPECTED IN YOUR APP
					
		
	}
	
	
	@Test
	public void printBody() {
		
		given()
			.relaxedHTTPSValidation()
		.when()
			.log().all()
		//.queryParam("per_page",2)
		.get("/posts")
			.body().prettyPrint() ; 
		
	}
	
	//@Test
	public void simpleGetRequestForSingleItem() {
		
		given()
			.relaxedHTTPSValidation()
		.when()
			.log().all()
			//.queryParam("per_page",2)  
			.pathParam("value", 24)	
			.get("/posts/{value}") 
			//.get("/posts/{whatever}" , 24)
			//.get("/posts/24) // this is what we want to get eventaully
			
		.then()
			.log().all() 
			.assertThat()
			.statusCode(200)
			.and()
			.body("id",  equalTo(24))
			.body("title.rendered", is("Vadym title") )
			.body("sticky", is(false))
			
			;
		
		// TASK . CHECK YOUR RESPONSE ID AND TITLE IS AS EXPECTED IN YOUR APP
					
		
	}
	
	
	//@Test
	public void simplePostTest() {
		
		given()
			.relaxedHTTPSValidation()
			.auth().preemptive().basic("admin","admin")
			.contentType(ContentType.JSON).
			
		when()
			.body("{\n" + 
					"	\"title\":\"YY -- API DAY 3 POST time for post request\" ,\n" + 
					"	\"content\": \"YY ASWESOME CONTENT\",\n" + 
					"	\"status\" : \"publish\"\n" + 
					"}")
			.log().all()
			.post("/posts").
		then()
			.log().all()
			.statusCode(201) ; 
			
			; 
		
		
		
	}
	
	
	
	
	//@Test
	public void simplepPutTest() {
		
		given()
			.relaxedHTTPSValidation()
			.auth().preemptive().basic("admin","admin")
			.contentType(ContentType.JSON).
			
		when()
			.body("{\n" + 
					"	\"title\":\"YY -- API DAY 3 POST time for PUT request\" " + 
					"}")
			.log().all()
			.pathParam("newID",29)
			.put("/posts/{newID}").
		then()
			.log().all()
			.statusCode(200)
			.body("title.raw", is("YY -- API DAY 3 POST time for PUT request") ) 
			
			; 
		
		
		
	}
	
	@Test
	public void simpleDeleteTest() {
		
		
		given()
			.relaxedHTTPSValidation()
			.auth().preemptive().basic("admin", "admin").
		when() 
			.pathParam("deleteID", 43)
			.queryParam("force", true)
			.delete("/posts/{deleteID}").
			//.delete("/posts/29")
		then()
			.statusCode(200)
			.body("deleted",is(true) ) 
			;
			
			
		
		
		
	}
	
	
	
	
	
	
	
}
