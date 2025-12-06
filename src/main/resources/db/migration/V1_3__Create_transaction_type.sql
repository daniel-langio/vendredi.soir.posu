DROP TYPE IF EXISTS transaction_type;
CREATE TYPE transaction_type AS ENUM ('IN', 'OUT');