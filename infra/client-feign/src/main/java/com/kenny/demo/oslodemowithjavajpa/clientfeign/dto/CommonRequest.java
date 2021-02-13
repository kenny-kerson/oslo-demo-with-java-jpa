package com.kenny.demo.oslodemowithjavajpa.clientfeign.dto;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommonRequest<T> {

    private String guid;
    private String cstno;
    private T dataBody;
}
