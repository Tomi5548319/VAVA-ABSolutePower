SELECT * FROM users

SELECT * FROM account_types

SELECT users.id, account_types.type AS account_type, users.login, users.password FROM users
JOIN account_types ON account_types.id = users.account_type

SELECT u1.login, COALESCE(u2.login, u1.login) AS password, u1.language_id, languages.language
FROM users AS u1
LEFT JOIN users AS u2 ON u2.id = u1.id - 4
JOIN languages ON u1.language_id = languages.id