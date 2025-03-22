package com.greencity.data;


import com.greencity.utils.PropertiesUtil;

public final class OwnSignInDtoRepository {

    private OwnSignInDtoRepository() {
    }

    public static OwnSignInDto getDefault() {
        return getValidSignInDto();
    }

    public static OwnSignInDto getValidSignInDto() {
        PropertiesUtil propertiesUtil = new PropertiesUtil();
        String captchaToken = propertiesUtil.readCaptchaToken();
        return new OwnSignInDto("tyv09754@zslsz.com", "Qwerty_1", captchaToken);
    }
}

