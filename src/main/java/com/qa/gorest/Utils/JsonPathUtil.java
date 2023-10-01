package com.qa.gorest.Utils;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.jayway.jsonpath.ReadContext;
import com.qa.gorest.frameworkExceptions.APIFrameworkExceptions;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class JsonPathUtil {

    public <T> T read(Response response, String jsonPath){
        ReadContext jp = JsonPath.parse(response.getBody().asString());
        try{
            return jp.read(jsonPath);
        }catch ( PathNotFoundException e){
            e.printStackTrace();
            throw new APIFrameworkExceptions(jsonPath + "path not found");
        }

    }

    public <T> List<T> readList(Response response, String jsonPath){
        ReadContext jp = JsonPath.parse(response.getBody().asString());
        try{
            return jp.read(jsonPath);
        }catch ( PathNotFoundException e){
            e.printStackTrace();
            throw new APIFrameworkExceptions(jsonPath + "path not found");
        }

    }

    public <T> List<Map<T,T>> readListOfMaps(Response response, String jsonPath){
        ReadContext jp = JsonPath.parse(response.getBody().asString());
        try{
            return jp.read(jsonPath);
        }catch ( PathNotFoundException e){
            e.printStackTrace();
            throw new APIFrameworkExceptions(jsonPath + "path not found");
        }

    }
}
