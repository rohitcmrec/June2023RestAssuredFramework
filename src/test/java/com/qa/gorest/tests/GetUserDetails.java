package com.qa.gorest.tests;

import com.qa.gorest.Contant.APIHttpStatus;
import com.qa.gorest.RestClient.RestClient;
import com.qa.gorest.base.BaseTest;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class GetUserDetails extends BaseTest {

    @BeforeMethod
    public void getUserSetUp() {
        restClient = new RestClient(prop, baseURI);
    }

    @Test
    public void getAllUserDetails() {

        restClient.get(GOREST_GET, true, true)
                .then().log().all().assertThat().statusCode(APIHttpStatus.OK_200.getCode());
    }

    @Test
    public void getUserDetail() {

        restClient.get(GOREST_GET+"/5213502", true, true)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("$", Matchers.hasKey("gender"))
                .body("status", Matchers.is(Matchers.equalTo("active")));
    }

    @Test
    public void getUserDetailWithQueryParam() {

        Map<String, Object> query = new HashMap<>();
        query.put("gender", "male");
        query.put("status", "active");

        restClient.get(GOREST_GET, null, query, true, true)
                .then()
                .log().all()
                .assertThat()
                .statusCode(APIHttpStatus.OK_200.getCode());
    }
}
