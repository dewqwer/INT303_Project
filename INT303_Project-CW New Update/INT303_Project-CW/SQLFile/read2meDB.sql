DROP TABLE productreview;
DROP TABLE shipping;
DROP TABLE lineitem;
DROP TABLE payment;
DROP TABLE orders;
DROP TABLE address;
DROP TABLE book;
DROP TABLE customer;


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
    isbn              VARCHAR(30) NOT NULL,
    title             VARCHAR(100) NOT NULL,
    author            VARCHAR(100) NOT NULL,
    description       VARCHAR(2000) NOT NULL,
    publisher         VARCHAR(100) NOT NULL,
    category          VARCHAR(100) NOT NULL,
    unitprice         FLOAT NOT NULL,
    salegroup         VARCHAR(100)
);

ALTER TABLE book ADD CONSTRAINT book_pk PRIMARY KEY ( isbn );

CREATE TABLE customer (
    customerid   BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    firstname    VARCHAR(200) NOT NULL,
    lastname     VARCHAR(200) NOT NULL,
    phoneno      VARCHAR(200) NOT NULL,
    email        VARCHAR(200) NOT NULL UNIQUE,
    password     VARCHAR(200) NOT NULL

);

ALTER TABLE customer ADD CONSTRAINT customer_pk PRIMARY KEY ( customerid );

CREATE TABLE lineitem(
    quantity             INTEGER NOT NULL,
    unitprice            FLOAT NOT NULL,
    orderid              BIGINT NOT NULL,
    isbn                 VARCHAR(30) NOT NULL
);

ALTER TABLE lineitem ADD CONSTRAINT lineitem_pk PRIMARY KEY ( orderid , isbn );

CREATE TABLE orders (
    orderid       BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    ordereddate   DATE NOT NULL,
   ordersatus   VARCHAR(200) NOT NULL,
    customerid    BIGINT NOT NULL 
);

ALTER TABLE orders ADD CONSTRAINT order_pk PRIMARY KEY ( orderid );

CREATE TABLE payment (
    paymentid       BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    paymentmethod   VARCHAR(200) NOT NULL,
    totalprice      FLOAT NOT NULL,
    orderid         BIGINT NOT NULL UNIQUE
);
ALTER TABLE payment ADD CONSTRAINT payment_pk PRIMARY KEY ( paymentid );

CREATE TABLE shipping (
    shippingid     BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    shippingcost   FLOAT NOT NULL,
    shippingdate   DATE NOT NULL,
    status         VARCHAR(200) NOT NULL,
    orderid        BIGINT NOT NULL UNIQUE ,
    addressid      BIGINT NOT NULL
);
ALTER TABLE shipping ADD CONSTRAINT shipping_pk PRIMARY KEY ( shippingid );

CREATE TABLE review (
    reviewid    BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    comment     VARCHAR(200),
    rating      INTEGER NOT NULL,
    isbn        VARCHAR(30) NOT NULL,
    customerid    BIGINT NOT NULL
);
ALTER TABLE review ADD CONSTRAINT review_pk PRIMARY KEY ( reviewid );

ALTER TABLE address
    ADD CONSTRAINT address_customer_fk FOREIGN KEY ( customerid )
        REFERENCES customer ( customerid );

ALTER TABLE lineitem
    ADD CONSTRAINT lineitem_book_fk FOREIGN KEY ( isbn )
        REFERENCES book ( isbn );

ALTER TABLE lineitem
    ADD CONSTRAINT lineitem_order_fk FOREIGN KEY ( orderid )
        REFERENCES orders ( orderid );

ALTER TABLE orders
    ADD CONSTRAINT order_customer_fk FOREIGN KEY ( customerid )
        REFERENCES customer ( customerid );

ALTER TABLE payment
    ADD CONSTRAINT payment_order_fk FOREIGN KEY ( orderid )
        REFERENCES orders ( orderid );

ALTER TABLE shipping
    ADD CONSTRAINT shipping_address_fk FOREIGN KEY ( addressid )
        REFERENCES address ( addressid );

ALTER TABLE shipping
    ADD CONSTRAINT shipping_order_fk FOREIGN KEY ( orderid )
        REFERENCES orders ( orderid );

ALTER TABLE review
    ADD CONSTRAINT review_book_fk FOREIGN KEY ( isbn )
        REFERENCES book ( isbn );

ALTER TABLE review
    ADD CONSTRAINT review_customer_fk FOREIGN KEY ( customerid )
        REFERENCES customer ( customerid );