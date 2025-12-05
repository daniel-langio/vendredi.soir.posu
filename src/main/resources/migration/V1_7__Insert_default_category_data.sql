INSERT INTO category (id, transaction_type, name, reference, description)
VALUES
    ('DEFAULT_MAIN_INCOME', 'IN', 'MAIN', 'DEFAULT_MAIN_INCOME', 'Main income'),
    ('DEFAULT_SECONDARY_INCOME', 'IN', 'SECONDARY', 'DEFAULT_SECONDARY_INCOME', 'Secondary income'),
    ('DEFAULT_OTHER_INCOME', 'IN', 'OTHER', 'DEFAULT_OTHER_INCOME', 'Other income'),
    ('DEFAULT_LIVING_EXPENSES', 'OUT', 'LIVING', 'DEFAULT_LIVING_EXPENSES', 'Every day''s expenses'),
    ('DEFAULT_PERSONAL_EXPENSES', 'OUT', 'Personal', 'DEFAULT_PERSONAL_EXPENSES', 'Personal expenses'),
    ('DEFAULT_PROFESSIONAL_EXPENSES', 'OUT', 'Professional', 'DEFAULT_PROFESSIONAL_EXPENSES', 'Business or work related expenses'),
    ('DEFAULT_FINANCIAL_EXPENSES', 'OUT', 'Financial', 'DEFAULT_FINANCIAL_EXPENSES', 'Financial expenses'),
    ('DEFAULT_EXCEPTIONAL_EXPENSES', 'OUT', 'Exceptional', 'DEFAULT_EXCEPTIONAL_EXPENSES', 'Exceptional expenses'),
    ('DEFAULT_OUT_MONEY_TRANSFER', 'OUT', 'Money Transfer', 'DEFAULT_OUT_MONEY_TRANSFER', 'Out for Money Transfer'),
    ('DEFAULT_IN_MONEY_TRANSFER', 'IN', 'Money Transfer', 'DEFAULT_IN_MONEY_TRANSFER', 'In from Money Transfer')
ON CONFLICT (transaction_type, name) DO NOTHING;
