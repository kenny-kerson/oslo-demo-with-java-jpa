package com.kenny.demo.oslodemowithjavajpa.dbpostgresql.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@Builder
@ToString
public class LoanAccountInfoM {

    @Id
    private String accountNo;
    private String customerNo;
    private String accountStatus;
    private BigDecimal balance;
    private String goodsCode;
    private String goodsDetailCode;
}
