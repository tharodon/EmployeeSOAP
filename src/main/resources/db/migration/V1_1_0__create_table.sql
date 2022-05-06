CREATE TABLE IF NOT EXISTS employee
(
    id          BIGSERIAL PRIMARY KEY,
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
    employee_id BIGINT,
    description TEXT      NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee (id)
);

CREATE TABLE IF NOT EXISTS distribute
(
    id          BIGSERIAL NOT NULL PRIMARY KEY,
    task_id     BIGINT    NOT NULL,
    employee_id BIGINT    NOT NULL,
    FOREIGN KEY (task_id) REFERENCES task (uid),
    FOREIGN KEY (employee_id) REFERENCES employee (id)
);