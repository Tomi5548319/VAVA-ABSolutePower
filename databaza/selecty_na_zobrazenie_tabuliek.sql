SELECT * FROM users
SELECT * FROM account_types
SELECT users.id, account_types.type AS account_type, users.login, users.password FROM users
JOIN account_types ON account_types.id = users.account_type