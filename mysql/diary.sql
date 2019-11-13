drop database if exists diary;
create database if not exists diary;
use diary;

drop table if exists `subject`;
CREATE TABLE IF NOT EXISTS `subject`
(
   `id_subject` BIGINT NOT NULL AUTO_INCREMENT,
   `name` VARCHAR (50) NOT NULL,
   `site` VARCHAR (100),
   `email` VARCHAR (50),
   PRIMARY KEY
   (
      `id_subject`,
      `name`
   )
);

drop table if exists `lesson`;
CREATE TABLE IF NOT EXISTS `lesson`
(
   `id_lesson` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   `date` DATETIME NOT NULL,
   `location` VARCHAR (45),
   `duration` DOUBLE NOT NULL,
   `type` ENUM
   (
      'Lecture',
      'Practice'
   ),
   `id_subject` BIGINT NOT NULL
);
ALTER TABLE lesson ADD CONSTRAINT fk_lesson_subject FOREIGN KEY (id_subject) REFERENCES subject (id_subject);
drop table if exists `homework`;
CREATE TABLE IF NOT EXISTS `homework`
(
   `id_homework` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   `deadline` DATETIME NOT NULL,
   `description` VARCHAR (255),
   `id_subject` BIGINT NOT NULL
);
ALTER TABLE homework ADD CONSTRAINT fk_homework_subject FOREIGN KEY (id_subject) REFERENCES subject (id_subject);
drop table if exists `exam`;
CREATE TABLE IF NOT EXISTS `exam`
(
   `id_exam` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   `date` DATETIME NOT NULL,
   `location` VARCHAR (45),
   `id_subject` BIGINT NOT NULL
);
ALTER TABLE exam ADD CONSTRAINT fk_exam_subject FOREIGN KEY (id_subject) REFERENCES subject (id_subject);

-- Fill subject table
INSERT INTO `diary`.`subject` (`id_subject`, `name`, `site`, `email`) VALUES ('1', 'paz1a', 'https://paz1a.ics.upjs.sk/', 'juraj.sebej@gmail.com');
INSERT INTO `diary`.`subject` (`id_subject`, `name`) VALUES ('2', 'num');
INSERT INTO `diary`.`subject` (`id_subject`, `name`, `site`, `email`) VALUES ('3', 'sxm', 'https://lms.ics.upjs.sk/course/view.php?id=14', 'szabari@upjs.sk');
INSERT INTO `diary`.`subject` (`id_subject`, `name`, `site`, `email`) VALUES ('4', 'paz1c', 'https://ics.science.upjs.sk/paz1c/', 'peter.gursky@upjs.sk');

-- Fill exam table
INSERT INTO `diary`.`exam` (`id_exam`, `date`, `location`, `id_subject`) VALUES ('1', '2001-04-13', 'SA1A1', '3');
INSERT INTO `diary`.`exam` (`id_exam`, `date`, `location`, `id_subject`) VALUES ('2', '2001-09-13', 'SJ1P00', '2');

-- Fill homework table
INSERT INTO `diary`.`homework` (`id_homework`, `deadline`, `description`, `id_subject`) VALUES ('1', '2020-11-8', 'description', '2');
INSERT INTO `diary`.`homework` (`id_homework`, `deadline`, `description`, `id_subject`) VALUES ('2', '2020-01-01', 'second one', '2');
INSERT INTO `diary`.`homework` (`id_homework`, `deadline`, `id_subject`) VALUES ('3', '2019-5-14', '1');
INSERT INTO `diary`.`homework` (`id_homework`, `deadline`, `id_subject`) VALUES ('4', '2019-5-6', '4');
INSERT INTO `diary`.`homework` (`id_homework`, `deadline`, `description`, `id_subject`) VALUES ('5', '2019-12-12', 'some description', '3');

-- Fill lesson table
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('1', '2019-10-11', 'SJSP1', '240', 'lecture', '1');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('2', '2020-12-11', 'SKUPJS', '120', 'practice', '1');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('3', '2018-12-11', 'UAONPU', '45', 'practice', '2');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('4', '2015-5-11', 'RUMFTI', '180', 'lecture', '2');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('5', '2019-12-30', 'MSKMGU', '180', 'lecture', '3');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('6', '2019-12-2', 'MSMK', '100', 'practice', '3');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('7', '2019-13-11', 'KMSMK', '300', 'practice', '4');

-- ==================
SELECT *
FROM
   `exam`;
SELECT *
FROM
   `homework`;
SELECT *
FROM
   `lesson`;
SELECT *
FROM
   `subject`;
   
select * from subject s left join homework h on s.id_subject=h.id_subject left join exam e on s.id_subject=e.id_subject;