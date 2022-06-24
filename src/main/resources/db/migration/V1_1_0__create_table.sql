CREATE TABLE IF NOT EXISTS employee
(
    id          BIGSERIAL UNIQUE PRIMARY KEY,
    uid         VARCHAR(50) UNIQUE,
    name        VARCHAR(50) NOT NULL,
    surname     VARCHAR(50) NOT NULL,
    position    VARCHAR(50) NOT NULL,
    salary      INT         NOT NULL,
    grade       VARCHAR(50),
    age         INT         NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS task
(
    uid         BIGSERIAL NOT NULL PRIMARY KEY,
    description TEXT      NOT NULL
);

CREATE TABLE IF NOT EXISTS distribute
(
    id          BIGSERIAL NOT NULL PRIMARY KEY,
    task_id     BIGINT    NOT NULL,
    employee_id BIGSERIAL NOT NULL,
    FOREIGN KEY (task_id) REFERENCES task (uid) on delete cascade,
    FOREIGN KEY (employee_id) REFERENCES employee (id) on delete cascade
);