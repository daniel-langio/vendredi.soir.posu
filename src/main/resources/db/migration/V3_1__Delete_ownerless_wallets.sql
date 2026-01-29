DELETE FROM wallet WHERE id NOT IN (SELECT wallet_id FROM user_wallet);
