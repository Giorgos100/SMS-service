package org.gch.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.gch.entity.MessageStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class MessageResourceTest {

    @Test
    public void testSendValidMessage() {
        given()
                .contentType("application/json")
                .body("{\"sourceNumber\":\"35799654321\",\"destinationNumber\":\"35799123456\",\"content\":\"Hello\"}")
                .when()
                .post("/messages")
                .then()
                .statusCode(201)
                .body("sourceNumber", equalTo("35799654321"))
                .body("destinationNumber", equalTo("35799123456"))
                .body("content", equalTo("Hello"))
                .body("status", equalTo(MessageStatus.DELIVERED.toString()));
    }

    @Test
    public void testSendInvalidSourceNumber() {
        given()
                .contentType("application/json")
                .body("{\"sourceNumber\":\"ABC123\",\"destinationNumber\":\"35799123456\",\"content\":\"Hello\"}")
                .when()
                .post("/messages")
                .then()
                .statusCode(400)
                .body("details[0]", containsString("sourceNumber"));
    }

    @Test
    public void testSendInvalidDestinationNumber() {
        given()
                .contentType("application/json")
                .body("{\"sourceNumber\":\"35799654321\",\"destinationNumber\":\"DEST!\",\"content\":\"Hello\"}")
                .when()
                .post("/messages")
                .then()
                .statusCode(400)
                .body("details[0]", containsString("destinationNumber"));
    }

    @Test
    public void testSendEmptyContent() {
        given()
                .contentType("application/json")
                .body("{\"sourceNumber\":\"35799654321\",\"destinationNumber\":\"35799123456\",\"content\":\"\"}")
                .when()
                .post("/messages")
                .then()
                .statusCode(400)
                .body("details[0]", containsString("content"));
    }

    @Test
    public void testGetAllMessages() {
        given()
                .accept("application/json")
                .when()
                .get("/messages")
                .then()
                .statusCode(200)
                .body("$", not(empty()));
    }
}
