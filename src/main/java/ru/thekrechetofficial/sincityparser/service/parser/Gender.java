/*
 * The Krechet Software
 */
package ru.thekrechetofficial.sincityparser.service.parser;

/**
 *
 * @author theValidator <the.validator@yandex.ru>
 */
public enum Gender {

    MALE("M", "мужчина"),
    FEMALE("F", "женщина"),
    TRANS("T", "транс"),
    COUPLE("C", "пара");

    private final String value;
    private final String textName;

    private Gender(String value, String textName) {
        this.value = value;
        this.textName = textName;
    }

    public String getValue() {
        return value;
    }

    public String getTextName() {
        return textName;
    }

}
