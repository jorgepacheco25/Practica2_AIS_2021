package es.urjc.code.daw.library.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.path.json.JsonPath.*;
import static io.restassured.RestAssured.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestTest {
	
	@LocalServerPort
    int port;
	
	@BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }
    
    
    @Test
    void whenCreateNewBook_ThenGetBackTest() {
        
    	Response response = given().
        request()
            .body("{ \"title\" : \"Virtual Hero\", \"description\": \"El personaje más carismático de la red, "
            		+ "el youtuber con más seguidores del panorama trolero actual, "
            		+ "protagoniza este cómic inspirado en los videojuegos, "
            		+ "que llevará a sus seguidores a explorar mundos diversos, "
            		+ "a combatir contra criaturas hostiles o a superar pruebas imposibles\" }")
            .contentType(ContentType.JSON).
	    when().
	         post("/api/books/").
		thenReturn();
    	
    	Integer id = from(response.getBody().asString()).get("id");
    	
    	when()
    		.get("/api/books/{id}", id)
    		.then()
				.statusCode(200)
				.body(
					"id", equalTo(id),
					"title", equalTo("Virtual Hero"),
					"description", equalTo("El personaje más carismático de la red, "
							+ "el youtuber con más seguidores del panorama trolero actual, "
							+ "protagoniza este cómic inspirado en los videojuegos, "
							+ "que llevará a sus seguidores a explorar mundos diversos, "
							+ "a combatir contra criaturas hostiles o a superar pruebas imposibles"));

    }
    
    
    @Test 
    void whenDeleteBook_ThenNotGetBackTest() {
    	
    	Response response = given().
    	        request()
    	            .body("{ \"title\" : \"El código Da Vinci\", \"description\": \"Dan Brown\" }")
    	            .contentType(ContentType.JSON).
    		    when().
    		         post("/api/books/").
    			thenReturn();
    	
    	Integer id = from(response.getBody().asString()).get("id");
    	
    	when()
			.get("/api/books/{id}", id)
			.then()
				.statusCode(200)
					.body(
						"id", equalTo(id),
						"title", equalTo("El código Da Vinci"),
						"description", equalTo("Dan Brown"));
    	
    	when()
    		.delete("/api/books/{id}", id)
    		.then().statusCode(200);
    	
    	when()
    		.get("/api/books/{id}", id)
    		.then()
    			.statusCode(404);
    	    	
    	
    }
	

}


