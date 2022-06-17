CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY UNIQUE NOT NULL,
    username VARCHAR(50) UNIQUE           NOT NULL,
    email    VARCHAR(50) UNIQUE           NOT NULL,
    password VARCHAR(255)                 NOT NULL
);

CREATE TABLE role
(
    id   BIGSERIAL UNIQUE   NOT NULL,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE user_roles
(
    id      serial primary key not null,
    user_id BIGSERIAL          NOT NULL,
    role_id BIGSERIAL          NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE
);

INSERT INTO role (name)
VALUES ('ROLE_USER');
