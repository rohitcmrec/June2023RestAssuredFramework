package com.qa.gorest.ConfigurationManagement;

import com.qa.gorest.frameworkExceptions.APIFrameworkExceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    Properties prop;
    FileInputStream fileInputStream;

    public Properties initProp() {

        prop = new Properties();
        String env = System.getProperty("env");
        try {
            if (env == null) {
                System.out.println("running on default env qa");
                fileInputStream = new FileInputStream("./src/test/resources/config/config.properties");
            } else {
                System.out.println("running on env " + env);
                switch (env.toLowerCase()) {
                    case "qa1":
                        fileInputStream = new FileInputStream("./src/test/resources/config/qa1-config.properties");
                        break;
                    case "qa2":
                        fileInputStream = new FileInputStream("./src/test/resources/config/qa2-config.properties");
                        break;
                    default:
                        System.out.println("please pass the right env");
                        throw new APIFrameworkExceptions("Invalid Env");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prop.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return prop;
    }
}
