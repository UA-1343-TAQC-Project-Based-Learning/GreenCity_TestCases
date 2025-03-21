package com.greencity.data;


import com.greencity.utils.DotenvUtil;

public final class TesterUserRepository {

    private TesterUserRepository() {
    }

    public static TesterUser getDefault() {
        return getValidUser();
    }

    public static TesterUser getValidUser() {
        return new TesterUser("tyv09754@zslsz.com", "Qwerty_1",
                System.getenv().get("TESTER_TOKEN"),
                "QwertyY", "https://greencity-user.greencity.cx.ua/api/testers/sign-in");
    }

    public static TesterUser getValidUserSecret() {
        DotenvUtil dotenvUtil = new DotenvUtil();
        return new TesterUser("tyv09754@zslsz.com", "Qwerty_1",
                dotenvUtil.getSecretKey(),
                "QwertyY", "https://greencity-user.greencity.cx.ua/api/testers/sign-in");
    }

}
