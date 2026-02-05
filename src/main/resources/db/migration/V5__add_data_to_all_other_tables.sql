-- =====================================================================
-- CATEGORIES DATA
-- =====================================================================
INSERT INTO categories (category_id,category_name,description, created_at, updated_at)
VALUES (1,'Beverages','Soft drinks, coffees, teas, beers, and ales', CURRENT_TIMESTAMP,DEFAULT);

INSERT INTO categories (category_id,category_name,description,created_at, updated_at)
VALUES (2,'Condiments','Sweet and savory sauces, relishes, spreads, and seasonings', CURRENT_TIMESTAMP,DEFAULT);

INSERT INTO categories (category_id,category_name,description, created_at, updated_at)
VALUES (3,'Confections','Desserts, candies, and sweet breads', CURRENT_TIMESTAMP,DEFAULT);

INSERT INTO categories (category_id,category_name,description, created_at, updated_at)
VALUES (4,'Dairy Products','Cheeses', CURRENT_TIMESTAMP,DEFAULT);

INSERT INTO categories (category_id,category_name,description, created_at, updated_at)
VALUES (5,'Grains/Cereals','Breads, crackers, pasta, and cereal', CURRENT_TIMESTAMP,DEFAULT);

INSERT INTO categories (category_id,category_name,description, created_at, updated_at)
VALUES (6,'Meat/Poultry','Prepared meats', CURRENT_TIMESTAMP,DEFAULT);

INSERT INTO categories (category_id,category_name,description, created_at, updated_at)
VALUES (7,'Produce','Dried fruit and bean curd', CURRENT_TIMESTAMP,DEFAULT);

INSERT INTO categories (category_id,category_name,description,created_at, updated_at)
VALUES (8,'Seafood','Seaweed and fish', CURRENT_TIMESTAMP,DEFAULT);


-- =====================================================================
-- CUSTOMERS DATA
-- =====================================================================
INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('Alfreds Futterkiste', 'Maria Anders', 'Sales Representative', 'Obere Str. 57', 'Berlin', NULL, '12209',
        'Germany', '030-0074321', '030-0076545', 'customer1', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('Berglunds snabbköp', 'Christina Berglund', 'Order Administrator', 'Berguvsvägen 8', 'Luleå', NULL, 'S-958 22',
        'Sweden', '0921-12 34 65', '0921-12 34 67', 'customer2', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('Centro comercial Moctezuma', 'Francisco Chang', 'Marketing Manager', 'Sierras de Granada 9993', 'México D.F.',
        NULL, '05022', 'Mexico', '(5) 555-3392', '(5) 555-7293', 'customer3', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('Du monde entier', 'Janine Labrune', 'Owner', '67, rue des Cinquante Otages', 'Nantes', NULL, '44000', 'France',
        '40.67.88.88', '40.67.89.89', 'customer4', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('Eastern Connection', 'Elizabeth Brown', 'Sales Representative', '35 King George', 'London', NULL, 'WX3 6FW',
        'UK', '(171) 555-0297', '(171) 555-3373', 'customer5', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('Ernst Handel', 'Roland Mendel', 'Sales Manager', 'Kirchgasse 6', 'Graz', NULL, '8010', 'Austria', '7675-3425',
        '7675-3426', 'customer6', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('France restauration', 'Carine Schmitt', 'Marketing Manager', '54, rue Royale', 'Nantes', NULL, '44000',
        'France', '40.32.21.21', '40.32.21.20', 'customer7', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('Great Lakes Food Market', 'Howard Snyder', 'Marketing Manager', '2732 Baker Blvd.', 'Eugene', 'OR', '97403',
        'USA', '(503) 555-7555', NULL, 'customer8', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('HILARION-Abastos', 'Carlos Hernández', 'Sales Representative', 'Carrera 22 con Ave. Carlos Soublette #8-35',
        'San Cristóbal', 'Táchira', '5022', 'Venezuela', '(5) 555-1340', '(5) 555-1948', 'customer9', CURRENT_TIMESTAMP,
        DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('Hungry Coyote Import Store', 'Yoshi Latimer', 'Sales Representative', 'City Center Plaza 516 Main St.',
        'Elgin', 'OR', '97827', 'USA', '(503) 555-6874', '(503) 555-2376', 'customer10', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('Island Trading', 'Helen Bennett', 'Marketing Manager', 'Garden House Crowther Way', 'Cowes', 'Isle of Wight',
        'PO31 7PJ', 'UK', '(198) 555-8888', NULL, 'customer11', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('Königlich Essen', 'Philip Cramer', 'Sales Associate', 'Maubelstr. 90', 'Brandenburg', NULL, '14776', 'Germany',
        '0555-09876', NULL, 'customer12', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('La corne d''abondance', 'Daniel Tonini', 'Sales Representative', '67, avenue de l''Europe', 'Versailles', NULL,
        '78000', 'France', '30.59.84.10', '30.59.85.11', 'customer13', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('La maison d''Asie', 'Annette Roulet', 'Sales Manager', '1 rue Alsace-Lorraine', 'Toulouse', NULL, '31000',
        'France', '61.77.61.10', '61.77.61.11', 'customer14', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('Laughing Bacchus Wine Cellars', 'Yoshi Tannamuri', 'Marketing Assistant', '1900 Oak St.', 'Vancouver', 'BC',
        'V3F 2K1', 'Canada', '(604) 555-3392', '(604) 555-7293', 'customer15', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('Magazzini Alimentari Riuniti', 'Giovanni Rovelli', 'Marketing Manager', 'Via Ludovico il Moro 22', 'Bergamo',
        NULL, '24100', 'Italy', '035-640230', '035-640231', 'customer16', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('North/South', 'Simon Crowther', 'Sales Associate', 'South House 300 Queensbridge', 'London', NULL, 'SW7 1RZ',
        'UK', '(171) 555-7733', '(171) 555-2530', 'customer17', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('Paris spécialités', 'Marie Bertrand', 'Owner', '265, boulevard Charonne', 'Paris', NULL, '75012', 'France',
        '(1) 42.34.22.66', '(1) 42.34.22.77', 'customer18', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('Que Delícia', 'Bernardo Batista', 'Accounting Manager', 'Rua da Panificadora, 12', 'Rio de Janeiro', 'RJ',
        '02389-673', 'Brazil', '(21) 555-4252', '(21) 555-4545', 'customer19', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('Queen Cozinha', 'Lúcia Carvalho', 'Marketing Assistant', 'Alameda dos Canàrios, 891', 'Sao Paulo', 'SP',
        '05487-020', 'Brazil', '(11) 555-1189', NULL, 'customer20', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('QUICK-Stop', 'Horst Kloss', 'Accounting Manager', 'Taucherstraße 10', 'Cunewalde', NULL, '01307', 'Germany',
        '0372-035188', NULL, 'customer21', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('Rancho grande', 'Sergio Gutiérrez', 'Sales Representative', 'Av. del Libertador 900', 'Buenos Aires', NULL,
        '1010', 'Argentina', '(1) 123-5555', '(1) 123-5556', 'customer22', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('Rattlesnake Canyon Grocery', 'Paula Wilson', 'Assistant Sales Representative', '2817 Milton Dr.',
        'Albuquerque', 'NM', '87110', 'USA', '(505) 555-5939', '(505) 555-3620', 'customer23', CURRENT_TIMESTAMP,
        DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('Richter Supermarkt', 'Michael Holz', 'Sales Manager', 'Grenzacherweg 237', 'Genève', NULL, '1203',
        'Switzerland', '0897-034214', NULL, 'customer24', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO customers (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone,
                       fax, username, created_at, updated_at)
VALUES ('Save-a-lot Markets', 'Jose Pavarotti', 'Sales Representative', '187 Suffolk Ln.', 'Boise', 'ID', '83720',
        'USA', '(208) 555-8097', NULL, 'customer25', CURRENT_TIMESTAMP, DEFAULT);

-- =====================================================================
-- EMPLOYEES DATA
-- =====================================================================
INSERT INTO employees (last_name, first_name, title, title_of_courtesy, birth_date, salary, reports_to,
                        created_at, updated_at)
VALUES ('Smith', 'John', 'Sales Director', 'Mr', '1970-01-15', 65000, NULL, 
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO employees (last_name, first_name, title, title_of_courtesy, birth_date, salary, reports_to, created_at, updated_at)
VALUES ('Johnson', 'Mary', 'Human Resources Director', 'Mrs', '1975-03-20', 62000, NULL, 
        CURRENT_TIMESTAMP,
        DEFAULT);

INSERT INTO employees (last_name, first_name, title, title_of_courtesy, birth_date, salary, reports_to,
                       created_at, updated_at)
VALUES ('Williams', 'Robert', 'Warehouse Operations Director', 'Mr', '1972-06-10', 60000, NULL,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO employees (last_name, first_name, title, title_of_courtesy, birth_date, salary, reports_to,
                       created_at, updated_at)
VALUES ('Brown', 'Patricia', 'Senior Sales Representative', 'Ms', '1980-04-25', 45000, 1000,
        CURRENT_TIMESTAMP,
        DEFAULT);

INSERT INTO employees (last_name, first_name, title, title_of_courtesy, birth_date, salary, reports_to,
                       created_at, updated_at)
VALUES ('Jones', 'Michael', 'Regional Sales Representative', 'Mr', '1982-08-12', 42000, 1000,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO employees (last_name, first_name, title, title_of_courtesy, birth_date, salary, reports_to,
                       created_at, updated_at)
VALUES ('Garcia', 'Linda', 'Regional Sales Representative', 'Mrs', '1979-11-30', 43000, 1000,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO employees (last_name, first_name, title, title_of_courtesy, birth_date, salary, reports_to,
                       created_at, updated_at)
VALUES ('Miller', 'James', 'Junior Sales Representative', 'Mr', '1983-07-15', 41000, 1000,
        CURRENT_TIMESTAMP,
        DEFAULT);

INSERT INTO employees (last_name, first_name, title, title_of_courtesy, birth_date, salary, reports_to,
                       created_at, updated_at)
VALUES ('Davis', 'Jennifer', 'Senior HR Specialist', 'Ms', '1985-05-22', 48000, 1001,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO employees (last_name, first_name, title, title_of_courtesy, birth_date, salary, reports_to,
                       created_at, updated_at)
VALUES ('Rodriguez', 'David', 'HR Recruitment Specialist', 'Mr', '1981-09-18', 45000, 1001,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO employees (last_name, first_name, title, title_of_courtesy, birth_date, salary, reports_to,
                       created_at, updated_at)
VALUES ('Martinez', 'Lisa', 'HR Coordinator', 'Mrs', '1984-12-05', 44000, 1001, CURRENT_TIMESTAMP,
        DEFAULT);

INSERT INTO employees (last_name, first_name, title, title_of_courtesy, birth_date, salary, reports_to,
                       created_at, updated_at)
VALUES ('Anderson', 'Daniel', 'HR Assistant', 'Mr', '1986-02-28', 42000, 1001, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO employees (last_name, first_name, title, title_of_courtesy, birth_date, salary, reports_to,
                       created_at, updated_at)
VALUES ('Taylor', 'Michelle', 'Senior Warehouse Coordinator', 'Ms', '1978-10-14', 39000, 1002,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO employees (last_name, first_name, title, title_of_courtesy, birth_date, salary, reports_to,
                       created_at, updated_at)
VALUES ('Thomas', 'Joseph', 'Inventory Control Specialist', 'Mr', '1980-03-25', 37000, 1002,
        CURRENT_TIMESTAMP,
        DEFAULT);

INSERT INTO employees (last_name, first_name, title, title_of_courtesy, birth_date, salary, reports_to,
                       created_at, updated_at)
VALUES ('Moore', 'Sarah', 'Shipping Coordinator', 'Mrs', '1982-06-19', 36000, 1002,
        CURRENT_TIMESTAMP,
        DEFAULT);

INSERT INTO employees (last_name, first_name, title, title_of_courtesy, birth_date, salary, reports_to,
                       created_at, updated_at)
VALUES ('Jackson', 'Chris', 'Receiving Clerk', 'Mr', '1983-01-08', 35000, 1002, CURRENT_TIMESTAMP,
        DEFAULT);

INSERT INTO employees (last_name, first_name, title, title_of_courtesy, birth_date, salary, reports_to,
                       created_at, updated_at)
VALUES ('White', 'Elizabeth', 'Sales Representative', 'Ms', '1984-04-17', 40000, 1000,
        CURRENT_TIMESTAMP,
        DEFAULT);

INSERT INTO employees (last_name, first_name, title, title_of_courtesy, birth_date, salary, reports_to,
                       created_at, updated_at)
VALUES ('Harris', 'Kevin', 'Sales Representative', 'Mr', '1981-07-23', 41000, 1000,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO employees (last_name, first_name, title, title_of_courtesy, birth_date, salary, reports_to,
                       created_at, updated_at)
VALUES ('Martin', 'Barbara', 'Benefits Coordinator', 'Mrs', '1983-11-11', 43000, 1001,
        CURRENT_TIMESTAMP,
        DEFAULT);

INSERT INTO employees (last_name, first_name, title, title_of_courtesy, birth_date, salary, reports_to,
                       created_at, updated_at)
VALUES ('Thompson', 'Richard', 'Warehouse Supervisor', 'Mr', '1979-08-30', 38000, 1002,
        CURRENT_TIMESTAMP,
        DEFAULT);

INSERT INTO employees (last_name, first_name, title, title_of_courtesy, birth_date, salary, reports_to,
                       created_at, updated_at)
VALUES ('Clark', 'Susan', 'Junior Sales Representative', 'Ms', '1985-12-24', 39000, 1000,
        CURRENT_TIMESTAMP,
        DEFAULT);

-- =====================================================================
-- EMPLOYEE_DETAILS DATA
-- =====================================================================

INSERT INTO employee_details (id, hire_date, address, city, region, postal_code, country, home_phone,
                              extension, notes, created_at, updated_at)
VALUES (1001, '1994-02-15', '908 W. Capital Way', 'Tacoma', 'WA', '98401', 'USA', '(206) 555-9482', '3457',
        'Mary holds a BA in psychology from Colorado State University. She also completed "The Art of the Cold Call." Mary is a member of Toastmasters International.',
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO employee_details (id, hire_date, address, city, region, postal_code, country, home_phone,
                              extension, notes, created_at, updated_at)
VALUES (1002, '1993-10-17', '722 Moss Bay Blvd.', 'Kirkland', 'WA', '98033', 'USA', '(206) 555-3412', '3355',
        'Robert has a BS degree in chemistry from Boston College. He has also completed a certificate program in logistics management. He is fluent in Japanese.',
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO employee_details (id, hire_date, address, city, region, postal_code, country, home_phone,
                              extension, notes, created_at, updated_at)
VALUES (1003, '1991-05-01', '4110 Old Redmond Rd.', 'Redmond', 'WA', '98052', 'USA', '(206) 555-8122', '5176',
        'Patricia completed her studies at Stanford University. She speaks English and Spanish fluently.',
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO employee_details (id, hire_date, address, city, region, postal_code, country, home_phone,
                              extension, notes, created_at, updated_at)
VALUES (1004, '1994-01-02', '14 Garrett Hill', 'London', NULL, 'SW1 8JR', 'UK', '(71) 555-4848', '3453',
        'Michael holds a BA in English from St. Lawrence College. He has strong team management skills and extensive customer service experience.',
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO employee_details (id, hire_date, address, city, region, postal_code, country, home_phone,
                              extension, notes, created_at, updated_at)
VALUES (1005, '1992-08-14', 'Coventry House Miner Rd.', 'London', NULL, 'EC2 7JR', 'UK', '(71) 555-7773', '428',
        'Linda has a BA degree in English from Concordia College and an MA from Fairleigh Dickinson University.',
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO employee_details (id, hire_date, address, city, region, postal_code, country, home_phone,
                              extension, notes, created_at, updated_at)
VALUES (1006, '1995-03-05', '4726 - 11th Ave. N.E.', 'Seattle', 'WA', '98105', 'USA', '(206) 555-1189', '2344',
        'James graduated from St. Lawrence College and has worked in sales positions for three years.',
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO employee_details (id, hire_date, address, city, region, postal_code, country, home_phone,
                              extension, notes, created_at, updated_at)
VALUES (1007, '1993-10-17', '7 Houndstooth Rd.', 'London', NULL, 'WG2 7LT', 'UK', '(71) 555-4444', '452',
        'Jennifer has a degree in business from the University of Washington. She is also a certified SHRM professional.',
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO employee_details (id, hire_date, address, city, region, postal_code, country, home_phone,
                              extension, notes, created_at, updated_at)
VALUES (1008, '1994-11-15', '162 Oak Drive', 'Portland', 'OR', '97204', 'USA', '(503) 555-9831', '2291',
        'David completed his MBA at Portland State University. He specializes in talent acquisition and employee relations.',
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO employee_details (id, hire_date, address, city, region, postal_code, country, home_phone,
                              extension, notes, created_at, updated_at)
VALUES (1009, '1991-08-12', '2743 Bering St.', 'Anchorage', 'AK', '99508', 'USA', '(907) 555-7584', '4567',
        'Lisa has worked in HR for over 10 years with expertise in employee benefits and workplace policies.',
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO employee_details (id, hire_date, address, city, region, postal_code, country, home_phone,
                              extension, notes, created_at, updated_at)
VALUES (1010, '1990-05-01', '9012 Terrace Drive', 'Denver', 'CO', '80014', 'USA', '(303) 555-6228', '2344',
        'Daniel has a background in organizational psychology and specializes in employee development programs.',
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO employee_details (id, hire_date, address, city, region, postal_code, country, home_phone,
                              extension, notes, created_at, updated_at)
VALUES (1011, '1992-04-01', '120 Hanover Sq.', 'London', NULL, 'WA1 1DP', 'UK', '(71) 555-7788', '5423',
        'Michelle has extensive experience in warehouse operations and inventory management systems.',
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO employee_details (id, hire_date, address, city, region, postal_code, country, home_phone,
                              extension, notes, created_at, updated_at)
VALUES (1012, '1993-12-05', '187 Suffolk Lane', 'Boise', 'ID', '83720', 'USA', '(208) 555-8097', '3457',
        'Joseph specializes in supply chain management and has implemented several efficiency improvement programs.',
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO employee_details (id, hire_date, address, city, region, postal_code, country, home_phone,
                              extension, notes, created_at, updated_at)
VALUES (1013, '1994-11-15', '90 Wadhurst Rd.', 'London', NULL, 'OX15 4NB', 'UK', '(71) 555-1717', '563',
        'Sarah has a degree in logistics management and is certified in warehouse safety procedures.',
        CURRENT_TIMESTAMP, DEFAULT);


-- =====================================================================
-- SUPPLIERS DATA
-- =====================================================================

INSERT INTO suppliers (supplier_id,company_name,contact_name,contact_title,address,city,region,postal_code,country,phone,fax,homepage, created_at, updated_at)
VALUES (1,'Exotic Liquids','Charlotte Cooper','Purchasing Manager','49 Gilbert St.','London',NULL,'EC1 4SD','UK','(171) 555-2222',NULL,NULL,CURRENT_TIMESTAMP,DEFAULT);

INSERT INTO suppliers (supplier_id,company_name,contact_name,contact_title,address,city,region,postal_code,country,phone,fax,homepage, created_at, updated_at)
VALUES (2,'New Orleans Cajun Delights','Shelley Burke','Order Administrator','P.O. Box 78934','New Orleans','LA','70117','USA','(100) 555-4822',NULL,'#CAJUN.HTM#', CURRENT_TIMESTAMP,DEFAULT);

INSERT INTO suppliers (supplier_id,company_name,contact_name,contact_title,address,city,region,postal_code,country,phone,fax,homepage, created_at, updated_at)
VALUES (3,'Grandma Kelly''s Homestead','Regina Murphy','Sales Representative','707 Oxford Rd.','Ann Arbor','MI','48104','USA','(313) 555-5735','(313) 555-3349',NULL, CURRENT_TIMESTAMP,DEFAULT);

INSERT INTO suppliers (supplier_id,company_name,contact_name,contact_title,address,city,region,postal_code,country,phone,fax,homepage, created_at, updated_at)
VALUES (4,'Tokyo Traders','Yoshi Nagase','Marketing Manager','9-8 Sekimai Musashino-shi','Tokyo',NULL,'100','Japan','(03) 3555-5011',NULL,NULL, CURRENT_TIMESTAMP,DEFAULT);

INSERT INTO suppliers (supplier_id,company_name,contact_name,contact_title,address,city,region,postal_code,country,phone,fax,homepage, created_at, updated_at)
VALUES (5,'Cooperativa de Quesos ''Las Cabras''','Antonio del Valle Saavedra','Export Administrator','Calle del Rosal 4','Oviedo','Asturias','33007','Spain','(98) 598 76 54',NULL,NULL, CURRENT_TIMESTAMP,DEFAULT);

INSERT INTO suppliers (supplier_id,company_name,contact_name,contact_title,address,city,region,postal_code,country,phone,fax,homepage, created_at, updated_at)
VALUES (6,'Mayumi''s','Mayumi Ohno','Marketing Representative','92 Setsuko Chuo-ku','Osaka',NULL,'545','Japan','(06) 431-7877',NULL,'Mayumi''s (on the World Wide Web)#http://www.microsoft.com/accessdev/sampleapps/mayumi.htm#', CURRENT_TIMESTAMP,DEFAULT);

INSERT INTO suppliers (supplier_id,company_name,contact_name,contact_title,address,city,region,postal_code,country,phone,fax,homepage, created_at, updated_at)
VALUES (7,'Pavlova, Ltd.','Ian Devling','Marketing Manager','74 Rose St. Moonie Ponds','Melbourne','Victoria','3058','Australia','(03) 444-2343','(03) 444-6588',NULL, CURRENT_TIMESTAMP,DEFAULT);

INSERT INTO suppliers (supplier_id,company_name,contact_name,contact_title,address,city,region,postal_code,country,phone,fax,homepage,created_at, updated_at)
VALUES (8,'Specialty Biscuits, Ltd.','Peter Wilson','Sales Representative','29 King''s Way','Manchester',NULL,'M14 GSD','UK','(161) 555-4448',NULL,NULL, CURRENT_TIMESTAMP,DEFAULT);


-- =====================================================================
-- PRODUCTS DATA
-- =====================================================================

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Chai', 1, 1, 'SP75017', 'BAR1000000001', '10 boxes x 20 bags', 18.0, 39, 0, 10, false, CURRENT_TIMESTAMP,
        DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Aniseed Syrup', 1, 2, 'SP75018', 'BAR1000000002', '12 - 550 ml bottles', 10.0, 13, 70, 25, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Chef Anton''s Cajun Seasoning', 2, 2, 'SP75019', 'BAR1000000003', '48 - 6 oz jars', 22.0, 53, 0, 0, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Chef Anton''s Gumbo Mix', 2, 2, 'SP75020', 'BAR1000000004', '36 boxes', 21.35, 0, 0, 0, true,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Grandma''s Boysenberry Spread', 3, 2, 'SP75021', 'BAR1000000005', '12 - 8 oz jars', 25.0, 120, 0, 25, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Uncle Bob''s Organic Dried Pears', 3, 7, 'SP75022', 'BAR1000000006', '12 - 1 lb pkgs.', 30.0, 15, 0, 10, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Northwoods Cranberry Sauce', 3, 2, 'SP75023', 'BAR1000000007', '12 - 12 oz jars', 40.0, 6, 0, 0, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Mishi Kobe Niku', 4, 6, 'SP75024', 'BAR1000000008', '18 - 500 g pkgs.', 97.0, 29, 0, 0, true,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Ikura', 4, 8, 'SP75025', 'BAR1000000009', '12 - 200 ml jars', 31.0, 31, 0, 0, false, CURRENT_TIMESTAMP,
        DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Queso Cabrales', 5, 4, 'SP75026', 'BAR1000000010', '1 kg pkg.', 21.0, 22, 30, 30, false, CURRENT_TIMESTAMP,
        DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Queso Manchego La Pastora', 5, 4, 'SP75027', 'BAR1000000011', '10 - 500 g pkgs.', 38.0, 86, 0, 0, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Konbu', 6, 8, 'SP75028', 'BAR1000000012', '2 kg box', 6.0, 24, 0, 5, false, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Tofu', 6, 7, 'SP75029', 'BAR1000000013', '40 - 100 g pkgs.', 23.25, 35, 0, 0, false, CURRENT_TIMESTAMP,
        DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Genen Shouyu', 6, 2, 'SP75030', 'BAR1000000014', '24 - 250 ml bottles', 15.5, 39, 0, 5, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Pavlova', 7, 3, 'SP75031', 'BAR1000000015', '32 - 500 g boxes', 17.45, 29, 0, 10, false, CURRENT_TIMESTAMP,
        DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Alice Mutton', 7, 6, 'SP75032', 'BAR1000000016', '20 - 1 kg tins', 39.0, 0, 0, 0, true, CURRENT_TIMESTAMP,
        DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Carnarvon Tigers', 7, 8, 'SP75033', 'BAR1000000017', '16 kg pkg.', 62.5, 42, 0, 0, false, CURRENT_TIMESTAMP,
        DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Teatime Chocolate Biscuits', 8, 3, 'SP75034', 'BAR1000000018', '10 boxes x 12 pieces', 9.2, 25, 0, 5, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Sir Rodney''s Marmalade', 8, 3, 'SP75035', 'BAR1000000019', '30 gift boxes', 81.0, 40, 0, 0, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Sir Rodney''s Scones', 8, 3, 'SP75036', 'BAR1000000020', '24 pkgs. x 4 pieces', 10.0, 3, 40, 5, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Gustaf''s Knäckebröd', 8, 5, 'SP75037', 'BAR1000000021', '24 - 500 g pkgs.', 21.0, 104, 0, 25, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Tunnbröd', 8, 5, 'SP75038', 'BAR1000000022', '12 - 250 g pkgs.', 9.0, 61, 0, 25, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Guaraná Fantástica', 1, 1, 'SP75039', 'BAR1000000023', '12 - 355 ml cans', 4.5, 20, 0, 0, true,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('NuNuCa Nuß-Nougat-Creme', 2, 3, 'SP75040', 'BAR1000000024', '20 - 450 g glasses', 14.0, 76, 0, 30, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Boston Crab Meat', 3, 8, 'SP75041', 'BAR1000000025', '24 - 4 oz tins', 18.4, 123, 0, 30, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Jack''s New England Clam Chowder', 4, 8, 'SP75042', 'BAR1000000026', '12 - 12 oz cans', 9.65, 85, 0, 10, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Singaporean Hokkien Fried Mee', 5, 5, 'SP75043', 'BAR1000000027', '32 - 1 kg pkgs.', 14.0, 26, 0, 0, true,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Louisiana Fiery Hot Pepper Sauce', 6, 2, 'SP75044', 'BAR1000000028', '32 - 8 oz bottles', 21.05, 76, 0, 0,
        false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Laughing Lumberjack Lager', 7, 1, 'SP75045', 'BAR1000000029', '24 - 12 oz bottles', 14.0, 52, 0, 10, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Scottish Longbreads', 8, 3, 'SP75046', 'BAR1000000030', '10 boxes x 8 pieces', 12.5, 6, 10, 15, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Gudbrandsdalsost', 4, 4, 'SP75047', 'BAR1000000031', '10 kg pkg.', 36.0, 26, 0, 15, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Outback Lager', 7, 1, 'SP75048', 'BAR1000000032', '24 - 355 ml bottles', 15.0, 15, 10, 30, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Flotemysost', 4, 4, 'SP75049', 'BAR1000000033', '10 - 500 g pkgs.', 21.5, 26, 0, 0, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Mozzarella di Giovanni', 5, 4, 'SP75050', 'BAR1000000034', '24 - 200 g pkgs.', 34.8, 14, 0, 0, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Röd Kaviar', 8, 8, 'SP75051', 'BAR1000000035', '24 - 150 g jars', 15.0, 101, 0, 5, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Longlife Tofu', 6, 7, 'SP75052', 'BAR1000000036', '5 kg pkg.', 10.0, 4, 20, 5, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Rhönbräu Klosterbier', 1, 1, 'SP75053', 'BAR1000000037', '24 - 0.5 l bottles', 7.75, 125, 0, 25, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Lakkalikööri', 2, 1, 'SP75054', 'BAR1000000038', '500 ml', 18.0, 57, 0, 20, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Original Frankfurter grüne Soße', 3, 2, 'SP75055', 'BAR1000000039', '12 boxes', 13.0, 32, 0, 15, false,
        CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO products (product_name, supplier_id, category_id, sku, barcode, quantity_per_unit, unit_price,
                      units_in_stock, units_on_order, reorder_level, discontinued, created_at, updated_at)
VALUES ('Chartreuse verte', 4, 1, 'SP75056', 'BAR1000000040', '750 cc per bottle', 18.0, 69, 0, 5, false,
        CURRENT_TIMESTAMP, DEFAULT);

-- =====================================================================
-- REGION DATA
-- =====================================================================

INSERT INTO region (region_id, region_description, created_at, updated_at)
VALUES (1,'Eastern', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO region (region_id, region_description, created_at, updated_at)
VALUES (2,'Western', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO region (region_id, region_description, created_at, updated_at)
VALUES (3,'Northern', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO region (region_id, region_description, created_at, updated_at)
VALUES (4,'Southern', CURRENT_TIMESTAMP, DEFAULT);

-- =====================================================================
-- ORDERS DATA
-- =====================================================================

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1005, 1005, 'DELIVERED', '2019-06-01', '2019-06-15', '2019-06-16', 22.3, 'Fast Track', '789 Pine Court',
        'Boston',
        'Eastern', '02108', 'USA', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1006, 1006, 'DELIVERED', '2019-07-01', '2019-07-15', '2019-07-16', 17.8, 'Express Post', '456 Elm Street',
        'Toronto',
        'Western', 'M5H 2N2', 'Canada', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1007, 1005, 'DELIVERED', '2019-08-01', '2019-08-15', '2019-08-16', 25.4, 'Quick Ship', '123 Oak Avenue',
        'Manchester',
        'Northern', 'M1 1AA', 'UK', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1008, 1003, 'DELIVERED', '2019-09-01', '2019-09-15', '2019-09-16', 19.2, 'Rapid Delivery', '789 Maple Road',
        'Paris',
        'Southern', '75001', 'France', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1009, 1000, 'DELIVERED', '2019-10-01', '2019-10-15', '2019-10-16', 21.5, 'Swift Express', '321 Pine Street',
        'Berlin',
        'Eastern', '10115', 'Germany', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1010, 1015, 'DELIVERED', '2019-11-01', '2019-11-15', '2019-11-16', 16.7, 'Global Shipping', '654 Oak Lane',
        'Stockholm',
        'Northern', '111 21', 'Sweden', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1011, 1016, 'DELIVERED', '2019-12-01', '2019-12-15', '2019-12-16', 23.9, 'Quick Post', '987 Elm Road',
        'Amsterdam',
        'Western', '1012 JS', 'Netherlands', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1012, 1004, 'DELIVERED', '2020-01-01', '2020-01-15', '2020-01-16', 18.3, 'Speedy Delivery', '741 Maple Court',
        'Rome',
        'Southern', '00100', 'Italy', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1013, 1003, 'DELIVERED', '2020-02-01', '2020-02-15', '2020-02-16', 24.6, 'Express Transport', '852 Pine Avenue',
        'Madrid',
        'Western', '28001', 'Spain', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1014, 1019, 'DELIVERED', '2020-03-01', '2020-03-15', '2020-03-16', 20.1, 'Fast Track', '963 Oak Street',
        'Vienna',
        'Eastern', '1010', 'Austria', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1015, 1004, 'DELIVERED', '2020-04-01', '2020-04-15', '2020-04-16', 15.8, 'Swift Ship', '147 Elm Lane',
        'Brussels',
        'Northern', '1000', 'Belgium', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1016, 1016, 'DELIVERED', '2020-05-01', '2020-05-15', '2020-05-16', 22.4, 'Quick Express', '258 Pine Road',
        'Copenhagen',
        'Western', '1050', 'Denmark', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1017, 1016, 'DELIVERED', '2020-06-01', '2020-06-15', '2020-06-16', 19.7, 'Rapid Post', '369 Maple Avenue',
        'Helsinki',
        'Southern', '00100', 'Finland', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1018, 1019, 'DELIVERED', '2020-07-01', '2020-07-15', '2020-07-16', 21.2, 'Global Express', '741 Oak Court',
        'Oslo',
        'Northern', '0010', 'Norway', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1019, 1019, 'DELIVERED', '2020-08-01', '2020-08-15', '2020-08-16', 17.9, 'Speedy Ship', '852 Elm Street',
        'Lisbon',
        'Western', '1000-001', 'Portugal', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1020, 1016, 'DELIVERED', '2020-09-01', '2020-09-15', '2020-09-16', 23.5, 'Express Freight', '963 Pine Lane',
        'Athens',
        'Southern', '10431', 'Greece', CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1021, 1003, 'DELIVERED', '2020-10-01', '2020-10-15', '2020-10-16', 18.6, 'Quick Ship', '147 Maple Road',
        'Dublin',
        'Western', 'D01 F5P2', 'Ireland', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1022, 1019, 'DELIVERED', '2020-11-01', '2020-11-15', '2020-11-16', 24.3, 'Swift Delivery', '258 Oak Avenue',
        'Warsaw',
        'Eastern', '00-001', 'Poland', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1023, 1004, 'DELIVERED', '2020-12-01', '2020-12-15', '2020-12-16', 20.8, 'Fast Track', '369 Pine Court',
        'Prague',
        'Eastern', '100 00', 'Czech Republic', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1001, 1003, 'DELIVERED', '2021-02-01', '2021-02-15', '2021-02-16', 19.5, 'Express Post', '123 Maple St',
        'London', 'Eastern',
        'EC1A 1BB', 'UK', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1002, 1019, 'DELIVERED', '2021-03-01', '2021-03-15', '2021-03-16', 22.3, 'Fast Track', '456 Oak Ave', 'Paris',
        'Western',
        '75001', 'France', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1003, 1016, 'DELIVERED', '2021-04-01', '2021-04-15', '2021-04-16', 17.8, 'Global Shipping', '789 Pine Rd',
        'Berlin',
        'Northern', '10115', 'Germany', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1004, 1015, 'DELIVERED', '2021-05-01', '2021-05-15', '2021-05-16', 25.4, 'Quick Ship', '321 Elm St', 'Madrid',
        'Southern',
        '28001', 'Spain', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1005, 1005, 'DELIVERED', '2021-06-01', '2021-06-15', '2021-06-16', 20.1, 'Express Transport', '654 Oak Ln',
        'Rome',
        'Western', '00100', 'Italy', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1006, 1006, 'DELIVERED', '2021-07-01', '2021-07-15', '2021-07-16', 23.9, 'Swift Express', '987 Pine Ave',
        'Amsterdam',
        'Northern', '1012', 'Netherlands', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1007, 1016, 'DELIVERED', '2021-08-01', '2021-08-15', '2021-08-16', 18.6, 'Global Express', '741 Maple Rd',
        'Brussels',
        'Eastern', '1000', 'Belgium', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1008, 1019, 'DELIVERED', '2021-09-01', '2021-09-15', '2021-09-16', 21.2, 'Speedy Delivery', '852 Oak St',
        'Stockholm',
        'Western', '111 21', 'Sweden', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1009, 1003, 'DELIVERED', '2021-10-01', '2021-10-15', '2021-10-16', 24.5, 'Fast Track', '963 Pine Ln',
        'Copenhagen',
        'Northern', '1050', 'Denmark', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1010, 1004, 'DELIVERED', '2021-11-01', '2021-11-15', '2021-11-16', 19.8, 'Express Freight', '147 Elm Ave',
        'Oslo',
        'Southern', '0010', 'Norway', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1011, 1019, 'DELIVERED', '2021-12-01', '2021-12-15', '2021-12-16', 22.7, 'Swift Ship', '258 Oak Rd', 'Helsinki',
        'Eastern',
        '00100', 'Finland', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1012, 1012, 'DELIVERED', '2022-01-01', '2022-01-15', '2022-01-16', 17.3, 'Quick Express', '369 Pine St',
        'Dublin', 'Western',
        'D01', 'Ireland', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1013, 1016, 'DELIVERED', '2022-02-01', '2022-02-15', '2022-02-16', 25.9, 'Global Shipping', '741 Oak Ave',
        'Warsaw',
        'Northern', '00-001', 'Poland', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1014, 1014, 'DELIVERED', '2022-03-01', '2022-03-15', '2022-03-16', 20.4, 'Express Post', '852 Elm Ln', 'Prague',
        'Southern',
        '100 00', 'Czech Republic', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1015, 1006, 'DELIVERED', '2022-04-01', '2022-04-15', '2022-04-16', 23.1, 'Fast Track', '963 Pine Ave',
        'Budapest', 'Eastern',
        '1011', 'Hungary', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1016, 1016, 'DELIVERED', '2022-05-01', '2022-05-15', '2022-05-16', 18.9, 'Swift Express', '147 Oak St',
        'Athens', 'Western',
        '104 31', 'Greece', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1017, 1019, 'DELIVERED', '2022-06-01', '2022-06-15', '2022-06-16', 21.6, 'Global Express', '258 Pine Rd',
        'Lisbon',
        'Northern', '1000-001', 'Portugal', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1018, 1016, 'DELIVERED', '2022-07-01', '2022-07-15', '2022-07-16', 24.8, 'Express Transport', '369 Elm Ave',
        'Vienna',
        'Southern', '1010', 'Austria', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1019, 1019, 'DELIVERED', '2022-08-01', '2022-08-15', '2022-08-16', 19.2, 'Speedy Delivery', '741 Oak Ln',
        'Bern', 'Eastern',
        '3000', 'Switzerland', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1020, 1003, 'DELIVERED', '2022-09-01', '2022-09-15', '2022-09-16', 22.5, 'Quick Ship', '852 Pine St',
        'Luxembourg',
        'Western', 'L-1111', 'Luxembourg', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1021, 1004, 'DELIVERED', '2022-10-01', '2022-10-15', '2022-10-16', 17.7, 'Express Post', '963 Oak Ave',
        'Bucharest',
        'Northern', '010001', 'Romania', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1022, 1005, 'DELIVERED', '2022-11-01', '2022-11-15', '2022-11-16', 25.3, 'Global Shipping', '147 Elm Rd',
        'Sofia',
        'Southern', '1000', 'Bulgaria', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1023, 1006, 'DELIVERED', '2022-12-01', '2022-12-15', '2022-12-16', 20.6, 'Fast Track', '258 Pine Ln', 'Zagreb',
        'Eastern',
        '10000', 'Croatia', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1024, 1019, 'DELIVERED', '2023-01-01', '2023-01-15', '2023-01-16', 23.4, 'Swift Express', '369 Oak St',
        'Belgrade',
        'Western', '11000', 'Serbia', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1001, 1015, 'DELIVERED', '2023-02-01', '2023-02-15', '2023-02-16', 18.8, 'Global Express', '741 Pine Ave',
        'Bratislava',
        'Northern', '811 01', 'Slovakia', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1002, 1006, 'DELIVERED', '2023-03-01', '2023-03-15', '2023-03-16', 21.9, 'Express Transport', '852 Elm St',
        'Ljubljana',
        'Southern', '1000', 'Slovenia', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1003, 1003, 'DELIVERED', '2023-04-01', '2023-04-15', '2023-04-16', 24.2, 'Speedy Delivery', '963 Oak Rd',
        'Vilnius',
        'Eastern', '01001', 'Lithuania', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1004, 1004, 'DELIVERED', '2023-05-01', '2023-05-15', '2023-05-16', 19.7, 'Quick Express', '147 Pine Ln', 'Riga',
        'Western',
        'LV-1050', 'Latvia', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1005, 1005, 'DELIVERED', '2023-06-01', '2023-06-15', '2023-06-16', 22.8, 'Express Post', '258 Oak Ave',
        'Tallinn',
        'Northern', '10111', 'Estonia', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1006, 1006, 'DELIVERED', '2023-07-01', '2023-07-15', '2023-07-16', 17.5, 'Global Shipping', '369 Elm Rd',
        'Valletta',
        'Southern', 'VLT 1117', 'Malta', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1007, 1019, 'DELIVERED', '2023-08-01', '2023-08-15', '2023-08-16', 25.7, 'Fast Track', '741 Pine St', 'Nicosia',
        'Eastern',
        '1065', 'Cyprus', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1008, 1015, 'DELIVERED', '2023-09-01', '2023-09-15', '2023-09-16', 20.3, 'Swift Express', '852 Oak Ln',
        'Reykjavik',
        'Western', '101', 'Iceland', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1009, 1016, 'DELIVERED', '2023-10-01', '2023-10-15', '2023-10-16', 23.6, 'Global Express', '963 Elm Ave',
        'Oslo', 'Northern',
        '0010', 'Norway', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1010, 1015, 'DELIVERED', '2023-11-01', '2023-11-15', '2023-11-16', 18.4, 'Express Transport', '147 Oak St',
        'Geneva',
        'Southern', '1201', 'Switzerland', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1011, 1004, 'DELIVERED', '2023-12-01', '2023-12-15', '2023-12-16', 21.7, 'Speedy Delivery', '258 Pine Ave',
        'Monaco',
        'Eastern', '98000', 'Monaco', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1012, 1003, 'DELIVERED', '2024-01-01', '2024-01-15', '2024-01-16', 24.9, 'Quick Ship', '369 Elm Ln',
        'Andorra la Vella',
        'Western', 'AD500', 'Andorra', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1013, 1019, 'DELIVERED', '2024-02-01', '2024-02-15', '2024-02-16', 19.3, 'Express Post', '741 Oak Rd',
        'San Marino',
        'Northern', '47890', 'San Marino', CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO orders (customer_id, employee_id, order_status, order_date, required_date, shipped_date, freight, ship_name,
                    ship_address,
                    ship_city, ship_region, ship_postal_code, ship_country, created_at, updated_at)
VALUES (1014, 1015, 'DELIVERED', '2024-03-01', '2024-03-15', '2024-03-16', 22.6, 'Global Shipping', '852 Pine St',
        'Vatican City',
        'Southern', '00120', 'Vatican City', CURRENT_TIMESTAMP, DEFAULT);

-- =====================================================================
-- ORDER_DETAILS DATA
-- =====================================================================


INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1000, 1000, 18.0, 5, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1000, 1001, 10.0, 3, 0.05, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1001, 1002, 22.0, 2, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1001, 1003, 21.35, 4, 0.1, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1002, 1000, 25.0, 1, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1002, 1001, 30.0, 3, 0.05, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1003, 1002, 40.0, 2, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1003, 1003, 97.0, 1, 0.15, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1004, 1000, 31.0, 4, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1004, 1001, 21.0, 2, 0.05, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1005, 1002, 38.0, 3, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1005, 1003, 6.0, 5, 0.1, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1006, 1000, 23.25, 2, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1006, 1001, 15.5, 4, 0.05, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1007, 1002, 17.45, 1, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1007, 1003, 39.0, 3, 0.1, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1008, 1000, 62.5, 2, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1008, 1001, 9.2, 5, 0.05, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1009, 1002, 81.0, 1, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1009, 1003, 10.0, 4, 0.15, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1010, 1000, 21.0, 3, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1010, 1001, 9.0, 2, 0.05, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1011, 1002, 4.5, 5, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1011, 1003, 14.0, 2, 0.1, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1012, 1000, 18.4, 3, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1012, 1001, 9.65, 4, 0.05, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1013, 1002, 14.0, 1, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1013, 1003, 21.05, 3, 0.15, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1014, 1000, 14.0, 2, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1014, 1001, 12.5, 5, 0.05, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1015, 1002, 36.0, 1, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1015, 1003, 15.0, 4, 0.1, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1016, 1000, 21.5, 3, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1016, 1001, 34.8, 2, 0.05, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1017, 1002, 15.0, 5, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1017, 1003, 10.0, 1, 0.15, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1018, 1000, 7.75, 4, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1018, 1001, 18.0, 2, 0.05, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1019, 1002, 13.0, 3, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1019, 1003, 18.0, 5, 0.1, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1020, 1000, 18.0, 2, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1020, 1001, 10.0, 4, 0.05, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1021, 1002, 22.0, 1, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1021, 1003, 21.35, 3, 0.15, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1022, 1000, 25.0, 5, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1022, 1001, 30.0, 2, 0.05, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1023, 1002, 40.0, 4, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1023, 1003, 97.0, 1, 0.1, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1024, 1000, 31.0, 3, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1025, 1001, 18.0, 3, 0.05, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1026, 1002, 22.0, 4, 0.1, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1027, 1003, 25.0, 2, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1028, 1004, 30.0, 5, 0.15, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1029, 1005, 40.0, 1, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1030, 1006, 97.0, 3, 0.1, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1031, 1007, 31.0, 2, 0.05, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1032, 1008, 21.0, 4, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1033, 1009, 38.0, 1, 0.1, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1034, 1010, 6.0, 5, 0.15, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1035, 1011, 23.25, 2, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1036, 1012, 15.5, 3, 0.05, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1037, 1013, 17.45, 4, 0.1, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1038, 1014, 39.0, 1, 0.0, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO order_details (order_id, product_id, total, quantity, discount, created_at, updated_at)
VALUES (1039, 1015, 62.5, 3, 0.15, CURRENT_TIMESTAMP, DEFAULT);

-- =====================================================================
-- TERRITORIES DATA
-- =====================================================================

INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (1, 'Westboro', 1, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (2, 'Bedford', 1, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (3, 'Georgetow', 1, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (4, 'Boston', 1, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (5, 'Cambridge', 1, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (6, 'Braintree', 1, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (7, 'Providence', 1, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (8, 'Hollis', 3, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (9, 'Portsmouth', 3, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (10, 'Wilton', 1, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (11, 'Morristown', 1, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (12, 'Edison', 1, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (13, 'New York', 1, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (14, 'New York', 1, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (15, 'Mellvile', 1, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (16, 'Fairport', 1, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (17, 'Philadelphia', 3, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (18, 'Neward', 1, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (19, 'Rockville', 1, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (20, 'Greensboro', 1, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (21, 'Cary', 1, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (22, 'Columbia', 4, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (23, 'Atlanta', 4, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (24, 'Savannah', 4, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (25, 'Orlando', 4, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (26, 'Tampa', 4, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (27, 'Louisville', 1, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (28, 'Beachwood', 3, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (29, 'Findlay', 3, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (30, 'Southfield', 3, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (31, 'Troy', 3, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (32, 'Bloomfield Hills', 3, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (33, 'Racine', 3, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (34, 'Roseville', 3, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (35, 'Minneapolis', 3, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (36, 'Hoffman Estates', 2, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (37, 'Chicago', 2, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (38, 'Bentonville', 4, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (39, 'Dallas', 4, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (40, 'Austin', 4, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (41, 'Denver', 2, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (42, 'Colorado Springs', 2, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (43, 'Phoenix', 2, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (44, 'Scottsdale', 2, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (45, 'Santa Monica', 2, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (46, 'Menlo Park', 2, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (47, 'San Francisco', 2, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (48, 'Campbell', 2, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (49, 'Santa Clara', 2, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (50, 'Santa Cruz', 2, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (51, 'Bellevue', 2, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (52, 'Redmond', 2, CURRENT_TIMESTAMP, DEFAULT);
INSERT INTO territories (territory_id, territory_description, region_id, created_at, updated_at)
VALUES (53, 'Seattle', 2, CURRENT_TIMESTAMP, DEFAULT);

-- =====================================================================
-- EMPLOYEE_TERRITORIES DATA
-- =====================================================================























