CREATE TABLE MASTER_USER (
                             ID UUID PRIMARY KEY,
                             FULL_NAME VARCHAR(255) NOT NULL
);

CREATE TABLE MASTER_ACCOUNT (
                                ID UUID PRIMARY KEY,
                                USER_ID UUID NOT NULL,
                                BALANCE DOUBLE PRECISION NOT NULL,
                                CONSTRAINT FK_USER_ACCOUNT FOREIGN KEY (USER_ID) REFERENCES MASTER_USER(ID)
);