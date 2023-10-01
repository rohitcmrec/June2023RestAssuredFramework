package com.qa.gorest.tests;

import com.qa.gorest.Contant.APIHttpStatus;
import com.qa.gorest.RestClient.RestClient;
import com.qa.gorest.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class GetFlightDetails extends BaseTest {

    String accessToken;

    @Parameters({"grantType","clientId","clientSecret"})
    @BeforeMethod
    public void setUp(String grantType, String clientId, String clientSecret){
        restClient = new RestClient(prop, baseURI);
        accessToken = restClient.getAccessToken(FLIGHT_TOKEN, grantType, clientId, clientSecret);
    }

    @Test
    public void getFlightDetails(){

        RestClient restClient = new RestClient(prop, baseURI);
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer "+accessToken);

        Map<String, Object> query = new HashMap<>();
        query.put("origin","PAR");
        query.put("maxPrice", 200);

        restClient.get(FLIGHT_GET,header, query,false, true)
                .then().log().all().assertThat().statusCode(APIHttpStatus.OK_200.getCode());

    }
}
