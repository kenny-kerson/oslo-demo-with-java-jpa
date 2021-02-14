package com.kenny.demo.oslodemowithjavajpa.dbpostgresql.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@Builder
@ToString
public class DepositAccountInfoM {

    @Id
    private String accountNo;
    private String customerNo;
    private String accountStatus;
    private BigDecimal balance;
    private String goodsCode;
    private String goodsDetailCode;
}
