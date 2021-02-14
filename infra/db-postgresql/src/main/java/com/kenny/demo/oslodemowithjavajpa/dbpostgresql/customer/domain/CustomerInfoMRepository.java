package com.kenny.demo.oslodemowithjavajpa.dbpostgresql.customer.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerInfoMRepository extends JpaRepository<CustomerInfoM, String> {

    List<CustomerBaseAccount> getCustomerBaseAccountList( final String customerNo );
}
