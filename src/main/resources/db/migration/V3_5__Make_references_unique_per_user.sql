-- Drop foreign key that depends on wallet(reference) unique constraint
ALTER TABLE "transaction" DROP CONSTRAINT IF EXISTS transaction_wallet_reference_fk;

-- Drop unique constraint on wallet reference and name
ALTER TABLE "wallet" DROP CONSTRAINT IF EXISTS wallet_reference_uq;
ALTER TABLE "wallet" DROP CONSTRAINT IF EXISTS wallet_name_key;

-- Drop unique constraint on label reference
-- Postgres default name for UNIQUE (reference) on table label
ALTER TABLE "label" DROP CONSTRAINT IF EXISTS label_reference_key;

-- Add user_id to label
ALTER TABLE "label" ADD COLUMN user_id VARCHAR(255) REFERENCES "user"(id);

-- Assign existing labels to the first user found, if any
DO $$
DECLARE
    first_user_id VARCHAR(255);
BEGIN
    SELECT id INTO first_user_id FROM "user" LIMIT 1;
    IF first_user_id IS NOT NULL THEN
        UPDATE "label" SET user_id = first_user_id WHERE user_id IS NULL;
    END IF;
END $$;

-- Add unique constraint on (user_id, reference) for label
ALTER TABLE "label" ADD CONSTRAINT label_user_id_reference_uq UNIQUE (user_id, reference);
