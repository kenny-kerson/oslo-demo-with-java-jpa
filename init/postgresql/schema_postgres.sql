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
    GOODS_CODE varchar(10),
    GOODS_DETAIL_CODE varchar(3)
);

ALTER TABLE DEPOSIT_ACCOUNT_INFO_M ADD PRIMARY KEY (ACCOUNT_NO);

INSERT INTO DEPOSIT_ACCOUNT_INFO_M (ACCOUNT_NO, SYSTEM_TIMESTAMP, CUSTOMER_NO, ACCOUNT_STATUS, BALANCE, GOODS_CODE, GOODS_DETAIL_CODE )
VALUES ('3333010000001', current_timestamp, '0000011000000123', '01', 20000, '1003330001', '001');

INSERT INTO DEPOSIT_ACCOUNT_INFO_M (ACCOUNT_NO, SYSTEM_TIMESTAMP, CUSTOMER_NO, ACCOUNT_STATUS, BALANCE, GOODS_CODE, GOODS_DETAIL_CODE )
VALUES ('3333010000002', current_timestamp, '0000011000000123', '01', 40000, '1003330001', '001');

INSERT INTO DEPOSIT_ACCOUNT_INFO_M (ACCOUNT_NO, SYSTEM_TIMESTAMP, CUSTOMER_NO, ACCOUNT_STATUS, BALANCE, GOODS_CODE, GOODS_DETAIL_CODE )
VALUES ('3333010000003', current_timestamp, '0000011000000123', '01', 60000, '1003330001', '001');


---------------------------------------------------------------------
-- 3. 여신계좌기본
---------------------------------------------------------------------
CREATE TABLE LOAN_ACCOUNT_INFO_M(
    ACCOUNT_NO varchar(16) NOT NULL,
    SYSTEM_TIMESTAMP timestamp NOT NULL default current_timestamp,
    CUSTOMER_NO varchar(16),
    ACCOUNT_STATUS varchar(2),
    BALANCE numeric,
    GOODS_CODE varchar(10),
    GOODS_DETAIL_CODE varchar(3)
);

ALTER TABLE LOAN_ACCOUNT_INFO_M ADD PRIMARY KEY (ACCOUNT_NO);

INSERT INTO LOAN_ACCOUNT_INFO_M (ACCOUNT_NO, SYSTEM_TIMESTAMP, CUSTOMER_NO, ACCOUNT_STATUS, BALANCE, GOODS_CODE, GOODS_DETAIL_CODE )
VALUES ('3651010000001', current_timestamp, '0000011000000123', '01', 30000000, '1003650001', '001');

---------------------------------------------------------------------
-- 4. 모임원계좌기본
---------------------------------------------------------------------
CREATE TABLE SHARED_ACCOUNT_INFO_M(
      ACCOUNT_NO varchar(16) NOT NULL,
      SYSTEM_TIMESTAMP timestamp NOT NULL default current_timestamp,
      CUSTOMER_NO varchar(16),
      ACCOUNT_STATUS varchar(2),
      BALANCE numeric,
      GOODS_CODE varchar(10),
      GOODS_DETAIL_CODE varchar(3)
);

ALTER TABLE SHARED_ACCOUNT_INFO_M ADD PRIMARY KEY (ACCOUNT_NO);

INSERT INTO SHARED_ACCOUNT_INFO_M (ACCOUNT_NO, SYSTEM_TIMESTAMP, CUSTOMER_NO, ACCOUNT_STATUS, BALANCE, GOODS_CODE, GOODS_DETAIL_CODE )
VALUES ('3333020000001', current_timestamp, '0000011000000123', '01', 1000000, '1003330001', '001');


---------------------------------------------------------------------
-- 5. 고객기본
---------------------------------------------------------------------
CREATE TABLE CUSTOMER_INFO_M(
      CUSTOMER_NO varchar(16) NOT NULL,
      SYSTEM_TIMESTAMP timestamp NOT NULL default current_timestamp,
      CUSTOMER_STATUS varchar(2),
      CUSTOMER_NAME varchar(100)
);

ALTER TABLE CUSTOMER_INFO_M ADD PRIMARY KEY (CUSTOMER_NO);

INSERT INTO CUSTOMER_INFO_M (CUSTOMER_NO, SYSTEM_TIMESTAMP, CUSTOMER_STATUS, CUSTOMER_NAME) VALUES ('0000011000000123', current_timestamp, '00', '고길동');