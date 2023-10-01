package com.qa.gorest.tests;

import com.qa.gorest.Contant.APIHttpStatus;
import com.qa.gorest.RestClient.RestClient;
import com.qa.gorest.Utils.JsonPathUtil;
import com.qa.gorest.base.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class GetCircuitDetails extends BaseTest {

    @BeforeMethod
    public void createUserSetUp() {
        restClient = new RestClient(prop, baseURI);
    }

    @Test
    public void circuitDetailsTest(){
        restClient.get(CIRCUIT_GET,false,true).then().log().all().statusCode(APIHttpStatus.OK_200.getCode());
    }

    @Test
    public void circuitDetailsTestJsonPath(){
        Response response = restClient.get(CIRCUIT_GET,false,true);
        JsonPathUtil ju = new JsonPathUtil();
        List<Map<String, String>> countryList = ju.readListOfMaps(response,"$.MRData.CircuitTable.Circuits[?(@.Location.long=='144.968')].Location.[\"country\",\"locality\"]");
        for(Map<String, String> country : countryList){
            System.out.println(country.get("country")+" "+ country.get("locality"));
        }
    }
}
