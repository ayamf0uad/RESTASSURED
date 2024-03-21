import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class APITest {

    @Test
    public void myFirstRaTest(){
        assertThat(RestAssured.config(), instanceOf(RestAssuredConfig.class));
    }

    @Test
    public void newTest(){
        Response response = given().get("https://restful-booker.herokuapp.com/ping/");
        int statusCode = response.getStatusCode();
        assertThat(statusCode, is(201));
    }

    @Test
    public void myHeaderTest(){
        Header acceptHeader = new Header("accept","application/json");

        Response response = given()
                .header(acceptHeader)
                .get("https://restful-booker.herokuapp.com/booking/1");

        int statusCode = response.getStatusCode();

        assertThat(statusCode, is(200));
    }

    @Test
    public void myReadingHeaderTest(){
        // The first line is where we send the HTTP request to the Web API and get a response back, which we store as Rest-Assured Response object. On the second line we take the response and call the method as() and provide it with a reference to our BookingResponse class by adding in BookingResponse.class as a parameter. This tells Rest-Assured to map the HTTP response body to our Java classes. If the class didn’t match with the response body then you would have received an error. Finally, we extract the string value using getAdditionalNeeds() and then assert that it equals Breakfast.
        Response response = given().get("https://restful-booker.herokuapp.com/booking/2");
        BookingResponse responseBody = response.as(BookingResponse.class);
        String additionalneeds = responseBody.getAdditionalneeds();
        assertThat(additionalneeds, is("Breakfast"));
    }

    @Test
    public void myPOSTTest(){
        AuthPayload authPayload = new AuthPayload("admin", "password123");

        Response response = given()
                .body(authPayload)
                .contentType("application/json")
                .post("https://restful-booker.herokuapp.com/auth");

        String authResponse = response.getBody().print();

        assertThat(authResponse, containsString("token"));
    }

}
