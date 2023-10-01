package com.qa.gorest.base;

import com.qa.gorest.ConfigurationManagement.ConfigManager;
import com.qa.gorest.RestClient.RestClient;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Properties;

public class BaseTest {

    static String year;
    public static final String GOREST_GET="/public/v2/users";
    public static final String CIRCUIT_GET="/api/f1/2017/circuits.json";
    public static final String FLIGHT_TOKEN="/v1/security/oauth2/token";
    public static final String FLIGHT_GET="/v1/shopping/flight-destinations";


    protected Properties prop;
    protected ConfigManager configManager;
    protected RestClient restClient;
    protected String baseURI;

    @Parameters({"baseURI"})
    @BeforeTest
    public void setUp(String baseURI){
        RestAssured.filters(new AllureRestAssured());
        configManager = new ConfigManager();
        prop = configManager.initProp();
        this.baseURI=baseURI;
        //restClient = new RestClient(prop, baseURI);
        year = System.getProperty("year");

    }
}
