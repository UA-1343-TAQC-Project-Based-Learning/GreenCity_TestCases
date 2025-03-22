package com.greencity.utils;



import io.github.cdimascio.dotenv.Dotenv;

public class DotenvUtil {
    private final String SECRET_KEY = "secretKey";
   // private final String FILE_NAME = ".env";
    //
    private Dotenv dotenv = null;

    public DotenvUtil() {
        dotenv = Dotenv.load();
    }

    public String getSecretKey() {
        System.out.println("***secretKey = " + dotenv.get(SECRET_KEY));
        return dotenv.get(SECRET_KEY);
    }
}