package stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.junit.Assert;
import resources.APIResources;
import resources.PayloadCreation;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;


public class AddPlaceApiStepDefinition extends Utils {


    RequestSpecification request;

    Response response;
    public static String place_id;


    @Given("Add Place Payload with {string} {string} {string}")
    public void addPlacePayloadWith(String name, String language, String address) throws IOException {

        System.out.println("Execution Started");
        request = given().spec(requestSpecification()).queryParam("key","qaclick123").body(PayloadCreation.addPlacePayload(name,language,address));

    }


    @When("user calls {string} api with http {string} request")
    public void userCallsAddplaceApiWithHttpPostRequest(String apiType,String httpRequest) {

         APIResources resource = APIResources.valueOf(apiType);
         resource.getResource();

         if(httpRequest.equalsIgnoreCase("POST"))
         {
             response = request.when().post(resource.getResource());
         }
         else if(httpRequest.equalsIgnoreCase("GET"))
         {
             response = request.when().get(resource.getResource());
         }
         else if(httpRequest.equalsIgnoreCase("DELETE"))
         {
             response = request.when().delete(resource.getResource());
         }


    }

    @Then("API call got sucess with status code {int}")
    public void apiCallGotSucessWithStatusCode(int expectedStatusCode) {
        int actualStatusCode = response.then().spec(responseSpecification()).extract().response().getStatusCode();
        Assert.assertEquals(expectedStatusCode,actualStatusCode);

    }

    @And("{string} in response body is {string}")
    public void statusInResponseBodyIsOK(String key,String value) {

            String expectedValue = getJsonPathValue(response,key);
            Assert.assertEquals(value, expectedValue);
            System.out.println("Place got added");


    }


    @Given("Get Place Payload with {string}")
    public void getPlacePayloadWith(String place_id) throws IOException {

        request=given().spec(requestSpecification()).queryParam("key","qaclick123")
                .queryParam("place_id",place_id);


    }

    @And("verify place_id created maps to {string} using {string}")
    public void verifyPlace_idCreatedMapsToUsing(String expectedName, String apiType) throws IOException {

       place_id= getJsonPathValue(response,"place_id");
        request=given().spec(requestSpecification()).queryParam("key","qaclick123")
                .queryParam("place_id",place_id);
        userCallsAddplaceApiWithHttpPostRequest(apiType,"GET");
        String actualName = getJsonPathValue(response,"name");
        Assert.assertEquals(actualName,expectedName);



    }

    @Given("Delete Place Payload")
    public void deletePlacePayload() throws IOException {

        request = given().spec(requestSpecification()).queryParam("key","qaclick123")
                .body(PayloadCreation.getDeletePlacePayload(place_id));
    }
}
