package com.greencity.data;


import com.greencity.utils.DotenvUtil;

import java.util.List;

public final class TesterUserRepository {

    private TesterUserRepository() {
    }

    public static TesterUser getDefault() {
        return getValidUserSecret();
    }


    public static TesterUser getValidUserSecret() {
        DotenvUtil dotenvUtil = new DotenvUtil();
        return new TesterUser("tyv09754@zslsz.com", "Qwerty_1",
                dotenvUtil.getSecretKey(),
                "QwertyY", "https://greencity-user.greencity.cx.ua/api/testers/sign-in");
    }
    /*
    public List<User> fromCsv(String filename){
        return User.getByLists(new CSVReader(filename));
    }
    public List<User> fromCsv(){
        return fromCsv("dataUser.csv");
    }
    *
     */

}
