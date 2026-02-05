-- =====================================================================
-- INSERT SAMPLE to 'role' table
-- =====================================================================

INSERT INTO role (name, description, created_at, updated_at) VALUES
                                                                 ('ROLE_ADMIN', 'Full system access, configuration and user management', CURRENT_TIMESTAMP, DEFAULT),
                                                                 ('ROLE_MANAGER', 'Manages teams, oversees operations and approves processes', CURRENT_TIMESTAMP, DEFAULT),
                                                                 ('ROLE_EMPLOYEE', 'Accesses own work-related data', CURRENT_TIMESTAMP, DEFAULT),
                                                                 ('ROLE_CUSTOMER', 'Uses customer-facing features and manages own account',CURRENT_TIMESTAMP, DEFAULT),
                                                                 ('ROLE_SALES', 'Manages sales activities, orders and customer relations',CURRENT_TIMESTAMP, DEFAULT),
                                                                 ('ROLE_HR', 'Manages employee records, payroll and HR processes',CURRENT_TIMESTAMP, DEFAULT),
                                                                 ('ROLE_WAREHOUSE', 'Handles inventory, stock operations and shipments',CURRENT_TIMESTAMP, DEFAULT);



-- =====================================================================
-- INSERT 'ROLE_CUSTOMER' role for the users
-- =====================================================================
INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'AROUT'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'BERGS'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'BLAUS'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'BLONP'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'BOLID'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'BONAP'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'BOTTM'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'BSBEV'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'CACTU'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'CENTC'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'CHOPS'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'COMMI'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'CONSH'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'DRACD'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'DUMON'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'EASTC'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'ERNSH'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'FAMIA'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'FISSA'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'FOLIG'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'FOLKO'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'FRANK'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'FRANR'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'FRANS'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'FURIB'), (SELECT id FROM role WHERE name = 'ROLE_CUSTOMER'));


-- =====================================================================
-- INSERT 'ROLE_ADMIN' role for the user
-- =====================================================================
INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'johndoe'), (SELECT id FROM role WHERE name = 'ROLE_ADMIN'));


-- =====================================================================
-- INSERT 'ROLE_MANAGER' role for the users
-- =====================================================================
INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'smithjohn'), (SELECT id FROM role WHERE name = 'ROLE_MANAGER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'robertwilliams'), (SELECT id FROM role WHERE name = 'ROLE_MANAGER'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'maryjohnson'), (SELECT id FROM role WHERE name = 'ROLE_MANAGER'));


-- =====================================================================
-- INSERT 'ROLE_SALES' role for the users
-- =====================================================================
INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'smithjohn'), (SELECT id FROM role WHERE name = 'ROLE_SALES'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'patriciabrown'), (SELECT id FROM role WHERE name = 'ROLE_SALES'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'lindagarcia'), (SELECT id FROM role WHERE name = 'ROLE_SALES'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'michealjones'), (SELECT id FROM role WHERE name = 'ROLE_SALES'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'jamesmiller'), (SELECT id FROM role WHERE name = 'ROLE_SALES'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'elizabethwhite'), (SELECT id FROM role WHERE name = 'ROLE_SALES'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'kevinharris'), (SELECT id FROM role WHERE name = 'ROLE_SALES'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'jenniferdavis'), (SELECT id FROM role WHERE name = 'ROLE_SALES'));


-- =====================================================================
-- INSERT 'ROLE_HR' role for the users
-- =====================================================================
INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'robertwilliams'), (SELECT id FROM role WHERE name = 'ROLE_HR'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'susanclark'), (SELECT id FROM role WHERE name = 'ROLE_HR'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'lisamartinez'), (SELECT id FROM role WHERE name = 'ROLE_HR'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'davidrodriguez'), (SELECT id FROM role WHERE name = 'ROLE_HR'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'danielanderson'), (SELECT id FROM role WHERE name = 'ROLE_HR'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'barbaramartin'), (SELECT id FROM role WHERE name = 'ROLE_HR'));


-- =====================================================================
-- INSERT 'ROLE_WAREHOUSE' role for the users
-- =====================================================================
INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'maryjohnson'), (SELECT id FROM role WHERE name = 'ROLE_WAREHOUSE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'taylormichelle'), (SELECT id FROM role WHERE name = 'ROLE_WAREHOUSE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'josephthomas'), (SELECT id FROM role WHERE name = 'ROLE_WAREHOUSE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'sarahmoore'), (SELECT id FROM role WHERE name = 'ROLE_WAREHOUSE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'christopherjackson'), (SELECT id FROM role WHERE name = 'ROLE_WAREHOUSE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'richardthompson'), (SELECT id FROM role WHERE name = 'ROLE_WAREHOUSE'));

-- =====================================================================
-- INSERT 'ROLE_EMPLOYEE' role for the users
-- =====================================================================

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'smithjohn'), (SELECT id FROM role WHERE name = 'ROLE_EMPLOYEE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'robertwilliams'), (SELECT id FROM role WHERE name = 'ROLE_EMPLOYEE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'maryjohnson'), (SELECT id FROM role WHERE name = 'ROLE_EMPLOYEE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'patriciabrown'), (SELECT id FROM role WHERE name = 'ROLE_EMPLOYEE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'lindagarcia'), (SELECT id FROM role WHERE name = 'ROLE_EMPLOYEE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'michealjones'), (SELECT id FROM role WHERE name = 'ROLE_EMPLOYEE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'jamesmiller'), (SELECT id FROM role WHERE name = 'ROLE_EMPLOYEE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'elizabethwhite'), (SELECT id FROM role WHERE name = 'ROLE_EMPLOYEE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'kevinharris'), (SELECT id FROM role WHERE name = 'ROLE_EMPLOYEE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'jenniferdavis'), (SELECT id FROM role WHERE name = 'ROLE_EMPLOYEE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'susanclark'), (SELECT id FROM role WHERE name = 'ROLE_EMPLOYEE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'lisamartinez'), (SELECT id FROM role WHERE name = 'ROLE_EMPLOYEE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'davidrodriguez'), (SELECT id FROM role WHERE name = 'ROLE_EMPLOYEE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'danielanderson'), (SELECT id FROM role WHERE name = 'ROLE_EMPLOYEE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'barbaramartin'), (SELECT id FROM role WHERE name = 'ROLE_EMPLOYEE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'taylormichelle'), (SELECT id FROM role WHERE name = 'ROLE_EMPLOYEE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'josephthomas'), (SELECT id FROM role WHERE name = 'ROLE_EMPLOYEE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'sarahmoore'), (SELECT id FROM role WHERE name = 'ROLE_EMPLOYEE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'christopherjackson'), (SELECT id FROM role WHERE name = 'ROLE_EMPLOYEE'));

INSERT INTO user_roles(user_id, role_id) VALUES
    ((SELECT id FROM users WHERE username = 'richardthompson'), (SELECT id FROM role WHERE name = 'ROLE_EMPLOYEE'));

