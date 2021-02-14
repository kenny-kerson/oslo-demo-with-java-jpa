package com.kenny.demo.oslodemowithjavajpa.dbpostgresql.customer.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
@ToString
public class CustomerInfoM {

    @Id
    private String customerNo;
    private String customerStatus;
    private String customerName;
}
