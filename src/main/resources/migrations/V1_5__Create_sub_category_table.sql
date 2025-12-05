CREATE TABLE IF NOT EXISTS transaction_sub_category (
    id VARCHAR PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    category_id VARCHAR REFERENCES transaction_category(id),
    description VARCHAR
);
