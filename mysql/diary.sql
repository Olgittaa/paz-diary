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
   `description` VARCHAR (255) NOT NULL,
   `status` BOOLEAN DEFAULT FALSE,
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
INSERT INTO `diary`.`homework` (`id_homework`, `deadline`, `description`, `status`, `id_subject`) VALUES ('1', '2019-11-8', 'description', TRUE ,'2');
INSERT INTO `diary`.`homework` (`id_homework`, `deadline`, `description`, `status`, `id_subject`) VALUES ('2', '2020-01-01', 'second one', TRUE,'2');
INSERT INTO `diary`.`homework` (`id_homework`, `deadline`, `description`, `id_subject`) VALUES ('3', '2019-12-14', 'hw', '1');
INSERT INTO `diary`.`homework` (`id_homework`, `deadline`, `description`, `status`, `id_subject`) VALUES ('4', '2019-12-6', 'pazpazpaz1c', TRUE, '4');
INSERT INTO `diary`.`homework` (`id_homework`, `deadline`, `description`, `id_subject`) VALUES ('5', '2019-11-13', 'some description', '3');

-- Fill lesson table
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('1', '2019-10-11', 'SJSP1', '240', 'lecture', '1');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('2', '2020-12-11', 'SKUPJS', '120', 'practice', '1');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('3', '2018-12-11', 'UAONPU', '45', 'practice', '2');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('4', '2015-5-11', 'RUMFTI', '180', 'lecture', '2');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('5', '2019-12-30', 'MSKMGU', '180', 'lecture', '3');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('6', '2019-12-2', 'MSMK', '100', 'practice', '3');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('7', '2019-12-11', 'KMSMK', '300', 'lecture', '4');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('8', '2019-11-18', 'SKRF', '60', 'practice', '4');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('23', '2019-11-9', 'AAAA', '60', 'practice', '4');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('24', '2019-11-10', 'BBBB', '60', 'practice', '3');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('25', '2019-11-11', 'CCCC', '60', 'practice', '4');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('17', '2019-11-12', 'DDDD', '60', 'practice', '1');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('18', '2019-11-12', 'EEEE', '60', 'practice', '2');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('19', '2019-11-12', 'FFFF', '65', 'practice', '1');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('20', '2019-11-15', 'GGGG', '60', 'practice', '1');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('21', '2019-11-19', 'HHHH', '60', 'practice', '2');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('22', '2019-11-19', 'KKKK', '60', 'practice', '3');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('9', '2019-11-19', 'LLLL', '60', 'practice', '2');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('10', '2019-11-20', 'MMMM', '60', 'practice', '4');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('11', '2019-11-21', 'NNNN', '60', 'practice', '1');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('12', '2019-11-22', 'OOOO', '60', 'practice', '2');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('13', '2019-11-22', 'PPPP', '60', 'practice', '4');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('14', '2019-11-24', 'RRRR', '60', 'practice', '3');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('15', '2019-11-26', 'SSSS', '60', 'practice', '1');
INSERT INTO `diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES ('16', '2019-11-26', 'TTTT', '60', 'practice', '4');


-- ==================
SELECT * FROM `exam`;
SELECT * FROM `homework`;
SELECT * FROM `lesson`;
SELECT * FROM `subject`;