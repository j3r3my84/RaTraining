package com.endava.examples;

import com.github.javafaker.Faker;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

public class TwitterTest {

    private static final String KEY = "xDpvjjIfui8Su72Ti1AR1CKzi";
    private static final String KEY_SECRET = "HobgaN3k3jn0vk11ItpV1CSWhyzpIm0Nf2FXbF01wZmDNxmyxl";
    private static final String TOKEN = "278136668-zJNcE6VzUubCamfnS29EEw1S0HyRUDufFl42AbUk";
    private static final String TOKEN_SECRET = "2Q5WhderaUy2Tk8tL7n400L4ddoXm6STWtasc6yKpM3Wf";

    private RequestSpecification createRequestSepecification() {
        return given().baseUri("https://api.twitter.com").basePath("/1.1/statuses").auth().oauth(KEY, KEY_SECRET, TOKEN, TOKEN_SECRET);
    }


    @Test
    public void postTwitterTest() {
        createRequestSepecification().queryParam("status", "Hello world!!!").post("/update.json").then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getTwitterTest() {
        String message = new Faker().ancient().god();

        ValidatableResponse postResponse = createRequestSepecification().queryParam("status", message).log().all().post("/update.json").prettyPeek().then().statusCode(HttpStatus.SC_OK);

        String id = postResponse.extract().jsonPath().getString("id_str");

        createRequestSepecification().get("/user_timeline.json").prettyPeek().then().statusCode(HttpStatus.SC_OK).body("id_str", hasItem(id)).body("text", hasItem(message));

    }


}


//         https://twitter.com/j3r3my84


//        Api Key: xDpvjjIfui8Su72Ti1AR1CKzi
//        Api Key secret: HobgaN3k3jn0vk11ItpV1CSWhyzpIm0Nf2FXbF01wZmDNxmyxl
//        Access Token: 278136668-zJNcE6VzUubCamfnS29EEw1S0HyRUDufFl42AbUk
//        Access Token Secret: 2Q5WhderaUy2Tk8tL7n400L4ddoXm6STWtasc6yKpM3Wf


//        Documentation: https://developer.twitter.com/en/docs/authentication/oauth-1-0a
//
//        https://api.twitter.com/1.1/statuses + /update.json
//        -queryParam - status
