package com.endava.examples;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class FacebookTest {

    @Test
    public void addUserTest(){
        given().baseUri("https://graph.facebook.com")
                .basePath("/v14.0")
                .auth()
                .oauth2("455136538609782|77cc2b5e28ee35c76bead0348506af38")
                .pathParam("application-id","455136538609782")
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "\t\"name\": \"Ieremiea Adrian\",\n" +
                        "\t\"password\": \"1232134213\"\n" +
                        "}").log().all()
                .post("/{application-id}/accounts/test-users").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
