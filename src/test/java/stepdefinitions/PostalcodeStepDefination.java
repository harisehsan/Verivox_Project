package stepdefinitions;

import Pages.*;
import cucumber.api.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class PostalcodeStepDefination {

     PostalCodePage postalCodePage = new PostalCodePage();

    @Given("the address checking service endpoint: <{string}>")
    public void theAddressCheckingServiceEndpoint(String endpoint) {
        postalCodePage.getEndpoint(endpoint);
    }

    @Then("I verify that status code is <{int}>")
    public void iVerifyThatStatusCode(int statusCode) {
        postalCodePage.verifyResponseCode(statusCode);
    }

    @When("I request the cities for postcode")
    public void iRequestTheCitiesForPostcode(List<Integer> postalCodes) {
        postalCodePage.callTheEndPoint(postalCodes);
    }

    @And("I should receive a response with only cities:")
    public void iShouldReceiveAResponseWithOnlyCities(List<String> Cities) {
        postalCodePage.verifyTheCity(Cities);
    }


    @When("I get the cities:")
    public void iRequestTheCitiesBerlinFischerbachHaslachHofstettenWithPostalCode(List<String> Cities) {
        postalCodePage.getTheCities(Cities);
    }

    @And("postal code:")
    public void postalCode(List<String> postcodes) {
        postalCodePage.getThePostal(postcodes);
    }

    @And("I request the street for cities and post codes")
    public void iRequestTheStreetForCitiesAndPostCodes() {
        postalCodePage.callTheEndPointforStreets();

    }
    @And("I should receive a response with specified number of streets and verify Special German Characters:")
    public void iShouldReceiveAResponseWithSpecifiedNumberOfStreets(List<Integer> streetCount) throws InterruptedException {
        postalCodePage.verifyTheNumberOfStreets(streetCount);
    }


    @And("I verify that response body is Empty")
    public void iVerifyThatResponseBodyIsEmpty() {
        postalCodePage.verifyTheEmptyResponseBody();

    }
}
