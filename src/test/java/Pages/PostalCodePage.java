package Pages;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import java.text.Normalizer;

public class PostalCodePage {
   List <Response> responseList  = new ArrayList<>();
   Response response;
   List<String> citiesLst = new ArrayList<>();
   List<String> postcodesLst = new ArrayList<>();
   List<String> streetsLst = new ArrayList<>();

    public void getEndpoint(String endpoint)
    {
        RestAssured.baseURI = endpoint;
    }
    public void callTheEndPoint(List<Integer> postalCode)
    {
        responseList.clear();
        for (int postCode:postalCode) {
        response = given()
                    .pathParam("postcode", postCode)
                    .when()
                    .get("/{postcode}");
        responseList.add(response);
        }
    }

    public void verifyResponseCode(int statusCode)
    {
        for (Response response : responseList) {
            response.then().statusCode(statusCode);
        }
    }

    public void verifyTheCity(List<String> CityName)
    {
        for (int i=0; i<responseList.size(); i++) {
            if (i==0) {
                String responseBody = responseList.get(i).asString();
                JsonPath jsonPath = new JsonPath(responseBody);
                int totalCities = jsonPath.getList("Cities").size();
                Assert.assertEquals(totalCities,1,"Either no or more than one cities are found!");
                String cityNameObtained = jsonPath.getString("Cities["+i+"]");
                Assert.assertEquals(CityName.get(i), cityNameObtained, "The response does not contain the expected city "+CityName.get(i));
            }
            else
            {
                String responseBody = responseList.get(i).asString();
                JsonPath jsonPath = new JsonPath(responseBody);
                int totalCities = jsonPath.getList("Cities").size();
                Assert.assertEquals(totalCities,3,"Total cities are not equal to 3");
                for (int j=0;j<totalCities;j++) {
                    String cityNameObtained = jsonPath.getString("Cities["+j+"]");
                    Assert.assertEquals(CityName.get(j+1), cityNameObtained, "The response does not contain the expected city "+CityName.get(j+1));
                }
            }
        }
    }

    public void verifyTheEmptyResponseBody()
    {
        for (Response response : responseList) {
            response.then().assertThat().body(is(""));
        }
    }

    public void getTheCities(List<String> Cities)
    {
       citiesLst.addAll(Cities);
    }

    public void getThePostal(List<String> postcodes)
    {
        postcodesLst.addAll(postcodes);
    }

    public void callTheEndPointforStreets()
    {
        int j=0;
        responseList.clear();
        for (String city : citiesLst) {
            response = given()
                    .when()
                    .get(postcodesLst.get(j) + "/" + city + "/streets");
            if (j == 0)
                j++;
            responseList.add(response);
        }
    }
    public void verifyTheNumberOfStreets(List<Integer> streetCount) throws InterruptedException {

        for (int i=0; i<responseList.size();i++)
        {
         String responseBody = responseList.get(i).asString();
         JsonPath jsonPath = new JsonPath(responseBody);
         int totalStreets = jsonPath.getList("Streets").size();
         int expectedStreets = streetCount.get(i);
         Assert.assertEquals(expectedStreets,totalStreets, "The total number of streets for "+citiesLst.get(i)+" is not equal to "+expectedStreets);
            for (int j=0;j<totalStreets;j++) {
                String city =  jsonPath.getString("Streets["+j+"]");
                streetsLst.add(city);
            }
           verifyTheSpecialGermanCharacters(streetsLst, i);
           streetsLst.clear();
        }
    }

    private void verifyTheSpecialGermanCharacters(List<String> StreetList, int counter) throws InterruptedException {
        if(citiesLst.get(counter).equalsIgnoreCase("Berlin")) {
            Assert.assertTrue(StreetList.get(2).contains("-"), StreetList.get(2)+" Does not display -");
            Assert.assertTrue(StreetList.get(8).contains("\u00FC"), StreetList.get(8)+" Does not display ü");
            Assert.assertTrue(StreetList.get(21).contains("\u00DF"), StreetList.get(21)+" Does not display ß");
        }
        else if(citiesLst.get(counter).equalsIgnoreCase("Fischerbach")) {
            Assert.assertTrue(StreetList.get(0).contains("\u00F6"), StreetList.get(0)+" Does not display ö");
            Assert.assertTrue(StreetList.get(6).contains("-"), StreetList.get(6)+" Does not display -");
            Assert.assertTrue(StreetList.get(7).contains("\u00FC"), StreetList.get(7)+" Does not display ü");
        }
        else if (citiesLst.get(counter).equalsIgnoreCase("Haslach")) {
            Assert.assertTrue(StreetList.get(9).contains("\u00FC"), StreetList.get(9)+" Does not display ü");
            Assert.assertTrue(StreetList.get(11).contains("\u00E4"), StreetList.get(11)+" Does not display ä");
            Assert.assertTrue( StreetList.get(12).contains("\u00DF"), StreetList.get(11)+" Does not display ß");
        }
        else {
            Assert.assertTrue(StreetList.get(8).contains("\u00FC"), StreetList.get(8)+" Does not display ü");
            Assert.assertTrue(StreetList.get(10).contains("-"), StreetList.get(10)+" Does not display -");
            Assert.assertTrue(StreetList.get(39).contains("\u00DF"), StreetList.get(39)+" Does not display ß");
        }
    }
}