CREATE TABLE MASTER_USER_ARIFUDIN (
                                      ID RAW(36) DEFAULT SYS_GUID() PRIMARY KEY,
                                      FULL_NAME VARCHAR(255) NOT NULL
);

CREATE TABLE MASTER_ACCOUNT_ARIFUDIN (
                                         ID RAW(36) DEFAULT SYS_GUID() PRIMARY KEY,
                                         USER_ID RAW(36) NOT NULL,
                                         BALANCE DOUBLE PRECISION NOT NULL,
                                         CONSTRAINT FK_USER_ACCOUNT FOREIGN KEY (USER_ID) REFERENCES MASTER_USER_ARIFUDIN(ID)
);
