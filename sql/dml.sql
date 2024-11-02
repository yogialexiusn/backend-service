INSERT INTO t_user (username, account_status) VALUES
('yogi', 'ACTIVE'),
('alexius', 'ACTIVE'),
('naibaho', 'INACTIVE');

INSERT INTO t_role (role_name , permissions) VALUES
('SUPERADMIN', ARRAY['READ', 'WRITE','DELETE']),
('VIEWER', ARRAY['READ', 'WRITE']),
('ADMIN', ARRAY['READ']);

INSERT INTO t_access (id, username, menu_access, role_name) VALUES
(1, 'yogi', 'NEWS', 'SUPERADMIN'),
(2, 'yogi', 'CAREER', 'SUPERADMIN'),
(3, 'alexius', 'NEWS', 'ADMIN'),
(4, 'alexius', 'CAREER', 'ADMIN'),
(5, 'naibaho', 'NEWS', 'VIEWER');

select * from t_user tu
join t_access ta on tu.username = ta.username;

INSERT INTO t_user (username, account_status) VALUES
('widodo', 'ACTIVE');