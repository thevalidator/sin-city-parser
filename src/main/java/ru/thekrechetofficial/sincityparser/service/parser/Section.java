/*
 * The Krechet Software
 */
package ru.thekrechetofficial.sincityparser.service.parser;

/**
 *
 * @author theValidator <the.validator@yandex.ru>
 */
public enum Section {
    
    ALL("0"),           // <option value="0"> Рубрика         
    VIRT("1"),          // <option value="1"> Вирт            
    REAL("2"),          // <option value="2"> Реал            
    VISIT("3"),         // <option value="3"> Реал выезд      
    APARTMENTS("4"),    // <option value="4"> Реал апартаменты
    CAR("5");           // <option value="5"> Реал в авто

    private final String value;

    private Section(String option) {
        this.value = option;
    }

    public String getValue() {
        return value;
    }
    
}
