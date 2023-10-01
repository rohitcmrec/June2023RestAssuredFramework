package com.qa.gorest.tests;

import com.qa.gorest.Contant.APIHttpStatus;
import com.qa.gorest.POJO.User;
import com.qa.gorest.RestClient.RestClient;
import com.qa.gorest.Utils.StringUtil;
import com.qa.gorest.base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.BeforeMethod;

public class APISchemaValidation extends BaseTest {

    @BeforeMethod
    public void createUserSetUp() {
        restClient = new RestClient(prop, baseURI);
    }

    public void createUserSchemaValidation() {

        User user = new User("tom", StringUtil.generateEmail(), "male", "active");

        restClient.post(GOREST_GET, user, "json", true, true).then()
                .log().all().assertThat().statusCode(APIHttpStatus.CREATED_201.getCode())
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("createUser.json"));
    }
}
