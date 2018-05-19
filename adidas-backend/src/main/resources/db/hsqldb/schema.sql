CREATE TABLE book (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  book_store_id BIGINT,
  name          VARCHAR(80),
  author        VARCHAR(80),
  price         DECIMAL(10,2),
  topic         VARCHAR(80),
  publish_date  DATE
);

CREATE TABLE book_store (
  id           BIGINT AUTO_INCREMENT PRIMARY KEY,
  name         VARCHAR(80),
  address      VARCHAR(80)
);

CREATE TABLE user (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  username      VARCHAR(80),
  password      VARCHAR(80)
);