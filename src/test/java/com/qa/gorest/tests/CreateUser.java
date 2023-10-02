package com.qa.gorest.tests;

import com.qa.gorest.Contant.APIHttpStatus;
import com.qa.gorest.POJO.User;
import com.qa.gorest.RestClient.RestClient;
import com.qa.gorest.Utils.ExcelUtil;
import com.qa.gorest.Utils.StringUtil;
import com.qa.gorest.base.BaseTest;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateUser extends BaseTest {

    @BeforeMethod
    public void createUserSetUp() {
        restClient = new RestClient(prop, baseURI);
    }

    @DataProvider
    public Object[][] getUser() {
        return new Object[][]{
                {"tom", "male", "active"},
                {"teena", "female", "inactive"},
                {"jack", "male", "active"}
        };
    }

    @DataProvider
    public Object[][] getUserExcel() {
        return ExcelUtil.getData("sheet1");
    }

    @Test(dataProvider="getUser", enabled = true)
    public void createUserTest(String name, String gender, String status) {

        User user = new User(name, StringUtil.generateEmail(), gender, status);

        int userId = restClient.post(GOREST_GET, user, "json", true, true).then()
                .log().all().assertThat().statusCode(APIHttpStatus.CREATED_201.getCode())
                .extract().path("id");

        System.out.println("The userId is " + userId);

        restClient.get(GOREST_GET + "/" + userId, true, true)
                .then().log().all().assertThat().statusCode(APIHttpStatus.OK_200.getCode())
                .body("name", Matchers.equalTo(user.getName()));
    }

    @Test(dataProvider="getUserExcel")
    public void createUserTestUsingExcel(String name, String gender, String status) {

        User user = new User(name, StringUtil.generateEmail(), gender, status);

        int userId = restClient.post(GOREST_GET, user, "json", true, true).then()
                .log().all().assertThat().statusCode(APIHttpStatus.CREATED_201.getCode())
                .extract().path("id");

        System.out.println("The userId is " + userId);

        restClient.get(GOREST_GET + "/" + userId, true, true)
                .then().log().all().assertThat().statusCode(APIHttpStatus.OK_200.getCode())
                .body("name", Matchers.equalTo(user.getName()));
    }
}
