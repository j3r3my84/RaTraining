package com.endava.examples;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class OpenWeatherMapTest {

    private static final String APP_ID = "14fdae5097e006752d94eed9ea503201";

    @Test
    public void getWeatherTest(){
        given().baseUri("https://api.openweathermap.org")
                .basePath("/data/2.5")
                .queryParam("appid",APP_ID)
                .queryParam("units","metric")
                .queryParam("q","Bucharest").log().all()
                .get("/weather").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Bucharest","Craiova","Constanta"})
    public void getWeatherTestParam(String city){
        given().baseUri("https://api.openweathermap.org")
                .basePath("/data/2.5")
                .queryParam("appid",APP_ID)
                .queryParam("units","metric")
                .queryParam("q",city).log().all()
                .get("/weather").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("name",is(city));
    }
}
