package com.ua.project.practice_270324_java_springboot.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DotEnvExtractor {
    public static String extractLineByAttributeName(String attributeName)  {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(".env")) {
            properties.load(fileInputStream);
            return properties.getProperty(attributeName);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return "";
    }
}
