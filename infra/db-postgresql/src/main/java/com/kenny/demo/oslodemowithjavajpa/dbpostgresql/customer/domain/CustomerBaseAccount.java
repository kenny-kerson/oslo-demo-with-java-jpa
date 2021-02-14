package com.kenny.demo.oslodemowithjavajpa.dbpostgresql.customer.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class CustomerBaseAccount {

    private final String accountNo;
    private final String firstDivisionCode;
}
