---------------------------------------------------------------------
-- 1. API로그
---------------------------------------------------------------------
CREATE TABLE API_LOG(
    CUSTOMER_NO varchar(16) NOT NULL,
    TRANSACTION_DT varchar(8) NOT NULL,
    TRANSACTION_SEQNO bigserial NOT NULL,
    SYSTEM_TIMESTAMP timestamp NOT NULL default current_timestamp,
    TRANSACTION_TIME varchar(9),
    PROCESS_STATUS varchar(2),
    METHOD_NAME varchar(100),
    INPUT json,
    OUTPUT json,
    HTTP_HEADER_CONTENT varchar(1000)
);

ALTER TABLE API_LOG ADD PRIMARY KEY (CUSTOMER_NO, TRANSACTION_DT, TRANSACTION_SEQNO);


---------------------------------------------------------------------
-- 2. 수신계좌기본
---------------------------------------------------------------------
CREATE TABLE DEPOSIT_ACCOUNT_INFO_M(
    ACCOUNT_NO varchar(16) NOT NULL,
    SYSTEM_TIMESTAMP timestamp NOT NULL default current_timestamp,
    CUSTOMER_NO varchar(16),
    ACCOUNT_STATUS varchar(2),
    BALANCE numeric,
    GOODS_CODE varchar(2),
    GOODS_DETAIL_CODE varchar(2)
);

ALTER TABLE DEPOSIT_ACCOUNT_INFO_M ADD PRIMARY KEY (ACCOUNT_NO);


---------------------------------------------------------------------
-- 3. 여신계좌기본
---------------------------------------------------------------------
CREATE TABLE LOAN_ACCOUNT_INFO_M(
    ACCOUNT_NO varchar(16) NOT NULL,
    SYSTEM_TIMESTAMP timestamp NOT NULL default current_timestamp,
    CUSTOMER_NO varchar(16),
    ACCOUNT_STATUS varchar(2),
    BALANCE numeric,
    GOODS_CODE varchar(2),
    GOODS_DETAIL_CODE varchar(2)
);

ALTER TABLE LOAN_ACCOUNT_INFO_M ADD PRIMARY KEY (ACCOUNT_NO);

---------------------------------------------------------------------
-- 4. 모임원계좌기본
---------------------------------------------------------------------
CREATE TABLE SHARED_ACCOUNT_INFO_M(
      ACCOUNT_NO varchar(16) NOT NULL,
      SYSTEM_TIMESTAMP timestamp NOT NULL default current_timestamp,
      CUSTOMER_NO varchar(16),
      ACCOUNT_STATUS varchar(2),
      BALANCE numeric,
      GOODS_CODE varchar(2),
      GOODS_DETAIL_CODE varchar(2)
);

ALTER TABLE SHARED_ACCOUNT_INFO_M ADD PRIMARY KEY (ACCOUNT_NO);