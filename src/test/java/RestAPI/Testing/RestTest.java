package RestAPI.Testing;

import static org.testng.Assert.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.* ;
//import static org.hamcrest.CoreMatchers.*;



public class RestTest {

	
	@BeforeClass
	public void setUp() {
		
		RestAssured.baseURI = "https://www.batch8-api-site.dev.cc/wp-json" ;
		basePath = "/wp/v2" ; 
		
		
		}
	
	//Given rest end point http://73.166.37.2:1000/ords/hr/employees/100
	//When i send a HTTP Get request to the server 
	//Then i should get 200 OK result as status code 
	@Test
	public void firstTest(){
		
		when()
			.get("http://73.166.37.2:1000/ords/hr/employees/100")
		.then()
			.statusCode(200) ; 
		
	}
	
	/* Given rest end point https://www.batch8-api-site.dev.cc/wp-json/wp/v2/posts/13
	 * When i send a HTTP Get request to the server 
		Then i should get 200 OK result as status code 
	 * */
	@Test
	public void Test(){
		
		given()
			.relaxedHTTPSValidation()	
		.when()
			.get("https://www.batch8-api-site.dev.cc/wp-json/wp/v2/posts")
		.then()	
			.statusCode(200) ; 
		
	}
	
	/* Given rest end point 
	 * 		https://www.batch8-api-site.dev.cc/wp-json/wp/v2/posts/24
	 * When i send a HTTP Get request to the server 
		Then i should get 200 OK result as status code 
		and id field should be 24
	 * */
	@Test
	public void idTest() {
		
	given()
		.relaxedHTTPSValidation()	
	.when()
		//.get("https://www.batch8-api-site.dev.cc/wp-json/wp/v2/posts/24")
		  .log().all()
		  .get("/posts/{id}",24)
	.then()
		.statusCode(200)
		.and()
		.assertThat()
		.body("id", equalTo(24) ) 
		.body("title.rendered", equalTo("Vadym title"))
		
		; 
		
		
	}
	
	
	@Test
	public void idTest_withLogDetail() {
		
	given()
		.relaxedHTTPSValidation()	
	.when()
		.log().all()
		.get("https://www.batch8-api-site.dev.cc/wp-json/wp/v2/posts/24")
	.then()
		.log().all()
		.statusCode(200)
		.and()
		.body("id", equalTo(24) ) 
		.body("title.rendered", equalTo("Vadym title"))
		
		; 
		
	}
	
	
	@Test
	public void Four_Test(){
		

		given()
			.relaxedHTTPSValidation()	
		.when()
			.get("/posts")
		.then()
			.statusCode(200) ; 
		
	}
	
	
}
