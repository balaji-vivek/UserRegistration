CREATE TABLE password_reset_token (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    token VARCHAR(255) NOT NULL,
    user_id INT NOT NULL,
    expiry_date TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE users (
    user_id INT PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    email VARCHAR(200) NOT NULL,
    role CHAR(2) NOT NULL
);

ALTER TABLE user ADD COLUMN reset_token VARCHAR(255);