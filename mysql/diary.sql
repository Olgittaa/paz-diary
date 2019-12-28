drop database if exists `diary`;
create database if not exists `diary`;
use `diary`;

drop table if exists `subject`;
CREATE TABLE IF NOT EXISTS `subject` (
    `id_subject` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) UNIQUE NOT NULL,
    `site` VARCHAR(100),
    `email` VARCHAR(50)
);

drop table if exists `lesson`;
CREATE TABLE IF NOT EXISTS `lesson` (
    `id_lesson` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `start_time` TIME NOT NULL,
    `day_of_week` ENUM('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY') NOT NULL,
    `till_date` DATETIME NOT NULL,
    `location` VARCHAR(45),
    `duration` DOUBLE NOT NULL,
    `type` ENUM('Lecture', 'Practice'),
    `id_subject` BIGINT NOT NULL,
    FOREIGN KEY (id_subject)
        REFERENCES subject (id_subject)
        ON DELETE CASCADE
);
drop table if exists `homework`;
CREATE TABLE IF NOT EXISTS `homework` (
    `id_homework` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `deadline` DATETIME NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `status` BOOLEAN DEFAULT FALSE,
    `id_subject` BIGINT NOT NULL,
    FOREIGN KEY (id_subject)
        REFERENCES subject (id_subject)
        ON DELETE CASCADE
);
drop table if exists `exam`;
CREATE TABLE IF NOT EXISTS `exam` (
    `id_exam` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `date` DATETIME NOT NULL,
    `location` VARCHAR(45),
    `id_subject` BIGINT NOT NULL,
    FOREIGN KEY (id_subject)
        REFERENCES subject (id_subject)
        ON DELETE CASCADE
);

-- Fill subject table

-- Fill exam table

-- Fill homework table

-- Fill lesson table

-- ==================
SELECT * FROM `exam`;
SELECT * FROM `homework`;
SELECT * FROM `lesson`;
SELECT * FROM `subject`;
