# LoansApplicationREST

Aplikacja udostępniająca web services.

Do skonfigurowania application.properties w celu połączenia z bazą danych.

Skrypty tworzące tabele (PostgreSQL)

CREATE SEQUENCE customer_id_seq;

CREATE TABLE customer

(

  id bigint NOT NULL default nextval('customer_id_seq'::regclass),

  name character varying(100) NOT NULL,

  surname character varying(100) NOT NULL,

  fiscal_code character varying(20) NOT NULL,

  date_of_birth timestamp without time zone NOT NULL,

  address text NOT NULL,

  phone integer NOT NULL,

  loan_type character varying(50) NOT NULL,

  total_amount text NOT NULL,

  rates integer NOT NULL,

  CONSTRAINT customer_pk PRIMARY KEY (id),

  CONSTRAINT loan_type_ck CHECK (loan_type IN ('AUTOMOTIVE', 'CUSTOMER', 'MORTGAGE'))

)


CREATE SEQUENCE file_id_seq;

CREATE TABLE file

(

  id bigint NOT NULL default nextval('file_id_seq'::regclass),

  file_name character varying(255) NOT NULL,

  path character varying(100) NOT NULL,

  customer_id integer NOT NULL,

  CONSTRAINT file_pk PRIMARY KEY (id),

  CONSTRAINT file_customer_id_fk FOREIGN KEY (customer_id) REFERENCES customer (id)

)

