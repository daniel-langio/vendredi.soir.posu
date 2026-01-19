ALTER TABLE "transaction"
    ADD COLUMN IF NOT EXISTS "wallet_reference" varchar;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM pg_constraint WHERE conname = 'transaction_wallet_reference_fk'
    ) THEN
ALTER TABLE "transaction"
    ADD CONSTRAINT transaction_wallet_reference_fk
        FOREIGN KEY ("wallet_reference") REFERENCES "wallet"("reference");
END IF;
END$$;
