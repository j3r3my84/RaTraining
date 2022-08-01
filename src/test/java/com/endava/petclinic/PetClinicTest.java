package com.endava.petclinic;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PetClinicTest {

    @Test
    public void getOwnerById(){
        given().baseUri( "http://api.petclinic.mywire.org" )
                .basePath( "/petclinic" )
                .port( 80 )
                .pathParam( "ownerId", 1 )
                .when()
                .get( "/api/owners/{ownerId}" )
                .prettyPeek()
                .then()
                .statusCode( HttpStatus.SC_OK )
                .body( "id", is( 1 ) )
                .body( "firstName", is( "George" ) )
                .body( "firstName", containsString( "org" ) )
                .body( "lastName", startsWith( "Fr" ) )
                .body( "city", equalToIgnoringCase( "madiSon" ) )
                .body( "telephone", hasLength( 10 ) );
    }

    @Test
    public void postOwnersTest(){
        ValidatableResponse response = given().baseUri( "http://api.petclinic.mywire.org" )
                .port( 80 )
                .basePath( "/petclinic" )
                .contentType( ContentType.JSON )
                .body( "{\n" +
                        "  \"address\": \"Magheru 5\",\n" +
                        "  \"city\": \"Bucharest\",\n" +
                        "  \"firstName\": \"Adrian\",\n" +
                        "  \"id\": null,\n" +
                        "  \"lastName\": \"Ieremiea\",\n" +
                        "  \"telephone\": \"1234567890\"\n" +
                        "}" )
                .when().log().all()
                .post( "/api/owners" ).prettyPeek()
                .then()
                .statusCode( HttpStatus.SC_CREATED );

        Integer ownerId = response.extract().jsonPath().getInt( "id" );

        given().baseUri( "http://api.petclinic.mywire.org" )
                .basePath( "/petclinic" )
                .port( 80 )
                .pathParam( "ownerId", ownerId )
                .when()
                .get("/api/owners/{ownerId}").prettyPeek()
                .then()
                .statusCode( HttpStatus.SC_OK )
                .body( "id", is( ownerId ) );

    }
}
