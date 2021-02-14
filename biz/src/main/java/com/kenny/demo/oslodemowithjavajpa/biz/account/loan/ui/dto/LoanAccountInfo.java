package com.kenny.demo.oslodemowithjavajpa.biz.account.loan.ui.dto;

import lombok.*;

public class LoanAccountInfo {

    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class In {
        private String acno;
    }

    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Out {
        private String loanAcno;
        private String firstDivisionCode;
        private String loanAcnoStcd;
        private String acnoName;
    }
}
