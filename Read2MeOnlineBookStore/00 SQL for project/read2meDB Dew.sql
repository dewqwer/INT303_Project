CREATE TABLE address (
    addressid     BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    addressno     VARCHAR(200) NOT NULL,
    street        VARCHAR(200) NOT NULL,
    alley         VARCHAR(200) NOT NULL,
    subdistrict   VARCHAR(200) NOT NULL,
    district      VARCHAR(200) NOT NULL,
    province      VARCHAR(200) NOT NULL,
    postcode      VARCHAR(200) NOT NULL,
    customerid    BIGINT NOT NULL
    
);
ALTER TABLE address ADD CONSTRAINT address_pk PRIMARY KEY ( addressid );

CREATE TABLE book (
    isbn              VARCHAR(30) NOT NULL ,
    title             VARCHAR(200) NOT NULL UNIQUE,
    author            VARCHAR(200) NOT NULL,
    description       VARCHAR(2000) NOT NULL,
    publisher         VARCHAR(200) NOT NULL,
    category          VARCHAR(200) NOT NULL,
    unitprice         FLOAT NOT NULL,
    salegroup         VARCHAR(200)
);
ALTER TABLE book ADD CONSTRAINT book_pk PRIMARY KEY ( isbn );

CREATE TABLE customer (
    customerid   BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    firstname    VARCHAR(200) NOT NULL,
    lastname     VARCHAR(200) NOT NULL,
    phoneno      VARCHAR(200) NOT NULL,
    email        VARCHAR(200) NOT NULL UNIQUE,
    password     VARCHAR(200) NOT NULL,

--Don't add column in real project!--
    realPassword VARCHAR(200) 
----------------------------------------------------------------------------------------------   
);
ALTER TABLE customer ADD CONSTRAINT customer_pk PRIMARY KEY ( customerid );

CREATE TABLE lineitem (
    quantity             INTEGER NOT NULL,
    unitprice            FLOAT NOT NULL,
    orderid              BIGINT NOT NULL ,
    isbn                 VARCHAR(30) NOT NULL
);
ALTER TABLE lineitem ADD CONSTRAINT lineitem_pk PRIMARY KEY ( orderid , isbn );

CREATE TABLE "ORDERS" (
    orderid       BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    ordereddate   DATE NOT NULL,
    orderstatus   VARCHAR(200) NOT NULL,
    customerid    BIGINT NOT NULL 
);
ALTER TABLE "ORDERS" ADD CONSTRAINT order_pk PRIMARY KEY ( orderid );

CREATE TABLE payment (
    paymentid    BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    "method"     VARCHAR(200) NOT NULL,
    totalprice   FLOAT NOT NULL,
    orderid      BIGINT NOT NULL UNIQUE
);
ALTER TABLE payment ADD CONSTRAINT payment_pk PRIMARY KEY ( paymentid );


--different from read2meDB Ploy.sql-----------------------------------------------------------------------
CREATE TABLE productreview (
    reviewid    BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    comment     VARCHAR(200),
    rating      INTEGER NOT NULL,
    isbn        VARCHAR(30) NOT NULL,
    customerid    BIGINT NOT NULL
);
ALTER TABLE productreview ADD CONSTRAINT productreview_pk PRIMARY KEY ( reviewid );
------------------------------------------------------------------------------------------------------

CREATE TABLE shipping (
    shippingid     BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    shippingcost   FLOAT NOT NULL,
    shippingdate   DATE NOT NULL,
    status         VARCHAR(200) NOT NULL,
    orderid        BIGINT NOT NULL UNIQUE ,
    addressid      BIGINT NOT NULL
);
ALTER TABLE shipping ADD CONSTRAINT shipping_pk PRIMARY KEY ( shippingid );

ALTER TABLE address
    ADD CONSTRAINT address_customer_fk FOREIGN KEY ( customerid )
        REFERENCES customer ( customerid );

ALTER TABLE lineitem
    ADD CONSTRAINT lineitem_book_fk FOREIGN KEY ( isbn )
        REFERENCES book ( isbn );

ALTER TABLE lineitem
    ADD CONSTRAINT lineitem_order_fk FOREIGN KEY ( orderid )
        REFERENCES "ORDERS" ( orderid );

ALTER TABLE "ORDERS"
    ADD CONSTRAINT order_customer_fk FOREIGN KEY ( customerid )
        REFERENCES customer ( customerid );

ALTER TABLE payment
    ADD CONSTRAINT payment_order_fk FOREIGN KEY ( orderid )
        REFERENCES "ORDERS" ( orderid );

--different from read2meDB Ploy.sql-----------------------------------------------------------------------
ALTER TABLE productreview
    ADD CONSTRAINT productreview_book_fk FOREIGN KEY ( isbn )
        REFERENCES book ( isbn );

ALTER TABLE productreview
    ADD CONSTRAINT productreview_customer_fk FOREIGN KEY ( customerid )
        REFERENCES customer ( customerid );
------------------------------------------------------------------------------------------------------

ALTER TABLE shipping
    ADD CONSTRAINT shipping_address_fk FOREIGN KEY ( addressid )
        REFERENCES address ( addressid );

ALTER TABLE shipping
    ADD CONSTRAINT shipping_order_fk FOREIGN KEY ( orderid )
        REFERENCES "ORDERS" ( orderid );



