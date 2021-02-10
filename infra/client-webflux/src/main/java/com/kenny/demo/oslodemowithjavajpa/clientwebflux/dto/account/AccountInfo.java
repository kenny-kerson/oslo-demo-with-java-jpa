package com.kenny.demo.oslodemowithjavajpa.clientwebflux.dto.account;

import lombok.*;

public class AccountInfo {

    @Getter
    @Builder
    @ToString
    @NoArgsConstructor @AllArgsConstructor
    public static class In {
        private String acno;
    }

    @Getter
    @Builder
    @ToString
    @NoArgsConstructor @AllArgsConstructor
    public static class Out {
        private String acno;
        private String firstDivisionCode;
        private String acnoStcd;
        private String acnoName;
    }
}
