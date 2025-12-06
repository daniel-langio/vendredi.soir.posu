INSERT INTO transaction_category (transaction_type, name, reference, description)
VALUES
    ('IN', 'MAIN', 'DEFAULT_MAIN_INCOME', 'Main income'),
    ('IN', 'SECONDARY', 'DEFAULT_SECONDARY_INCOME', 'Secondary income'),
    ('IN', 'OTHER', 'DEFAULT_OTHER_INCOME', 'Other income'),
    ('OUT', 'LIVING', 'DEFAULT_LIVING_EXPENSES', 'Every day''s expenses'),
    ('OUT', 'Personal', 'DEFAULT_PERSONAL_EXPENSES', 'Personal expenses'),
    ('OUT', 'Professional', 'DEFAULT_PROFESSIONAL_EXPENSES', 'Business or work related expenses'),
    ('OUT', 'Financial', 'DEFAULT_FINANCIAL_EXPENSES', 'Financial expenses'),
    ('OUT', 'Exceptional', 'DEFAULT_EXCEPTIONAL_EXPENSES', 'Exceptional expenses'),
    ('OUT', 'Money Transfer', 'DEFAULT_OUT_MONEY_TRANSFER', 'Out for Money Transfer'),
    ('IN', 'Money Transfer', 'DEFAULT_IN_MONEY_TRANSFER', 'In from Money Transfer')
ON CONFLICT (transaction_type, name) DO NOTHING;
