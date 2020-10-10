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

ALTER TABLE API_LOG ADD PRIMARY KEY (CSTNO, TX_DT, TX_SEQNO)