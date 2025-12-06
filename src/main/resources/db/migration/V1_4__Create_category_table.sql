CREATE TABLE IF NOT EXISTS transaction_category (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR,
    reference VARCHAR,
    description VARCHAR,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    transaction_type transaction_type NOT NULL DEFAULT 'OUT',

    CONSTRAINT uq_transaction_category UNIQUE (transaction_type, name)
);