package com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.customer;

import lombok.*;

import java.util.List;

public class BaseAccountList {

    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class In {
        private String representationAcno;
    }

    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Out {
        private String representationAcno;
        private List<Grid01> grid01;
    }

    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Grid01 {

        private String acno;
        private String firstDivisionCode;
    }

}
