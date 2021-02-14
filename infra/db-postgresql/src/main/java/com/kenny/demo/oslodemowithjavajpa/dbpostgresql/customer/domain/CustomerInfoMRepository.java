package com.kenny.demo.oslodemowithjavajpa.dbpostgresql.customer.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerInfoMRepository extends JpaRepository<CustomerInfoM, String> {

    @Query(
        nativeQuery = true,
        value = "SELECT /* 고객별 계좌기본목록조회 */" +
                "       account_no as account_no" +
                "     , '01'       as first_division_code" +
                "  FROM deposit_account_info_m" +
                " WHERE 1=1" +
                "   AND customer_no = :customer_no" +
                " UNION ALL" +
                "SELECT " +
                "       account_no as account_no" +
                "     , '01'       as first_division_code" +
                "  FROM loan_account_info_m" +
                " WHERE 1=1" +
                "   AND customer_no = :customer_no" +
                " UNION ALL" +
                "SELECT " +
                "       account_no as account_no" +
                "     , '01'       as first_division_code" +
                "  FROM shared_account_info_m" +
                " WHERE 1=1" +
                "   AND customer_no = :customer_no"
    )
    List<CustomerBaseAccount> getCustomerBaseAccountList( @Param("customer_no") String customerNo );
}
