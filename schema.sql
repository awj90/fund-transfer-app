create database acme_bank;

use acme_bank;

create table accounts (
    account_id VARCHAR(10) NOT NULL,
    name VARCHAR(50) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY(account_id)
);

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/data.csv' 
INTO TABLE accounts
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
(account_id, name, balance);

INSERT INTO accounts (account_id, name, balance) VALUES
('2468011111', 'wilma', 400.00),
('1357900000', 'betty', 500.00);