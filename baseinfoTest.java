package org.example.testStendGB;

import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class baseinfoTest
{

    static Properties prop = new Properties();

    private static InputStream configFile;
    private static String apiKey;
    private static String baseUrl;

    @BeforeAll
    static void initTest() throws IOException {
        configFile = new FileInputStream("src/main/resources/testStendGB.properties");
        prop.load(configFile);
        apiKey = prop.getProperty("apiKey");
        baseUrl = prop.getProperty("baseUrl");
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }





}
