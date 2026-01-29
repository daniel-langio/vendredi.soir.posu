CREATE TABLE IF NOT EXISTS "user" (
    id VARCHAR PRIMARY KEY DEFAULT uuid_generate_v4()::varchar,
    username VARCHAR UNIQUE NOT NULL,
    email VARCHAR UNIQUE NOT NULL,
    password VARCHAR NOT NULL,
    api_key VARCHAR UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS "user_wallet" (
    user_id VARCHAR REFERENCES "user"(id),
    wallet_id VARCHAR REFERENCES "wallet"(id),
    PRIMARY KEY (user_id, wallet_id)
);
