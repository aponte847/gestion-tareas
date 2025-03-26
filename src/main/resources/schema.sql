CREATE TABLE task_status (
                               id BIGINT PRIMARY KEY,
                               name VARCHAR(255) NOT NULL
);

CREATE TABLE users (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          username VARCHAR(100) NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          role VARCHAR(255) NOT NULL
);

CREATE TABLE tasks (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        title VARCHAR(255) NOT NULL,
                        description VARCHAR(500),
                        status_id BIGINT NOT NULL,
                        user_id BIGINT NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP,

                        CONSTRAINT fk_estado FOREIGN KEY (status_id)
                            REFERENCES task_status(id) ON DELETE RESTRICT,

                        CONSTRAINT fk_usuario FOREIGN KEY (user_id)
                            REFERENCES users(id) ON DELETE CASCADE
);
