package Lesson3;

import org.junit.jupiter.api.Test;

import java.io.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class AccountTests extends BaseTest{
    @Test
    void getAccountInfoTest() {
        given()
                .header("Authorization", token)
                .when()
                .get("https://api.imgur.com/3/account/{username}", username)
                .then()
                .statusCode(200);
    }
    @Test
    void getAccountInfoWithLoggingTest() {
        given()
                .header("Authorization", "Bearer 81ed217eee6d991be324edc8754a07e4ce686bb9")
                .log()
                .method()
                .log()
                .uri()
                .when()
                .get("https://api.imgur.com/3/account/{username}", username)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void getAccountInfoWithAssertionsInGivenTest() {
        given()
                .header("Authorization", "Bearer 81ed217eee6d991be324edc8754a07e4ce686bb9")
                .log()
                .method()
                .log()
                .uri()
                .expect()
                .statusCode(200)
                .body("data.url", equalTo(username))
                .body("success", equalTo(true))
                .body("status", equalTo(200))
                .contentType("application/json")
                .when()
                .get("https://api.imgur.com/3/account/{username}", username)
                .prettyPeek();

    }



    private static void getProperties(){
        try (InputStream output = new FileInputStream("src/test/resources/application.properties")) {
            properties.load(output);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

