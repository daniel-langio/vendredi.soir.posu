CREATE TABLE IF NOT EXISTS wallet(
    id varchar PRIMARY KEY default uuid_generate_v4(),
    name VARCHAR UNIQUE NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    type wallet_type NOT NULL DEFAULT 'CASH',
    reference VARCHAR
);

INSERT INTO wallet(name, reference) VALUES ('LIQUIDE', 'DEFAULT_LIQUIDE') ON CONFLICT (name) DO NOTHING;