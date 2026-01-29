ALTER TABLE "transaction" DROP COLUMN IF EXISTS "category_id";
ALTER TABLE "transaction" DROP COLUMN IF EXISTS "sub_category";

DROP TABLE IF EXISTS "transaction_sub_category";
DROP TABLE IF EXISTS "transaction_category";
