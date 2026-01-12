ALTER TABLE "transaction" DROP COLUMN "category_id";
ALTER TABLE "transaction" DROP COLUMN "sub_category";

DROP TABLE IF EXISTS "transaction_sub_category";
DROP TABLE IF EXISTS "transaction_category";
