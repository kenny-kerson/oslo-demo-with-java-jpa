package com.kenny.demo.oslodemowithjavajpa.common.code;

public enum StatusCode {

    OK( "OK", "정상응답" ),
    ERROR( "ERROR", "정상응답" ),
    ;

    private final String code;
    private final String title;

    StatusCode(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }
}
