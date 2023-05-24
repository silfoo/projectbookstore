CREATE TABLE BOOK (ID integer auto_increment primary key,
                    AUTHOR varchar,
                    CATEGORY varchar,
                    EDITORIAL varchar,
                    IS_LOWERED varchar,
                    ISBN varchar,
                    TITLE varchar,
                    AMOUNT decimal,
                    CURRENCY varchar,
                    published_Date varchar);

CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;

INSERT INTO BOOK (AUTHOR, CATEGORY, EDITORIAL, IS_LOWERED, ISBN,  TITLE, AMOUNT, CURRENCY, published_Date)
VALUES
('Patric Suskind', 'THRILLER', 'Penguin Books', 'yes', '0-14-012083-1', 'Perfume: The Story of a murderer', 10,'EUR', 19850101),
('Herge', 'CHILD_GENDER', 'Egmont', 'no', '978-1-4052-0635-8', 'The adventures of Tintin', 7.99 ,'EUR',20090701),
('Frank Herbert', 'FICTION', 'SF Masterworks', 'no', '978-0-575-08150-5', 'Dune', 13.99 ,'EUR',20071025),
('Anne Rice', 'FANTASY', 'Timus Mas', 'yes', '84-7722-453-6', 'Lestat, El vampiro', 8.69 ,'EUR',19901208),
('J.R.R. Tolkien', 'FANTASY', 'Harper Collins', 'no', '978-0-261-10320-7', 'The Lord of the Ring', 53.70 ,'EUR',20041020);
