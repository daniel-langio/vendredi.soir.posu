DO $$
    BEGIN
        IF NOT EXISTS (
            SELECT 1 FROM pg_constraint WHERE conname = 'wallet_reference_uq'
        ) THEN
            ALTER TABLE "wallet"
                ADD CONSTRAINT wallet_reference_uq
                    UNIQUE (reference);
        END IF;
    END$$;