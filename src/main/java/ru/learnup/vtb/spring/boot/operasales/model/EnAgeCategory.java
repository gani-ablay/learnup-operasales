package ru.learnup.vtb.spring.boot.operasales.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum EnAgeCategory {
    G("G"),
    PG("PG"),
    PG13("PG-13"),
    R("R"),
    NC17("NC-17");

    private String code;

    private EnAgeCategory(String code) {
        this.code=code;
    }

/*
    @JsonCreator
    public static EnAgeCategory decode(final String code) {
        return Stream.of(EnAgeCategory.values()).filter(targetEnum -> targetEnum.code.equals(code)).findFirst().orElse(null);
    }

    @JsonValue
    public String getCode() {
        return code;
    }
*/
}


