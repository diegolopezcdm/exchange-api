-- The encrypted client_secret it `secret`
INSERT INTO oauth_client_details (client_id, client_secret, scope, authorized_grant_types, authorities, access_token_validity)
  VALUES ('exchange-api-read', '{bcrypt}$2a$10$vCXMWCn7fDZWOcLnIEhmK.74dvK1Eh8ae2WrWlhr2ETPLoxQctN4.', 'read', 'password,refresh_token,client_credentials', 'ROLE_CLIENT', 300);

INSERT INTO oauth_client_details (client_id, client_secret, scope, authorized_grant_types, authorities, access_token_validity)
  VALUES ('exchange-api-read-write', '{bcrypt}$2a$10$vCXMWCn7fDZWOcLnIEhmK.74dvK1Eh8ae2WrWlhr2ETPLoxQctN4.', 'read,write', 'password,refresh_token,client_credentials', 'ROLE_CLIENT', 300);

-- The encrypted password is `pass` 
INSERT INTO users (id, username, password, enabled) VALUES (2, 'mikaela', '{bcrypt}$2a$10$cyf5NfobcruKQ8XGjUJkEegr9ZWFqaea6vjpXWEaSqTa2xL9wjgQC', '1');
INSERT INTO users (id, username, password, enabled) VALUES (3, 'diegol', '{bcrypt}$2a$10$cyf5NfobcruKQ8XGjUJkEegr9ZWFqaea6vjpXWEaSqTa2xL9wjgQC', '1');

INSERT INTO AUTHORITY(ID, NAME) VALUES (1, 'ROLE_ADMIN');
INSERT INTO AUTHORITY(ID, NAME) VALUES (2, 'ROLE_CLIENT');
INSERT INTO AUTHORITY(ID, NAME) VALUES (3, 'CALCULATOR_READ');
INSERT INTO AUTHORITY(ID, NAME) VALUES (4, 'CALCULATOR_WRITE');
INSERT INTO AUTHORITY(ID, NAME) VALUES (5, 'USER_READ');
INSERT INTO AUTHORITY(ID, NAME) VALUES (6, 'USER_WRITE');

INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (3, 1);
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (3, 3);
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (3, 4);
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (3, 5);
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (3, 6);
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (2, 2);