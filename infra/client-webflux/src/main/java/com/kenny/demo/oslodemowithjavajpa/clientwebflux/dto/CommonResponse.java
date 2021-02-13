package com.kenny.demo.oslodemowithjavajpa.clientwebflux.dto;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {

    private String status;
    private String errorCode;
    private String errorContent;
    private String errorDetailContent;
    private String guid;
    private String cstno;
    private T dataBody;
}