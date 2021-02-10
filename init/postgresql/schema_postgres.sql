---------------------------------------------------------------------
-- 1. API로그
---------------------------------------------------------------------
CREATE TABLE API_LOG(
    CSTNO varchar(16) NOT NULL,
    TX_DT varchar(8) NOT NULL,
    TX_SEQNO bigserial NOT NULL,
    SYSTEM_TIMESTAMP timestamp NOT NULL default current_timestamp,
    TX_TIME varchar(9),
    PROCS_STCD varchar(2),
    METHOD_NAME varchar(100),
    INPUT json,
    OUTPUT json,
    HTTP_HEADER_CONTENT varchar(1000)
);

ALTER TABLE API_LOG ADD PRIMARY KEY (CSTNO, TX_DT, TX_SEQNO);


---------------------------------------------------------------------
-- 2. 수신계좌기본
---------------------------------------------------------------------
CREATE TABLE DEPOSIT_ACCOUNT_INFO_M(
    ACNO varchar(16) NOT NULL,
    SYSTEM_TIMESTAMP timestamp NOT NULL default current_timestamp,
    CSTNO varchar(16),
    ACCO_STCD varchar(2)
);

ALTER TABLE DEPOSIT_ACCOUNT_INFO_M ADD PRIMARY KEY (ACNO);


---------------------------------------------------------------------
-- 3. 여신계좌기본
---------------------------------------------------------------------
CREATE TABLE LOAN_ACCOUNT_INFO_M(
       ACNO varchar(16) NOT NULL,
       SYSTEM_TIMESTAMP timestamp NOT NULL default current_timestamp,
       CSTNO varchar(16),
       ACCO_STCD varchar(2)
);

ALTER TABLE LOAN_ACCOUNT_INFO_M ADD PRIMARY KEY (ACNO);

---------------------------------------------------------------------
-- 4. 모임원계좌기본
---------------------------------------------------------------------
CREATE TABLE SHARED_ACCOUNT_INFO_M(
    ACNO varchar(16) NOT NULL,
    SYSTEM_TIMESTAMP timestamp NOT NULL default current_timestamp,
    CSTNO varchar(16),
    ACCO_STCD varchar(2)
);

ALTER TABLE SHARED_ACCOUNT_INFO_M ADD PRIMARY KEY (ACNO);