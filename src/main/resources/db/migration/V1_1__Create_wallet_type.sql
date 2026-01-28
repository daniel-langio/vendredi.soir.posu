DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'wallet_type') THEN
        CREATE TYPE wallet_type AS ENUM (
            'CASH',
            'BANK_ACCOUNT',
            'MOBILE_MONEY',
            'CRYPTO'
        );
    END IF;
END$$;
