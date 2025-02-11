-- Drop the existing table if it exists
DROP TABLE IF EXISTS MORTGAGE_RATE;

CREATE TABLE MORTGAGE_RATE (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    maturity_period INT NOT NULL UNIQUE,
    interest_rate DECIMAL(5, 2) NOT NULL,
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);