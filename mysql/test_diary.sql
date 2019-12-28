drop database if exists test_diary;
create database if not exists test_diary;
use test_diary;

drop table if exists `subject`;
CREATE TABLE IF NOT EXISTS `subject`
(
   `id_subject` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   `name` VARCHAR (50) UNIQUE NOT NULL,
   `site` VARCHAR (100),
   `email` VARCHAR (50)
);
drop table if exists `lesson`;
CREATE TABLE IF NOT EXISTS `lesson`
(
   `id_lesson` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   `from_date` DATETIME NOT NULL,
   `location` VARCHAR (45),
   `duration` DOUBLE NOT NULL,
   `type` ENUM
   (
      'Lecture',
      'Practice'
   ),
   `id_subject` BIGINT NOT NULL,
	`till_date` date not null,
	FOREIGN KEY (id_subject) REFERENCES subject (id_subject) ON DELETE CASCADE
);
drop table if exists `homework`;
CREATE TABLE IF NOT EXISTS `homework`
(
   `id_homework` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   `deadline` DATETIME NOT NULL,
   `description` VARCHAR (255) NOT NULL,
   `status` BOOLEAN DEFAULT FALSE,
   `id_subject` BIGINT NOT NULL,
   FOREIGN KEY (id_subject) REFERENCES subject (id_subject) ON DELETE CASCADE
);
drop table if exists `exam`;
CREATE TABLE IF NOT EXISTS `exam`
(
   `id_exam` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   `date` DATETIME NOT NULL,
   `location` VARCHAR (45),
   `id_subject` BIGINT NOT NULL,
   FOREIGN KEY (id_subject) REFERENCES subject (id_subject) ON DELETE CASCADE
);

-- Fill subject table
INSERT INTO `test_diary`.`subject` (`id_subject`, `name`, `site`, `email`) VALUES ('1', 'paz1a', 'https://paz1a.ics.upjs.sk/', 'juraj.sebej@gmail.com');
INSERT INTO `test_diary`.`subject` (`id_subject`, `name`) VALUES ('2', 'num');
INSERT INTO `test_diary`.`subject` (`id_subject`, `name`, `site`, `email`) VALUES ('3', 'sxm', 'https://lms.ics.upjs.sk/course/view.php?id=14', 'szabari@upjs.sk');
INSERT INTO `test_diary`.`subject` (`id_subject`, `name`, `site`, `email`) VALUES ('4', 'paz1c', 'https://ics.science.upjs.sk/paz1c/', 'peter.gursky@upjs.sk');
INSERT INTO `test_diary`.`subject` (`id_subject`, `name`, `email`) VALUES ('5', 'pfajka', 'viktoria.silna@student.upjs.sk');
INSERT INTO `test_diary`.`subject` (`id_subject`, `name`, `email`) VALUES ('6', 'pos', 'tomas.bajtos@student.upjs.sk');
INSERT INTO `test_diary`.`subject` (`id_subject`, `name`, `email`) VALUES ('7', 'pm', 'szabari@upjs.sk');
INSERT INTO `test_diary`.`subject` (`id_subject`, `name`, `site`, `email`) VALUES ('8', 'psin', 'https://siete.gursky.sk/', 'peter.gursky@upjs.sk');
INSERT INTO `test_diary`.`subject` (`id_subject`, `name`) VALUES ('9', 'prp');
INSERT INTO `test_diary`.`subject` (`id_subject`, `name`, `site`, `email`) VALUES ('10', 'paz1b', 'https://paz1b.ics.upjs.sk/', 'frantisek.galcik@upjs.sk');
INSERT INTO `test_diary`.`subject` (`id_subject`, `name`) VALUES ('11', 'wbn');
INSERT INTO `test_diary`.`subject` (`id_subject`, `name`) VALUES ('12', 'abap');
INSERT INTO `test_diary`.`subject` (`id_subject`, `name`, `site`, `email`) VALUES ('13', 'dbs1a', 'https://databazy.ics.upjs.sk/dbs1a/index.html', 'wiliam.kacala@student.upjs.sk');
INSERT INTO `test_diary`.`subject` (`id_subject`, `name`, `site`, `email`) VALUES ('14', 'dbs1b', 'https://databazy.ics.upjs.sk/dbs1b/index.html', 'wiliam.kacala@student.upjs.sk');
INSERT INTO `test_diary`.`subject` (`id_subject`, `name`, `site`, `email`) VALUES ('15', 'osy', 'https://ics.upjs.sk/~pisarcik/osy/', 'pisarcik@upjs.sk');

-- Fill exam table
INSERT INTO `test_diary`.`exam` (`id_exam`, `date`, `location`, `id_subject`) VALUES (1, '2019-11-4 9:00', 'SA1A1', 1);
INSERT INTO `test_diary`.`exam` (`id_exam`, `date`, `location`, `id_subject`) VALUES (2, '2019-11-11 10:00', 'AJAX13', 2);
INSERT INTO `test_diary`.`exam` (`id_exam`, `date`, `location`, `id_subject`) VALUES (3, '2019-12-12 9:20', '40', 3);
INSERT INTO `test_diary`.`exam` (`id_exam`, `date`, `location`, `id_subject`) VALUES (4, '2019-12-13 10:15', 'SJ1P01', 7);
INSERT INTO `test_diary`.`exam` (`id_exam`, `date`, `location`, `id_subject`) VALUES (5, '2019-12-19 14:20', 'SA1A1', 7);
INSERT INTO `test_diary`.`exam` (`id_exam`, `date`, `location`, `id_subject`) VALUES (6, '2019-12-20 11:00', 'SJ1P02', 2);
INSERT INTO `test_diary`.`exam` (`id_exam`, `date`, `location`, `id_subject`) VALUES (7, '2020-01-21 8:00', 'SJSP01', 10);
INSERT INTO `test_diary`.`exam` (`id_exam`, `date`, `location`, `id_subject`) VALUES (8, '2020-02-25 9:00', 'SJ1P03', 11);
INSERT INTO `test_diary`.`exam` (`id_exam`, `date`, `location`, `id_subject`) VALUES (9, '2020-02-29 13:00', 'SA1A1', 15);
INSERT INTO `test_diary`.`exam` (`id_exam`, `date`, `location`, `id_subject`) VALUES (10, '2020-01-30 8:30', 'SJ1P04',4);

-- Fill homework table
INSERT INTO `test_diary`.`homework` (`id_homework`, `deadline`, `description`, `id_subject`) VALUES (1, '2019-12-25 23:00', 'description of homework',2);
INSERT INTO `test_diary`.`homework` (`id_homework`, `deadline`, `description`, `id_subject`) VALUES (2, '2020-12-23 17:05', 'second one', 2);
INSERT INTO `test_diary`.`homework` (`id_homework`, `deadline`, `description`, `id_subject`) VALUES (3, '2019-12-14 21:00', 'hw', 1);
INSERT INTO `test_diary`.`homework` (`id_homework`, `deadline`, `description`, `id_subject`) VALUES (4, '2019-12-6 00:00', 'pazpazpaz1c', 4);
INSERT INTO `test_diary`.`homework` (`id_homework`, `deadline`, `description`, `id_subject`) VALUES (5, '2020-1-11 23:59', 'some description', 3);
INSERT INTO `test_diary`.`homework` (`id_homework`, `deadline`, `description`, `id_subject`) VALUES (6, '2020-01-1 23:59', 'strc prst skrz krk', 3);
INSERT INTO `test_diary`.`homework` (`id_homework`, `deadline`, `description`, `id_subject`) VALUES (7, '2020-1-2 00:00', 'cosi tak toto je uzasna databaza', 3);
INSERT INTO `test_diary`.`homework` (`id_homework`, `deadline`, `description`, `id_subject`) VALUES (8, '2020-2-14 0:15', 'pan boh prosim chcem A', 3);

-- Fill lesson table
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (1, '2019-10-11 14:00', 'SJSP1', '240', 'lecture', 1);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (2, '2020-12-11 7:00', 'SKUPJS', '120', 'practice', 1);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (3, '2018-12-11 8:00', 'UAONPU', '45', 'practice', 2);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (4, '2015-5-11 9:00', 'RUMFTI', '180', 'lecture', 2);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (5, '2019-12-30 10:00', 'MSKMGU', '180', 'lecture', 3);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (6, '2019-12-2 11:00', 'MSMK', '100', 'practice', 3);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (7, '2019-12-11 12:00', 'KMSMK', '300', 'lecture', 4);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (8, '2019-11-18 13:00', 'SKRF', '60', 'practice', 4);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (9, '2019-11-9 14:00', 'AAAA', '60', 'lecture', 4);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (10, '2019-11-10 15:00', 'BBBB', '60', 'practice', 3);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (11, '2019-11-11 16:00', 'CCCC', '60', 'lecture', 4);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (12, '2019-11-12 17:00', 'DDDD', '60', 'practice', 1);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (13, '2019-11-12 7:30', 'EEEE', '60', 'lecture', 2);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (14, '2019-11-12 8:30', 'FFFF', '65', 'practice', 1);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (15, '2019-11-15 9:30', 'GGGG', '60', 'lecture', 5);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (16, '2019-11-19 10:30', 'HHHH', '60', 'practice', 2);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (17, '2019-11-19 11:30', 'KKKK', '60', 'lecture', 6);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (18, '2019-11-19 12:30', 'LLLL', '60', 'practice', 2);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (19, '2019-11-20 13:30', 'MMMM', '60', 'lecture', 7);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (20, '2019-11-21 14:30', 'NNNN', '60', 'practice', 11);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (21, '2019-11-22 15:30', 'OOOO', '60', 'lecture', 12);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (22, '2019-11-22 16:30', 'PPPP', '60', 'practice', 4);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (23, '2019-11-24 17:30', 'RRRR', '60', 'lecture', 3);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (24, '2019-11-26 7:05', 'SSSS', '60', 'practice', 1);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (25, '2019-11-26 8:05', 'TTTT', '60', 'lecture', 15);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (26, '2019-11-20 9:05', 'UUUU', '60', 'practice', 14);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (27, '2019-11-21 10:05', 'HHHH', '60', 'lecture', 11);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (28, '2019-11-22 11:05', 'QQQQ', '60', 'practice', 10);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (29, '2019-11-22 12:05', 'WWWW', '60', 'lecture', 9);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (30, '2019-11-24 13:05', 'VVVV', '60', 'practice', 7);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (31, '2019-11-26 14:05', 'ZZZZ', '60', 'lecture', 8);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (32, '2019-11-26 8:05', 'MOMO', '60', 'practice', 6);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (33, '2019-11-26 7:05', 'AUD', '60', 'practice', 6);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (34, '2019-11-27 9:05', 'POI', '60', 'practice', 6);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (35, '2019-11-27 8:05', 'CCK', '60', 'practice', 6);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (36, '2019-11-27 7:05', 'WOI', '60', 'practice', 6);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (37, '2019-11-27 10:05', 'WAR', '60', 'practice', 6);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (38, '2019-11-25 11:05', 'MOR', '60', 'practice', 6);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (39, '2019-11-25 12:05', 'REC', '60', 'practice', 6);
INSERT INTO `test_diary`.`lesson` (`id_lesson`, `date`, `location`, `duration`, `type`, `id_subject`) VALUES (40, '2019-11-28 13:05', 'OBR', '60', 'practice', 6);


-- ==================
#SELECT * FROM `exam`;
#SELECT * FROM `homework`;
#SELECT * FROM `lesson`;
#SELECT * FROM `subject`;
