DROP TYPE IF EXISTS wallet_type;

CREATE TYPE wallet_type AS ENUM (
    'CASH',
    'BANK_ACCOUNT',
    'MOBILE_MONEY',
    'CRYPTO'
);
