-- =====================================================================
-- INSERT SAMPLE CUSTOMER USER
-- =====================================================================
-- username and password are same


INSERT INTO users (username, password, email, first_name, last_name, disabled, account_expired, account_locked,
                   credentials_expired,
                   created_at, updated_at)
VALUES ('AROUT', '$2a$10$9RblWScj6Ef66SPULILIguZl82Q54MYGUd5h7NwO8qwI0H3Gzk2/m', 'arout@around.com', 'Around',
        'the Horn', false, false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('BERGS', '$2a$10$Q7zf6f5Q5X1vAD8KbqQX2O0kTAoFr/6ZgU9.1azKDG6KQwf3bwlYS', 'bergs@bergs.com', 'Berglunds',
        'snabbköp', false, false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('BLAUS', '$2a$10$H3x8nRh3UYhZ0K6D9JxbX.dV2nH5SwzFaQ2yFg.KZvWqYv.xSDLk2', 'blaus@blaus.com', 'Blauer',
        'See Delikatessen', false, false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('BLONP', '$2a$10$WqQA8WVDgU8vqOVz6TjhB.Pzp1x6TxGw34ZRoPDUhgsAXX7vwYm0O', 'blonp@blonp.com', 'Blondesddsl',
        'père et fils', false, false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('BOLID', '$2a$10$3AkbmmDQ5gQDwSXVlJ.Keui4CebeJxAmaIBbYclRzk0RcwHXIXN5W', 'bolid@bolid.com', 'Bólido',
        'Comidas preparadas', false, false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('BONAP', '$2a$10$0R4ys6gvG3Zq6tFqy4gufOiEiD0g2sJe7QMc3bZT6H9W4HCg5OFAK', 'bonap@bonap.com', 'Bon', 'app', false,
        false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('BOTTM', '$2a$10$U4hOl4gZ4UyjM6sZ3tTrTOEwO/lKBz0t7qSh8xvw/YJZsz5zBAEVi', 'bottm@bottm.com', 'Bottom-Dollar',
        'Markets', false, false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('BSBEV', '$2a$10$JVWljgF7rXZY3FBLn4HKgO.yf/uc4LDdS8gFI6inYyM3g3L3KPROG', 'bsbev@bsbev.com', 'Bs', 'Beverages',
        false, false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('CACTU', '$2a$10$wSDbZsiwQVU3FuxXoYTl9.T1tXpFpGubEU7P.RfZmO/4rCBVPwpJ2', 'cactu@cactu.com', 'Cactus',
        'Comidas para llevar', false, false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('CENTC', '$2a$10$cTXgSLJtL.rOO98fJ3RIBOxq.fv6lH6xTRq4q6sWJ.MnD2n9wYZ7S', 'centc@centc.com', 'Centro',
        'comercial Moctezuma', false, false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('CHOPS', '$2a$10$F4.3nQcXyeIwsCUJMUtr0.u7tNnZKZPhWpW1rkhzFGTgua5RLhKUq', 'chops@chops.com', 'Chop-suey',
        'Chinese', false, false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('COMMI', '$2a$10$qxY8wEnVxh5FQasRv6umK.N6mUUpiUn42A2qqHW7cRk90UdBUoNF.', 'commi@commi.com', 'Comércio',
        'Mineiro', false, false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('CONSH', '$2a$10$U1m1TvAcQZo5STxJ5LnSa.UJ22w8EoFlBvvVNtQ1UdTcXNxsXY5E2', 'consh@consh.com', 'Consolidated',
        'Holdings', false, false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('DRACD', '$2a$10$60Y9g.3XsaKUlOIqPVPqG.YtFeILQVpt32DBwqTDfaqtPwctbfPxu', 'dracd@dracd.com', 'Drachenblut',
        'Delikatessen', false, false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('DUMON', '$2a$10$3O4w0YSHmBAf9PfBOdjhEOXlGKx4Qw/7PuGYe4suvRbA57yxeuZjG', 'dumon@dumon.com', 'Du',
        'monde entier', false, false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('EASTC', '$2a$10$x.Jjggb5aWlO0ihhQo9LOOJlEmzOLbxLpvZG2Gj1FY4oBD7blSG22', 'eastc@eastc.com', 'Eastern',
        'Connection', false, false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('ERNSH', '$2a$10$6YpTFkXPeyb3j1Rcns3KFuogSfnz/sCshcCL/TYEmQoxcpe/oQMIa', 'ernsh@ernsh.com', 'Ernst', 'Handel',
        false, false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('FAMIA', '$2a$10$8wYIhIVPXPUX4y7rGKNyAOlM9gg.H6Xdyic7hhMXvblRWFaBV6Yhq', 'famia@famia.com', 'Familia',
        'Arquibaldo', false, false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('FISSA', '$2a$10$H7ppC5abgXKx2h1gqEOA6eBFmoKySZJi6sZIawYK6u/D5kZhvbgbG', 'fissa@fissa.com', 'FISSA',
        'Fabrica Inter', false, false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('FOLIG', '$2a$10$tVe4zdbB2lyzQ/80x6eRS.YquYh7ZDO6h/e6P9arEkQE3sNnSaIla', 'folig@folig.com', 'Folies',
        'gourmandes', false, false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('FOLKO', '$2a$10$soGUL9Woc0qQo1sIF8amce0nqz4LN6pHzC6bLnSoZzcyvv6ycyETG', 'folko@folko.com', 'Folk', 'och fä HB',
        false, false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('FRANK', '$2a$10$yv5AAoO5AqQPvjcWQoSO2eGcsyLpq1tBFER.mleC9f1jOuR7fNmgW', 'frank@frank.com', 'Frankenversand',
        'Trade', false, false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('FRANR', '$2a$10$CbyP1Yl8HjW49dYDILg7geF0mK34FGRRl9CgxiEVFGS5H9o86vQ6m', 'franr@franr.com', 'France',
        'restauration', false, false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('FRANS', '$2a$10$NYz9ZqgDazt9NlmXiaBj/.3vcSuH75lvvG7XcMMC8dZ5p1TtHgG0C', 'frans@frans.com', 'Franchi', 'SpA',
        false, false, false, false, CURRENT_TIMESTAMP, DEFAULT),
       ('FURIB', '$2a$10$0uXhjGHooh4JF.m9I7g4uus1pOIlaa/f9B8sBC3r67yAnX.D1FW4u', 'furib@furib.com', 'Furia',
        'Bacalhau e Frutos do Mar', false, false, false, false, CURRENT_TIMESTAMP, DEFAULT);


-- =====================================================================
-- INSERT SAMPLE ROLE_ADMIN USER
-- =====================================================================
-- username: johndoe
-- password: admin123


INSERT INTO users (username, password, email, first_name, last_name, disabled, account_expired, account_locked,
                   credentials_expired,
                   created_at, updated_at)
VALUES ('johndoe', '$2a$10$yrLK2j/q4DJcgNC6BEfVu.czjD8wc.dce0sEY4l65zDUvQsArXv8i', 'johndoe@gmail.com', 'John', 'Doe',
        false, false, false, false, CURRENT_TIMESTAMP, DEFAULT);


-- =====================================================================
-- INSERT SAMPLE ROLE_MANAGER USER
-- =====================================================================
-- username and password are same

-- SALES MANAGER
INSERT INTO users (username, password, email, first_name, last_name, disabled, account_expired, account_locked,
                   credentials_expired,
                   created_at, updated_at)
VALUES('smithjohn', '$2a$10$kR78tBZ9uIrb1K4QdfYqlOAN8ANBPEtgEfCKUPnqSbbX9oFiWZCrO', 'johnsmith@gmail.com', 'John', 'Smith',
       false, false, false, false, CURRENT_TIMESTAMP, DEFAULT);

-- HR MANAGER
INSERT INTO users (username, password, email, first_name, last_name, disabled, account_expired, account_locked,
                   credentials_expired,
                   created_at, updated_at)
VALUES('robertwilliams', '$2a$10$BL096wBDmBSFgLHSCvjG7.I3vxWeKsfZICPdnQK/QN/A/fuwiqVwq', 'robertwilliams@gmail.com', 'Robert', 'Williams',
       false, false, false, false, CURRENT_TIMESTAMP, DEFAULT);

-- WAREHOUSE MANAGER
INSERT INTO users (username, password, email, first_name, last_name, disabled, account_expired, account_locked,
                   credentials_expired,
                   created_at, updated_at)
VALUES('maryjohnson', '$2a$10$SFsn0Lke.CziZdSy.rPSEubmEMKaKQ1UxpJhaX5EmPUxSxs5TlSmO', 'maryjohnson@gmail.com', 'Mary', 'Johnson',
       false, false, false, false, CURRENT_TIMESTAMP, DEFAULT);



-- =====================================================================
-- INSERT SAMPLE ROLE_SALES USER
-- =====================================================================


INSERT INTO users (username, password, email, first_name, last_name, disabled, account_expired, account_locked,
                   credentials_expired,
                   created_at, updated_at)
VALUES('patriciabrown', '$2a$10$xu7SLSIp01YjOZPYAXr8mOFAx.MfnoT2sl78bRtkPFuZNp7f85dz2', 'patriciabrown@gmail.com', 'Patricia', 'Brown',
       false, false, false, false, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO users (username, password, email, first_name, last_name, disabled, account_expired, account_locked,
                   credentials_expired,
                   created_at, updated_at)
VALUES('lindagarcia', '$2a$10$ux1nwzVtQpdM8KitXkFHSuuvj7rr94yYVkdtYHIp38/l2ZfUh1UUy', 'lindagarcia@gmail.com', 'Linda', 'Garcia',
       false, false, false, false, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO users (username, password, email, first_name, last_name, disabled, account_expired, account_locked,
                   credentials_expired,
                   created_at, updated_at)
VALUES('michealjones', '$2a$10$7Gm.5eP8HdrHuos1Jj7t3.RhgMlTwBg3k6tkqCulYiolLto1vxXr.', 'michealjones@gmail.com', 'Micheal', 'Jones',
       false, false, false, false, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO users (username, password, email, first_name, last_name, disabled, account_expired, account_locked,
                   credentials_expired,
                   created_at, updated_at)
VALUES('jamesmiller', '$2a$10$.UPehn16UPYgn/YytWquWuF9wL/FtTOSuu1zkk5Fi7Cp6Ew1HZoGy', 'jamesmiller@gmail.com', 'James', 'Miller',
       false, false, false, false, CURRENT_TIMESTAMP, DEFAULT);


INSERT INTO users (username, password, email, first_name, last_name, disabled, account_expired, account_locked,
                   credentials_expired,
                   created_at, updated_at)
VALUES('elizabethwhite', '$2a$10$.3komDCkcQ07piUMKDEhM.18S8cb2U6lh9xIz/hkVPgD5qJUJpV0C', 'elizabethwhite@gmail.com', 'Elizabeth', 'White',
       false, false, false, false, CURRENT_TIMESTAMP, DEFAULT);


INSERT INTO users (username, password, email, first_name, last_name, disabled, account_expired, account_locked,
                   credentials_expired,
                   created_at, updated_at)
VALUES('kevinharris', '$2a$10$cz6MRgNkZRvpN02JDjYaYONAi5Y3PnBRlQrhMujQKVQh1Txnwow02', 'kevinharris@gmail.com', 'Kevin', 'Harris',
       false, false, false, false, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO users (username, password, email, first_name, last_name, disabled, account_expired, account_locked,
                   credentials_expired,
                   created_at, updated_at)
VALUES('jenniferdavis', '$2a$10$K83LwMuFDnQzrsTkJNu/BuGOWqDQ1ZcS339sUShJWXfLhoGI8LP6O', 'jenniferdavis@gmail.com', 'Jennifer', 'Davis',
       false, false, false, false, CURRENT_TIMESTAMP, DEFAULT);


-- =====================================================================
-- INSERT SAMPLE ROLE_HR USER
-- =====================================================================


INSERT INTO users (username, password, email, first_name, last_name, disabled, account_expired, account_locked,
                   credentials_expired,
                   created_at, updated_at)
VALUES('susanclark', '$2a$10$91..TnQ4mI7xSwxs4PLdH.Bn0s9yGmP/BZBGFblSATaiWxHw4L5Ra', 'susanclark@gmail.com', 'Susan', 'Clark',
       false, false, false, false, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO users (username, password, email, first_name, last_name, disabled, account_expired, account_locked,
                   credentials_expired,
                   created_at, updated_at)
VALUES('lisamartinez', '$2a$10$NUcOgT/WnM4tMH11IXZgDueI4sDATAmcWxrZM/Q.KgqDsVNNs4RT2', 'lisamartinez@gmail.com', 'Lisa', 'Martinez',
       false, false, false, false, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO users (username, password, email, first_name, last_name, disabled, account_expired, account_locked,
                   credentials_expired,
                   created_at, updated_at)
VALUES('davidrodriguez', '$2a$10$DdUWH4T8GVkF0peSjtlDcOImvmjtXyrsP7O1aCkf/n2qk4lqM//D2', 'davidrodriguez@gmail.com', 'David', 'Rodriguez',
       false, false, false, false, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO users (username, password, email, first_name, last_name, disabled, account_expired, account_locked,
                   credentials_expired,
                   created_at, updated_at)
VALUES('danielanderson', '$2a$10$rfngNmGDAwDobLKwna9Wr.KiaHfTAS1ekWCQdRXdKxKFu9OC33RGK', 'danielanderson@gmail.com', 'Daniel', 'Anderson',
       false, false, false, false, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO users (username, password, email, first_name, last_name, disabled, account_expired, account_locked,
                   credentials_expired,
                   created_at, updated_at)
VALUES('barbaramartin', '$2a$10$nhnmeSM6go8DvD81UQh4luxa5QXjq1oaDqe/EqfDjlXACcpoxAL82', 'barbaramartin@gmail.com', 'Barbara', 'Martin',
       false, false, false, false, CURRENT_TIMESTAMP, DEFAULT);



-- =====================================================================
-- INSERT SAMPLE ROLE_WAREHOUSE USER
-- =====================================================================

INSERT INTO users (username, password, email, first_name, last_name, disabled, account_expired, account_locked,
                   credentials_expired,
                   created_at, updated_at)
VALUES('taylormichelle', '$2a$10$fFj94WqThFWwd9PxUBjOAOrKz34Ls6aAYSqkLJRyMh3s7Nk70bkEa', 'taylormichelle@gmail.com', 'Taylor', 'Michelle',
       false, false, false, false, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO users (username, password, email, first_name, last_name, disabled, account_expired, account_locked,
                   credentials_expired,
                   created_at, updated_at)
VALUES('josephthomas', '$2a$10$IgqY.yRiT6HH93hBxVrELu3rMlo1E3RoAjuFZOIHFLTVBEaE99p9C', 'josephthomas@gmail.com', 'Joseph', 'Thomas',
       false, false, false, false, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO users (username, password, email, first_name, last_name, disabled, account_expired, account_locked,
                   credentials_expired,
                   created_at, updated_at)
VALUES('sarahmoore', '$2a$10$9NcRr181UnOVePMkSz4vR.LRE8/fYDZXWf2uGrZ92pWaePIYCqFE6', 'sarahmoore@gmail.com', 'Sarah', 'Moore',
       false, false, false, false, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO users (username, password, email, first_name, last_name, disabled, account_expired, account_locked,
                   credentials_expired,
                   created_at, updated_at)
VALUES('christopherjackson', '$2a$10$7MguTdrS50Kvlw1g6lliyONIQid4gijyPa8YF.S5firx3qUAqp/JO', 'christopherjackson@gmail.com', 'Christopher', 'Jackson',
       false, false, false, false, CURRENT_TIMESTAMP, DEFAULT);

INSERT INTO users (username, password, email, first_name, last_name, disabled, account_expired, account_locked,
                   credentials_expired,
                   created_at, updated_at)
VALUES('richardthompson', '$2a$10$BGxIK6vURByGEddPnG1o3eRHZrjaF19LQ0DKiPCH5jl5rQ74WSTl6', 'richardthompson@gmail.com', 'Richard', 'Thompson',
       false, false, false, false, CURRENT_TIMESTAMP, DEFAULT);


