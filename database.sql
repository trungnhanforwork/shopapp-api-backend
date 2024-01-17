CREATE DATABASE shopapp;
USE shopapp;

CREATE TABLE users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  fullname VARCHAR(100) DEFAULT '',
  phone_number VARCHAR(10) NOT NULL,
  address VARCHAR(200) DEFAULT '',
  password VARCHAR(100) NOT NULL DEFAULT '', 
  created_at DATETIME,
  updated_at DATETIME,
  is_active TINYINT DEFAULT 1,
  date_of_birth DATE,
  facebook_account_id INT DEFAULT 0,
  google_account_id INT DEFAULT 0
);

CREATE TABLE tokens(
  id INT PRIMARY KEY AUTO_INCREMENT,
  token VARCHAR(255) UNIQUE NOT NULL,
  token_type VARCHAR(50) NOT NULL,
  expiration_date DATETIME,
  revoked TINYINT(1) NOT NULL,
  expired TINYINT(1) NOT NULL,
  user_id int,
  FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE social_account(
  id INT PRIMARY KEY AUTO_INCREMENT,
  provider VARCHAR(20) NOT NULL COMMENT 'Name of provider'
  provider_id VARCHAR(50) NOT NULL,
  email VARCHAR(150) NOT NULL,
  name VARCHAR(100) NOT NULL,
  user_id INT,
  FOREIGN KEY user_id REFERENCES users(id)
  
);

CREATE TABLE roles(
    id INT PRIMARY KEY,
    name VARCHAR(20) NOT NULL 
);

CREATE TABLE categories(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(100) NOT NULL DEFAULT '' COMMENT 'Tên danh mục'
);

CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(350) COMMENT 'Tên sản phẩm',
    price FLOAT NOT NULL CHECK (price >= 0),
    thumbnail VARCHAR(300) DEFAULT '',
    description LONGTEXT DEFAULT '',
    created_at DATETIME,
    updated_at DATETIME,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE orders(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id int,
    FOREIGN KEY (user_id) REFERENCES users(id),
    fullname VARCHAR(100) DEFAULT '',
    email VARCHAR(100) DEFAULT '',
    phone_number VARCHAR(20) NOT NULL,
    address VARCHAR(200) NOT NULL,
    note VARCHAR(100) DEFAULT '',
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20),
    total_money FLOAT CHECK(total_money >= 0)
);
