-- =====================================================================
-- CATEGORIES TABLE
-- =====================================================================
CREATE TABLE IF NOT EXISTS categories (
                                          category_id             BIGINT NOT NULL PRIMARY KEY,
                                          category_name           VARCHAR(15) NOT NULL,
                                          description             VARCHAR(500),
                                          created_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                          updated_at              TIMESTAMP
);

-- =====================================================================
-- CUSTOMERS TABLE
-- =====================================================================
CREATE SEQUENCE IF NOT EXISTS customers_seq START WITH 1000 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS customers (
                                         customer_id      BIGINT PRIMARY KEY DEFAULT nextval('customers_seq'),
                                         company_name     VARCHAR(40) NOT NULL,
                                         contact_name     VARCHAR(30),
                                         contact_title    VARCHAR(30),
                                         address          VARCHAR(60),
                                         city             VARCHAR(15),
                                         region           VARCHAR(15),
                                         postal_code      VARCHAR(10),
                                         country          VARCHAR(15),
                                         phone            VARCHAR(24),
                                         fax              VARCHAR(24),
                                         username         VARCHAR(255) NOT NULL,
                                         created_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         updated_at       TIMESTAMP,

                                         CONSTRAINT uq_customers_username
                                             UNIQUE (username)
);

-- =====================================================================
-- EMPLOYEES TABLE
-- =====================================================================
CREATE SEQUENCE IF NOT EXISTS employees_seq START WITH 1000 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS employees (
                                         employee_id              BIGINT PRIMARY KEY DEFAULT nextval('employees_seq'),
                                         last_name                VARCHAR(20) NOT NULL,
                                         first_name               VARCHAR(10) NOT NULL,
                                         title                    VARCHAR(30),
                                         title_of_courtesy        VARCHAR(25),
                                         birth_date               DATE,
                                         salary                   DECIMAL(10, 2),
                                         reports_to               BIGINT,
                                         created_at               TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         updated_at               TIMESTAMP,

                                         FOREIGN KEY (reports_to) REFERENCES employees

);

-- =====================================================================
-- EMPLOYEE_DETAILS TABLE
-- =====================================================================
CREATE TABLE IF NOT EXISTS employee_details (
                                                id           BIGINT PRIMARY KEY,
                                                hire_date    DATE,
                                                address      VARCHAR(60),
                                                city         VARCHAR(15),
                                                region       VARCHAR(15),
                                                postal_code  VARCHAR(10),
                                                country      VARCHAR(15),
                                                home_phone   VARCHAR(24),
                                                extension    VARCHAR(4),
                                                notes        VARCHAR(500),
                                                created_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                                updated_at              TIMESTAMP,

                                                CONSTRAINT fk_employee_details_employee
                                                    FOREIGN KEY (id) REFERENCES employees(employee_id)

);

-- =====================================================================
-- SUPPLIERS TABLE
-- =====================================================================
CREATE TABLE IF NOT EXISTS suppliers (
                                         supplier_id             BIGINT NOT NULL PRIMARY KEY,
                                         company_name            VARCHAR(40) NOT NULL,
                                         contact_name            VARCHAR(30),
                                         contact_title           VARCHAR(30),
                                         address                 VARCHAR(255),
                                         city                    VARCHAR(15),
                                         region                  VARCHAR(15),
                                         postal_code             VARCHAR(10),
                                         country                 VARCHAR(15),
                                         phone                   VARCHAR(24),
                                         fax                     VARCHAR(24),
                                         homepage                VARCHAR(500),
                                         created_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         updated_at              TIMESTAMP
);

-- =====================================================================
-- PRODUCTS TABLE
-- =====================================================================
CREATE SEQUENCE IF NOT EXISTS products_seq START WITH 1000 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS products (
                                        product_id              BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('products_seq'),
                                        product_name            VARCHAR(40) NOT NULL,
                                        supplier_id             BIGINT,
                                        category_id             BIGINT,
                                        sku                     VARCHAR(10) NOT NULL,
                                        barcode                 VARCHAR(15) NOT NULL,
                                        quantity_per_unit       VARCHAR(20),
                                        unit_price              DECIMAL(10, 2),
                                        units_in_stock          BIGINT,
                                        units_on_order          BIGINT,
                                        reorder_level           BIGINT,
                                        discontinued            BOOLEAN NOT NULL,
                                        created_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                        updated_at              TIMESTAMP,

                                        FOREIGN KEY (category_id) REFERENCES categories,
                                        FOREIGN KEY (supplier_id) REFERENCES suppliers

);

-- =====================================================================
-- REGION TABLE
-- =====================================================================
CREATE TABLE IF NOT EXISTS region (
                                        region_id               BIGINT NOT NULL PRIMARY KEY,
                                        region_description      VARCHAR(20) NOT NULL,
                                        created_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                        updated_at              TIMESTAMP
);

-- =====================================================================
-- ORDERS TABLE
-- =====================================================================
CREATE SEQUENCE IF NOT EXISTS orders_seq START WITH 1000 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS orders (
                                      order_id                BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('orders_seq'),
                                      customer_id             BIGINT NOT NULL,
                                      employee_id             BIGINT,
                                      order_status            VARCHAR(50) NOT NULL CHECK (
                                                                order_status IN ('CREATED', 'APPROVED', 'PROCESSING', 'CANCELLED', 'SHIPPED', 'DELIVERED', 'REFUNDED')
                                                              ),
                                      order_date              TIMESTAMP,
                                      required_date           TIMESTAMP,
                                      shipped_date            TIMESTAMP,
                                      freight                 BIGINT,
                                      ship_name               VARCHAR(40),
                                      ship_address            VARCHAR(60),
                                      ship_city               VARCHAR(20),
                                      ship_region             VARCHAR(20),
                                      ship_postal_code        VARCHAR(10),
                                      ship_country            VARCHAR(20),
                                      created_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      updated_at              TIMESTAMP,


                                      FOREIGN KEY (customer_id) REFERENCES customers,
                                      FOREIGN KEY (employee_id) REFERENCES employees
);

-- =====================================================================
-- ORDER_DETAILS TABLE
-- =====================================================================
CREATE TABLE IF NOT EXISTS order_details (
                                      order_id                BIGINT NOT NULL,
                                      product_id              BIGINT NOT NULL,
                                      total                   DECIMAL(10, 2),
                                      quantity                INT,
                                      discount                DECIMAL(10, 2),
                                      created_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      updated_at              TIMESTAMP,


                                      PRIMARY KEY (order_id, product_id),
                                      FOREIGN KEY (order_id) REFERENCES orders,
                                      FOREIGN KEY (product_id) REFERENCES products
);


-- =====================================================================
-- TERRITORIES TABLE
-- =====================================================================
CREATE TABLE IF NOT EXISTS territories (
                                             territory_id            BIGINT NOT NULL PRIMARY KEY,
                                             territory_description   VARCHAR(20) NOT NULL,
                                             region_id               BIGINT NOT NULL,
                                             created_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                             updated_at              TIMESTAMP,

                                             FOREIGN KEY (region_id) REFERENCES region

);

-- =====================================================================
-- EMPLOYEE_TERRITORIES TABLE
-- =====================================================================
CREATE TABLE IF NOT EXISTS employee_territories (
                                           employee_id             BIGINT NOT NULL,
                                           territory_id            BIGINT NOT NULL,
                                           created_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                           updated_at              TIMESTAMP,

                                           PRIMARY KEY (employee_id, territory_id),
                                           FOREIGN KEY (territory_id) REFERENCES territories,
                                           FOREIGN KEY (employee_id) REFERENCES employees

);
