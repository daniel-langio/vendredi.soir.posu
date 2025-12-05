CREATE TABLE IF NOT EXISTS transaction (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    date TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    recorded_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    wallet_id UUID REFERENCES wallet(id) ON DELETE CASCADE ,
    amount DECIMAL NOT NULL DEFAULT 0,
    type transaction_type NOT NULL DEFAULT 'OUT',
    category_id UUID REFERENCES transaction_category(id) ON DELETE SET NULL,
    sub_category UUID REFERENCES transaction_sub_category(id) ON DELETE SET NULL,
    description TEXT
)