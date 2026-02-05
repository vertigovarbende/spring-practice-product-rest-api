CREATE SEQUENCE IF NOT EXISTS users_seq START WITH 1000 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS users (
                                     id                  BIGINT PRIMARY KEY DEFAULT nextval('users_seq'),
                                     username            VARCHAR(255) NOT NULL,
                                     password            VARCHAR(255) NOT NULL,
                                     email               VARCHAR(255),
                                     first_name          VARCHAR(255),
                                     last_name           VARCHAR(255),
                                     disabled            BOOLEAN,
                                     account_expired     BOOLEAN,
                                     account_locked      BOOLEAN,
                                     credentials_expired BOOLEAN,
                                     created_at          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                     updated_at          TIMESTAMP,

                                     CONSTRAINT uq_users_username
                                         UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS role (
    id   BIGINT GENERATED ALWAYS AS IDENTITY CONSTRAINT pk_role_id PRIMARY KEY,
    name VARCHAR(50) NOT NULL CHECK (name IN ('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_CUSTOMER', 'ROLE_SALES', 'ROLE_HR', 'ROLE_WAREHOUSE', 'ROLE_EMPLOYEE')),
    description TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS user_roles (
                                          user_id         BIGINT NOT NULL,
                                          role_id         BIGINT NOT NULL,

                                          CONSTRAINT pk_user_roles
                                              PRIMARY KEY (user_id, role_id)
);