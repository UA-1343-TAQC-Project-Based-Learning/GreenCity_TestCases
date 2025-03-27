package com.greencity.modules;




import com.google.gson.Gson;
import com.greencity.data.LoginDto;
import com.greencity.data.TesterUser;
import okhttp3.*;


import java.io.IOException;

public class GreenCityPost {
    public LoginDto login(TesterUser testerUser) {
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody;
        Request request;
        Response response = null;
        String resultJson = null;
        LoginDto loginDto;
        //
        // Login
        String jsonBody =  new StringBuilder()
                .append("{")
                .append("\"email\":\"" + testerUser.getEmail() + "\",")
                .append("\"password\":\"" + testerUser.getPassword() + "\",")
                .append("\"secretKey\":\"" + testerUser.getSecretKey() + "\"")
                .append("}").toString();
        requestBody = RequestBody.create(jsonBody,
                MediaType.parse("application/json; charset=utf-8"));
        request = new Request.Builder()
                .url(testerUser.getUrl())
                //.addHeader("Content-Type", "application/json")
                .post(requestBody)
                .build();
        try {
            response = client.newCall(request).execute();
            resultJson = response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("resultJson = " + resultJson);
        // check
        if (resultJson.contains("accessToken")) {
            loginDto = gson.fromJson(resultJson, LoginDto.class);
        } else {
            throw new RuntimeException(resultJson);
        }
        return loginDto;
    }
}