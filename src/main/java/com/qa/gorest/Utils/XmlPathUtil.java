package com.qa.gorest.Utils;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class XmlPathUtil {

    public <T> T get(Response response, String xmlExpression){
        XmlPath xmlPath = new XmlPath(response.getBody().asString());
        return xmlPath.get(xmlExpression);
    }

    public <T> List<T> getList(Response response, String xmlExpression){
        XmlPath xmlPath = new XmlPath(response.getBody().asString());
        return xmlPath.get(xmlExpression);
    }

    public <T> List<Map<T,T>> getListOfMaps(Response response, String xmlExpression){
        XmlPath xmlPath = new XmlPath(response.getBody().asString());
        return xmlPath.get(xmlExpression);
    }
}
