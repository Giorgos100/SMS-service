package org.gch.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.gch.entity.MessageStatus;
import org.gch.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.anyOf;

@QuarkusTest
class MessageResourceTest {

    @Inject
    MessageRepository repository;

    @BeforeEach
    @Transactional
    void setup() {
        repository.deleteAllMessages();
    }

    @Test
    void testSendMessage() {
        String json = "{\"sourceNumber\":\"12345\",\"destinationNumber\":\"67890\",\"content\":\"Hello\"}";

        given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("/messages")
                .then()
                .statusCode(201)
                .body("sourceNumber", is("12345"))
                .body("destinationNumber", is("67890"))
                .body("content", is("Hello"))
                .body("status", anyOf(is("PENDING"), is("DELIVERED"), is("FAILED")));
    }

    @Test
    void testGetAllMessages() {
        String json = "{\"sourceNumber\":\"111\",\"destinationNumber\":\"222\",\"content\":\"Msg\"}";
        given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("/messages")
                .then()
                .statusCode(201);

        given()
                .when()
                .get("/messages")
                .then()
                .statusCode(200)
                .body("$", hasSize(1));
    }

    @Test
    void testSearchMessages() {
        String json = "{\"sourceNumber\":\"555\",\"destinationNumber\":\"666\",\"content\":\"Hello\"}";
        given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("/messages")
                .then()
                .statusCode(201);

        given()
                .when()
                .get("/messages/search?sourceNumber=555&destinationNumber=666")
                .then()
                .statusCode(200)
                .body("$", hasSize(1));
    }

    @Test
    void testGetMessageById() {
        String json = "{\"sourceNumber\":\"888\",\"destinationNumber\":\"999\",\"content\":\"Hi\"}";
        long id =
                given()
                        .contentType("application/json")
                        .body(json)
                        .when()
                        .post("/messages")
                        .then()
                        .statusCode(201)
                        .extract()
                        .path("id");

        given()
                .when()
                .get("/messages/" + id)
                .then()
                .statusCode(200)
                .body("id", is((int) id));
    }
}
