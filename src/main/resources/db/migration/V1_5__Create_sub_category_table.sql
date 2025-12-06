CREATE TABLE IF NOT EXISTS transaction_sub_category (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    category_id UUID REFERENCES transaction_category(id),
    description VARCHAR,
    CONSTRAINT uq_sub_category UNIQUE (category_id, name)
);
