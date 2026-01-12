CREATE TABLE IF NOT EXISTS "label" (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    reference VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS "transaction_label" (
    transaction_id VARCHAR(255) REFERENCES "transaction"(id),
    label_id VARCHAR(255) REFERENCES "label"(id),
    PRIMARY KEY (transaction_id, label_id)
);
