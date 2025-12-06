INSERT INTO transaction_sub_category (name, category_id, description)
VALUES
    -- IN : MAIN
    ('Salary', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_MAIN_INCOME'), 'Primary salary or main income source'),
    ('Bonus', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_MAIN_INCOME'), 'Bonus or exceptional primary income'),

    -- IN : SECONDARY
    ('Side Job', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_SECONDARY_INCOME'), 'Freelance / part-time / side job'),
    ('Rental Income', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_SECONDARY_INCOME'), 'Income from rent or property'),
    ('Investment Income', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_SECONDARY_INCOME'), 'Dividends or returns'),

    -- IN : OTHER
    ('Gift', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_OTHER_INCOME'), 'Financial gifts received'),
    ('Miscellaneous', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_OTHER_INCOME'), 'Other income not categorized'),

    -- OUT : LIVING
    ('Food', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_LIVING_EXPENSES'), 'Groceries, snacks, restaurants'),
    ('Transport', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_LIVING_EXPENSES'), 'Daily transportation costs'),
    ('Rent', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_LIVING_EXPENSES'), 'Housing or rent expenses'),

    -- OUT : PERSONAL
    ('Clothes', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_PERSONAL_EXPENSES'), 'Clothing and accessories'),
    ('Hygiene', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_PERSONAL_EXPENSES'), 'Personal care and hygiene'),
    ('Entertainment', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_PERSONAL_EXPENSES'), 'Movies, games, hobbies'),

    -- OUT : PROFESSIONAL
    ('Tools', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_PROFESSIONAL_EXPENSES'), 'Work tools or equipment'),
    ('Training', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_PROFESSIONAL_EXPENSES'), 'Courses or professional training'),
    ('Work Transport', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_PROFESSIONAL_EXPENSES'), 'Work-related travel'),

    -- OUT : FINANCIAL
    ('Bank Fees', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_FINANCIAL_EXPENSES'), 'Bank or transfer fees'),
    ('Loan Given', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_FINANCIAL_EXPENSES'), 'Money loaned to someone'),
    ('Loan Repayment', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_FINANCIAL_EXPENSES'), 'Money you repay for a loan you took'),

    -- Charity (OUT)
    ('Charity', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_EXCEPTIONAL_EXPENSES'), 'Donations and charity'),

    -- OUT : EXCEPTIONAL
    ('Health Emergency', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_EXCEPTIONAL_EXPENSES'), 'Unexpected medical expenses'),
    ('Repair', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_EXCEPTIONAL_EXPENSES'), 'Major repairs or fixes'),
    ('Special Event', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_EXCEPTIONAL_EXPENSES'), 'Ceremonies or special events'),

    -- OUT : MONEY TRANSFER
    ('Sending Money', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_OUT_MONEY_TRANSFER'), 'Sending funds to someone'),

    -- IN : MONEY TRANSFER
    ('Receiving Money', (SELECT id FROM transaction_category WHERE reference = 'DEFAULT_IN_MONEY_TRANSFER'), 'Receiving funds from someone')
ON CONFLICT (category_id, name) DO NOTHING;
