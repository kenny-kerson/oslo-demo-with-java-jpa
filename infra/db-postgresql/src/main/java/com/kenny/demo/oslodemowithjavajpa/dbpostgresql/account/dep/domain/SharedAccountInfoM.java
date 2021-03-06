package com.kenny.demo.oslodemowithjavajpa.dbpostgresql.account.dep.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
@ToString
public class SharedAccountInfoM {

    @Id
    private String accountNo;
    private String customerNo;
    private String accountStatus;
    private BigDecimal balance;
    private String goodsCode;
    private String goodsDetailCode;
}
