/*
 * The Krechet Software
 */
package ru.thekrechetofficial.sincityparser.service.parser;

/**
 *
 * @author theValidator <the.validator@yandex.ru>
 */
public enum Section {
    
    ALL("0"),
    VIRT("1"),
    REAL("2"),
    VISIT("3"),
    APARTMENTS("4"),
    CAR("5");

    private final String value;

    private Section(String option) {
        this.value = option;
    }

    public String getValue() {
        return value;
    }
    
}
