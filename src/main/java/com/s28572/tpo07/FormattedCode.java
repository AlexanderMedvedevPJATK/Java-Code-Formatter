package com.s28572.tpo07;

import java.io.Serializable;

public class FormattedCode implements Serializable {

    private final String id;
    private final String code;

    public FormattedCode(String id, String code) {
        this.id = id;
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }
}
