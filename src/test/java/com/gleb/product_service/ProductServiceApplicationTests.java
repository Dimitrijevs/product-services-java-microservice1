package com.gleb.product_service;

import java.util.Random;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)

// starts test on random port
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	// cretes a mongoDB container using Docker, connects it to spring automatically
	@ServiceConnection
	static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

	// gets random port
	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {

		// RestAsured - library for testing web apis

		// tells restAssured where my app is running
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	// starts the mongodb container
	static {
		mongoDBContainer.start();
	}

	@Test
	void shouldCreateProduct() {
		Random random = new Random();

		int iPhoneNumber = 7 + random.nextInt(10); // 7 - 16

		int price = 200 + random.nextInt(1201); // 200 - 1400

		// random json data
		String requestBody = String.format("""
				{
				    "name": "Iphone %d",
				    "description": "Iphone %d description",
				    "price": %d
				}
				""", iPhoneNumber, iPhoneNumber, price);

		// sends a post request, 3 chekcs at the end
		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product/create")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("Iphone " + iPhoneNumber))
				.body("price", Matchers.equalTo(price));
	}
}
