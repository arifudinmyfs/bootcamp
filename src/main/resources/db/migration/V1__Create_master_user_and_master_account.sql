CREATE TABLE BACKOFFICE.MASTER_USER_ARIFUDIN (
                             ID UUID PRIMARY KEY,
                             FULL_NAME VARCHAR(255) NOT NULL
);

CREATE TABLE BACKOFFICE.MASTER_ACCOUNT_ARIFUDIN (
                                ID UUID PRIMARY KEY,
                                USER_ID UUID NOT NULL,
                                BALANCE DOUBLE PRECISION NOT NULL,
                                CONSTRAINT FK_USER_ACCOUNT FOREIGN KEY (USER_ID) REFERENCES MASTER_USER(ID)
);