package com.example.week11;

public class LanguageChanger {

    private static LanguageChanger languageChanger = new LanguageChanger();
    String langCode;

    private LanguageChanger(){
        langCode = "en";
    }
    public static LanguageChanger getInstance(){
        return languageChanger;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public String getLangCode() {
        return langCode;
    }
}
