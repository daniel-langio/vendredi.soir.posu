-- V3_2__Convert_ids_to_varchar.sql

-- 1. Drop FKs
ALTER TABLE "transaction" DROP CONSTRAINT IF EXISTS transaction_wallet_id_fkey;
ALTER TABLE "transaction_label" DROP CONSTRAINT IF EXISTS transaction_label_transaction_id_fkey;
ALTER TABLE "transaction_label" DROP CONSTRAINT IF EXISTS transaction_label_label_id_fkey;
ALTER TABLE "user_wallet" DROP CONSTRAINT IF EXISTS user_wallet_user_id_fkey;
ALTER TABLE "user_wallet" DROP CONSTRAINT IF EXISTS user_wallet_wallet_id_fkey;

-- 2. Change column types
ALTER TABLE "user" ALTER COLUMN id TYPE varchar USING id::varchar;
ALTER TABLE "wallet" ALTER COLUMN id TYPE varchar USING id::varchar;
ALTER TABLE "label" ALTER COLUMN id TYPE varchar USING id::varchar;
ALTER TABLE "transaction" ALTER COLUMN id TYPE varchar USING id::varchar;
ALTER TABLE "transaction" ALTER COLUMN wallet_id TYPE varchar USING wallet_id::varchar;
ALTER TABLE "transaction_label" ALTER COLUMN transaction_id TYPE varchar USING transaction_id::varchar;
ALTER TABLE "transaction_label" ALTER COLUMN label_id TYPE varchar USING label_id::varchar;
ALTER TABLE "user_wallet" ALTER COLUMN user_id TYPE varchar USING user_id::varchar;
ALTER TABLE "user_wallet" ALTER COLUMN wallet_id TYPE varchar USING wallet_id::varchar;

-- 3. Re-add FKs
ALTER TABLE "transaction" ADD CONSTRAINT transaction_wallet_id_fkey FOREIGN KEY (wallet_id) REFERENCES wallet(id) ON DELETE CASCADE;
ALTER TABLE "transaction_label" ADD CONSTRAINT transaction_label_transaction_id_fkey FOREIGN KEY (transaction_id) REFERENCES transaction(id);
ALTER TABLE "transaction_label" ADD CONSTRAINT transaction_label_label_id_fkey FOREIGN KEY (label_id) REFERENCES label(id);
ALTER TABLE "user_wallet" ADD CONSTRAINT user_wallet_user_id_fkey FOREIGN KEY (user_id) REFERENCES "user"(id);
ALTER TABLE "user_wallet" ADD CONSTRAINT user_wallet_wallet_id_fkey FOREIGN KEY (wallet_id) REFERENCES wallet(id);
