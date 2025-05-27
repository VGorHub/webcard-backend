CREATE TABLE flyway_schema_history (
  installed_rank INTEGER NOT NULL,
   version VARCHAR(50),
   description VARCHAR(200) NOT NULL,
   type VARCHAR(20) NOT NULL,
   script VARCHAR(1000) NOT NULL,
   checksum INTEGER,
   installed_by VARCHAR(100) NOT NULL,
   installed_on TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
   execution_time INTEGER NOT NULL,
   success BOOLEAN NOT NULL,
   CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank)
);

CREATE TABLE app_user (
  id BIGINT NOT NULL,
   password_hash VARCHAR(255),
   role SMALLINT,
   username VARCHAR(255),
   CONSTRAINT app_user_pkey PRIMARY KEY (id)
);

CREATE INDEX flyway_schema_history_s_idx ON flyway_schema_history(success);

CREATE SEQUENCE  IF NOT EXISTS app_user_seq AS bigint START WITH 1 INCREMENT BY 50 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE SEQUENCE  IF NOT EXISTS personal_info_seq AS bigint START WITH 1 INCREMENT BY 50 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE personal_info (
  id BIGINT NOT NULL,
   CONSTRAINT personal_info_pkey PRIMARY KEY (id)
);