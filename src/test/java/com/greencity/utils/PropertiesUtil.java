package com.greencity.utils;


import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUtil {
    private final String PATH_SEPARATOR = "/";
    private final String BASE_URL = "base.url";
    private final String CAPTCHA_TOKEN = "captcha.token";
    //
    private Properties appProps = null;
    private String filename;

    public PropertiesUtil() {
        appProps = new Properties();
        filename = "application-test.properties";
    }

    public PropertiesUtil(String filename) {
        this();
        this.filename = filename;
    }

    private String getFullPath() {
        String path = this.getClass().getResource(PATH_SEPARATOR + filename).getPath();
        System.out.println("\tpath = " + path);
        return path;
    }

    private void readProperties() {
        try (FileInputStream fileInputStream = new FileInputStream(getFullPath())) {
            appProps.load(fileInputStream);
            System.out.println("appProps = " + appProps);
        } catch (Exception e){
            System.out.println("ERROR Reading " + filename + "  Message = " + e.getMessage());
        }
    }

    public String readBaseUrl() {
        // TODO
        readProperties();
        String baseUrl = appProps.getProperty(BASE_URL, null);
        return baseUrl;
    }

    public String readCaptchaToken() {
        // TODO
        readProperties();
        String captchaToken = appProps.getProperty(CAPTCHA_TOKEN, null);
        System.out.println("captchaToken = " + captchaToken);
        return captchaToken;
    }
}
