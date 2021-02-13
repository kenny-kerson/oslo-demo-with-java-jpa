package com.kenny.demo.oslodemowithjavajpa.clientwebflux.dto;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor @AllArgsConstructor
public class CommonRequest<T> {

    private String guid;
    private String cstno;
    private T dataBody;
}
