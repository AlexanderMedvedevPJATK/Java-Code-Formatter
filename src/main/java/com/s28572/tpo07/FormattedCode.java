package com.s28572.tpo07;

import java.io.Serializable;
import java.time.LocalDateTime;


public class FormattedCode implements Serializable {

    private String formattedCode;
    private LocalDateTime expirationDate;

    public FormattedCode(String formattedCode, int expiresIn) {
        this.formattedCode = formattedCode;
        this.expirationDate = LocalDateTime.now().plusSeconds(expiresIn);
    }

    public String getFormattedCode() {
        return formattedCode;
    }

    public void setFormattedCode(String formattedCode) {
        this.formattedCode = formattedCode;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}
