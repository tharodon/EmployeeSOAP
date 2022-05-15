CREATE TABLE IF NOT EXISTS employee
(
    id          VARCHAR(50) UNIQUE PRIMARY KEY,
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
    id          BIGSERIAL   NOT NULL PRIMARY KEY,
    task_id     BIGINT      NOT NULL,
    employee_id VARCHAR(50) NOT NULL,
    FOREIGN KEY (task_id) REFERENCES task (uid),
    FOREIGN KEY (employee_id) REFERENCES employee (id)
);