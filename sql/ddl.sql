CREATE TYPE account_status_enum AS ENUM ('ACTIVE', 'INACTIVE');


CREATE TABLE t_user (
    username VARCHAR(50) NOT NULL unique PRIMARY KEY,
    account_status account_status_enum NOT null
);

CREATE TABLE t_role (
    role_name VARCHAR(15) NOT NULL unique PRIMARY KEY,
    permissions TEXT[] NOT NULL
);

CREATE TABLE t_access (
    id INT NOT NULL unique PRIMARY key,
    username VARCHAR(50) NOT NULL,
    menu_access VARCHAR(20) NOT NULL,
    role_name VARCHAR(15),
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (role_name) REFERENCES t_role(role_name) ON DELETE CASCADE,
    FOREIGN KEY (username) REFERENCES t_user(username) ON DELETE CASCADE
);
