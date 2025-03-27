package com.greencity.data;




import java.util.List;

public final class TesterUserRepository {

    private TesterUserRepository() {
    }

    public static TesterUser getDefault() {
        return getValidUserSecret();
    }


    public static TesterUser getValidUserSecret() {

        return new TesterUser("tyv09754@zslsz.com", "Qwerty_1",
                "dotenvUtil.getSecretKey()",
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
