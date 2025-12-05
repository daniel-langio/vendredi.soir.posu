INSERT INTO transaction_sub_category (id, name, category_id, description)
VALUES
    -- IN : MAIN
    ('DEFAULT_MAIN_SALARY', 'Salary', 'DEFAULT_MAIN_INCOME', 'Primary salary or main income source'),
    ('DEFAULT_MAIN_BONUS', 'Bonus', 'DEFAULT_MAIN_INCOME', 'Bonus or exceptional primary income'),

    -- IN : SECONDARY
    ('DEFAULT_SECONDARY_SIDEJOB', 'Side Job', 'DEFAULT_SECONDARY_INCOME', 'Freelance / part-time / side job'),
    ('DEFAULT_SECONDARY_RENTAL', 'Rental Income', 'DEFAULT_SECONDARY_INCOME', 'Income from rent or property'),
    ('DEFAULT_SECONDARY_INVEST', 'Investment Income', 'DEFAULT_SECONDARY_INCOME', 'Dividends or returns'),

    -- IN : OTHER
    ('DEFAULT_OTHER_GIFT', 'Gift', 'DEFAULT_OTHER_INCOME', 'Financial gifts received'),
    ('DEFAULT_OTHER_MISC', 'Miscellaneous', 'DEFAULT_OTHER_INCOME', 'Other income not categorized'),

    -- OUT : LIVING
    ('DEFAULT_LIVING_FOOD', 'Food', 'DEFAULT_LIVING_EXPENSES', 'Groceries, snacks, restaurants'),
    ('DEFAULT_LIVING_TRANSPORT', 'Transport', 'DEFAULT_LIVING_EXPENSES', 'Daily transportation costs'),
    ('DEFAULT_LIVING_RENT', 'Rent', 'DEFAULT_LIVING_EXPENSES', 'Housing or rent expenses'),

    -- OUT : PERSONAL
    ('DEFAULT_PERSONAL_CLOTHES', 'Clothes', 'DEFAULT_PERSONAL_EXPENSES', 'Clothing and accessories'),
    ('DEFAULT_PERSONAL_HYGIENE', 'Hygiene', 'DEFAULT_PERSONAL_EXPENSES', 'Personal care and hygiene'),
    ('DEFAULT_PERSONAL_FUN', 'Entertainment', 'DEFAULT_PERSONAL_EXPENSES', 'Movies, games, hobbies'),

    -- OUT : PROFESSIONAL
    ('DEFAULT_PROFESSIONAL_TOOLS', 'Tools', 'DEFAULT_PROFESSIONAL_EXPENSES', 'Work tools or equipment'),
    ('DEFAULT_PROFESSIONAL_TRAINING', 'Training', 'DEFAULT_PROFESSIONAL_EXPENSES', 'Courses or professional training'),
    ('DEFAULT_PROFESSIONAL_TRANSPORT', 'Work Transport', 'DEFAULT_PROFESSIONAL_EXPENSES', 'Work-related travel'),

    -- OUT : FINANCIAL
    ('DEFAULT_FINANCIAL_BANK_FEES', 'Bank Fees', 'DEFAULT_FINANCIAL_EXPENSES', 'Bank or transfer fees'),

    -- Loans (corrected and expanded as you asked)
    ('DEFAULT_FINANCIAL_LOAN_GIVEN', 'Loan Given', 'DEFAULT_FINANCIAL_EXPENSES', 'Money loaned to someone'),
    ('DEFAULT_FINANCIAL_LOAN_REPAYMENT_RECEIVED', 'Loan Repayment Received', 'DEFAULT_FINANCIAL_INCOME', 'Money repaid to you for a loan you gave'),
    ('DEFAULT_FINANCIAL_LOAN_TAKEN', 'Loan Taken', 'DEFAULT_FINANCIAL_INCOME', 'Money you borrowed from someone'),
    ('DEFAULT_FINANCIAL_LOAN_REPAYMENT', 'Loan Repayment', 'DEFAULT_FINANCIAL_EXPENSES', 'Money you repay for a loan you took'),

    -- Charity (OUT)
    ('DEFAULT_EXCEPTIONAL_CHARITY', 'Charity', 'DEFAULT_EXCEPTIONAL_EXPENSES', 'Donations and charity'),

    -- OUT : EXCEPTIONAL
    ('DEFAULT_EXCEPTIONAL_HEALTH', 'Health Emergency', 'DEFAULT_EXCEPTIONAL_EXPENSES', 'Unexpected medical expenses'),
    ('DEFAULT_EXCEPTIONAL_REPAIR', 'Repair', 'DEFAULT_EXCEPTIONAL_EXPENSES', 'Major repairs or fixes'),
    ('DEFAULT_EXCEPTIONAL_EVENT', 'Special Event', 'DEFAULT_EXCEPTIONAL_EXPENSES', 'Ceremonies or special events'),

    -- OUT : MONEY TRANSFER
    ('DEFAULT_OUT_TRANSFER_SENDING', 'Sending Money', 'DEFAULT_OUT_MONEY_TRANSFER', 'Sending funds to someone'),

    -- IN : MONEY TRANSFER
    ('DEFAULT_IN_TRANSFER_RECEIVING', 'Receiving Money', 'DEFAULT_IN_MONEY_TRANSFER', 'Receiving funds from someone')

ON CONFLICT (id) DO NOTHING;
