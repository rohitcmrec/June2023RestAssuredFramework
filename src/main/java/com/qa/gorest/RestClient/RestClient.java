package com.qa.gorest.RestClient;

import com.qa.gorest.frameworkExceptions.APIFrameworkExceptions;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class RestClient {

    private static RequestSpecBuilder requestSpecBuilder;
    /*private static final String BASE_URI = "https://gorest.co.in";
    private static final String BEARER_TOKEN = "572a3da7137f9a65c0bbe7ea7d749bc3be3feab3b3a6f46235a8ec8b85761e54";*/
    private String baseURL;
    private Properties prop;
    private boolean authAdded = false;

    public RestClient(Properties prop, String baseURL) {
        requestSpecBuilder = new RequestSpecBuilder();
        this.prop = prop;
        this.baseURL = baseURL;
    }

    public void addAuthorizationHeader() {
        if (!authAdded) {
            requestSpecBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("token"));
            authAdded=true;
        }
    }

    public void setContentType(String contentType) {
        switch (contentType.toLowerCase()) {
            case "json":
                requestSpecBuilder.setContentType(ContentType.JSON);
                break;
            case "xml":
                requestSpecBuilder.setContentType(ContentType.XML);
                break;
            case "text":
                requestSpecBuilder.setContentType(ContentType.TEXT);
                break;
            case "multipart":
                requestSpecBuilder.setContentType(ContentType.MULTIPART);
                break;
            default:
                System.out.println("Please pass the correct content type");
                throw new APIFrameworkExceptions("INVALIDCONTENTTYPE");
        }
    }

    public RequestSpecification createRequestSpec(boolean includeAuth) {
        requestSpecBuilder.setBaseUri(baseURL);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        return requestSpecBuilder.build();
    }

    public RequestSpecification createRequestSpec(Map<String, String> headerMap, boolean includeAuth) {
        requestSpecBuilder.setBaseUri(baseURL);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        if (headerMap != null) {
            requestSpecBuilder.addHeaders(headerMap);
        }
        return requestSpecBuilder.build();
    }

    public RequestSpecification createRequestSpec(Map<String, String> headerMap, Map<String, Object> queryParamMap, boolean includeAuth) {
        requestSpecBuilder.setBaseUri(baseURL);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        if (headerMap != null) {
            requestSpecBuilder.addHeaders(headerMap);
        }
        requestSpecBuilder.addQueryParams(queryParamMap);
        return requestSpecBuilder.build();
    }

    public RequestSpecification createRequestSpec(Object body, String contentType, boolean includeAuth) {
        requestSpecBuilder.setBaseUri(baseURL);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        setContentType(contentType);
        if (body != null) {
            requestSpecBuilder.setBody(body);
        }
        return requestSpecBuilder.build();
    }

    public RequestSpecification createRequestSpec(Object body, String contentType, Map<String, String> headerMap, boolean includeAuth) {
        requestSpecBuilder.setBaseUri(baseURL);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        setContentType(contentType);
        if (body != null) {
            requestSpecBuilder.setBody(body);
        }
        if (headerMap != null) {
            requestSpecBuilder.addHeaders(headerMap);
        }
        return requestSpecBuilder.build();
    }

    // https method call method

    public Response get(String serviceURL, boolean includeAuth, boolean log) {
        if (log) {
            return RestAssured.given().log().all().spec(createRequestSpec(includeAuth)).when().get(serviceURL);
        }
        return RestAssured.given().spec(createRequestSpec(includeAuth)).when().get(serviceURL);
    }

    public Response get(String serviceURL, Map<String, String> headers, boolean includeAuth, boolean log) {
        if (log) {
            return RestAssured.given().log().all().spec(createRequestSpec(headers, includeAuth)).when().get(serviceURL);
        }
        return RestAssured.given().spec(createRequestSpec(headers, includeAuth)).when().get(serviceURL);
    }

    public Response get(String serviceURL, Map<String, String> headers, Map<String, Object> queryParams, boolean includeAuth, boolean log) {
        if (log) {
            return RestAssured.given().log().all().spec(createRequestSpec(headers, queryParams, includeAuth)).when().get(serviceURL);
        }
        return RestAssured.given().spec(createRequestSpec(headers, queryParams, includeAuth)).when().get(serviceURL);
    }

    public Response post(String serviceURL, Object body, String contentType, boolean includeAuth, boolean log) {
        if (log) {
            return RestAssured.given().log().all().spec(createRequestSpec(body, contentType, includeAuth)).when().post(serviceURL);
        }
        return RestAssured.given().spec(createRequestSpec(body, contentType, includeAuth)).when().post(serviceURL);
    }

    public Response post(String serviceURL, String body, String contentType, Map<String, String> headers, boolean includeAuth, boolean log) {
        if (log) {
            return RestAssured.given().log().all().spec(createRequestSpec(body, contentType, headers, includeAuth)).when().post(serviceURL);
        }
        return RestAssured.given().spec(createRequestSpec(body, contentType, headers, includeAuth)).when().post(serviceURL);
    }

    public Response put(String serviceURL, String body, String contentType, boolean includeAuth, boolean log) {
        if (log) {
            return RestAssured.given().log().all().spec(createRequestSpec(body, contentType, includeAuth)).when().put(serviceURL);
        }
        return RestAssured.given().spec(createRequestSpec(body, contentType, includeAuth)).when().put(serviceURL);
    }

    public Response put(String serviceURL, String body, String contentType, Map<String, String> headers, boolean includeAuth, boolean log) {
        if (log) {
            return RestAssured.given().log().all().spec(createRequestSpec(body, contentType, headers, includeAuth)).when().put(serviceURL);
        }
        return RestAssured.given().spec(createRequestSpec(body, contentType, headers, includeAuth)).when().put(serviceURL);
    }

    public Response patch(String serviceURL, String body, String contentType, boolean includeAuth, boolean log) {
        if (log) {
            return RestAssured.given().log().all().spec(createRequestSpec(body, contentType, includeAuth)).when().patch(serviceURL);
        }
        return RestAssured.given().spec(createRequestSpec(body, contentType, includeAuth)).when().patch(serviceURL);
    }

    public Response patch(String serviceURL, String body, String contentType, Map<String, String> headers, boolean includeAuth, boolean log) {
        if (log) {
            return RestAssured.given().log().all().spec(createRequestSpec(body, contentType, headers, includeAuth)).when().patch(serviceURL);
        }
        return RestAssured.given().spec(createRequestSpec(body, contentType, headers, includeAuth)).when().patch(serviceURL);
    }

    public void delete(String serviceURL, boolean includeAuth, boolean log) {
        if (log) {
            RestAssured.given().log().all().spec(createRequestSpec(includeAuth)).when().delete(serviceURL);
        }
        RestAssured.given().spec(createRequestSpec(includeAuth)).when().delete(serviceURL);
    }

    public String getAccessToken(String serviceURL, String grantType, String clientId, String clientSecret){

        RestAssured.baseURI = baseURL;
        String access_token = RestAssured.given()
                                            .contentType(ContentType.URLENC)
                                            .formParam("grant_type", grantType)
                                            .formParam("client_id", clientId)
                                            .formParam("client_secret", clientSecret)
                                        .when().post(serviceURL)
                                        .then()
                                                .log().all()
                                                .assertThat()
                                                        .statusCode(200)
                                                .extract()
                                                        .path("access_token");
        return access_token;
    }

}
