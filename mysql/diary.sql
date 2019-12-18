drop database if exists diary;
create database if not exists diary;
use diary;

drop table if exists subject;
CREATE TABLE IF NOT EXISTS subject
(
   `id_subject` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   `name` VARCHAR (50) UNIQUE NOT NULL,
   `site` VARCHAR (100),
   `email` VARCHAR (50)
);

drop table if exists lesson;
CREATE TABLE IF NOT EXISTS lesson
(
   `id_lesson` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   `date` DATETIME NOT NULL,
   `location` VARCHAR (45),
   `duration` DOUBLE NOT NULL,
   type ENUM
   (
      'Lecture',
      'Practice'
   ),
   `id_subject` BIGINT NOT NULL,
	FOREIGN KEY (id_subject) REFERENCES subject (id_subject) ON DELETE CASCADE
);
drop table if exists homework;
CREATE TABLE IF NOT EXISTS homework
(
   `id_homework` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   `deadline` DATETIME NOT NULL,
   `description` VARCHAR (255) NOT NULL,
   `status` BOOLEAN DEFAULT FALSE,
   `id_subject` BIGINT NOT NULL,
   FOREIGN KEY (id_subject) REFERENCES subject (id_subject) ON DELETE CASCADE
);
drop table if exists exam;
CREATE TABLE IF NOT EXISTS exam
(
   `id_exam` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   `date` DATETIME NOT NULL,
   `location` VARCHAR (45),
   `id_subject` BIGINT NOT NULL,
   FOREIGN KEY (id_subject) REFERENCES subject (id_subject) ON DELETE CASCADE
);

-- Fill subject table
INSERT INTO diary.subject (`id_subject`, name, site, `email`) VALUES ('1', 'paz1a', 'https://paz1a.ics.upjs.sk/', 'juraj.sebej@gmail.com');
INSERT INTO diary.subject (`id_subject`, `name`) VALUES ('2', 'num');
INSERT INTO diary.subject (`id_subject`, name, site, `email`) VALUES ('3', 'sxm', 'https://lms.ics.upjs.sk/course/view.php?id=14', 'szabari@upjs.sk');
INSERT INTO diary.subject (`id_subject`, name, site, `email`) VALUES ('4', 'paz1c', 'https://ics.science.upjs.sk/paz1c/', 'peter.gursky@upjs.sk');
INSERT INTO diary.subject (`id_subject`, name, `email`) VALUES ('5', 'pfajka', 'viktoria.silna@student.upjs.sk');
INSERT INTO diary.subject (`id_subject`, name, `email`) VALUES ('6', 'pos', 'tomas.bajtos@student.upjs.sk');
INSERT INTO diary.subject (`id_subject`, name, `email`) VALUES ('7', 'pm', 'szabari@upjs.sk');
INSERT INTO diary.subject (`id_subject`, name, site, `email`) VALUES ('8', 'psin', 'https://siete.gursky.sk/', 'peter.gursky@upjs.sk');
INSERT INTO diary.subject (`id_subject`, `name`) VALUES ('9', 'prp');
INSERT INTO diary.subject (`id_subject`, name, site, `email`) VALUES ('10', 'paz1b', 'https://paz1b.ics.upjs.sk/', 'frantisek.galcik@upjs.sk');
INSERT INTO diary.subject (`id_subject`, `name`) VALUES ('11', 'wbn');
INSERT INTO diary.subject (`id_subject`, `name`) VALUES ('12', 'abap');
INSERT INTO diary.subject (`id_subject`, name, site, `email`) VALUES ('13', 'dbs1a', 'https://databazy.ics.upjs.sk/dbs1a/index.html', 'wiliam.kacala@student.upjs.sk');
INSERT INTO diary.subject (`id_subject`, name, site, `email`) VALUES ('14', 'dbs1b', 'https://databazy.ics.upjs.sk/dbs1b/index.html', 'wiliam.kacala@student.upjs.sk');
INSERT INTO diary.subject (`id_subject`, name, site, `email`) VALUES ('15', 'osy', 'https://ics.upjs.sk/~pisarcik/osy/', 'pisarcik@upjs.sk');

-- Fill exam table
INSERT INTO diary.exam (`id_exam`, date, location, `id_subject`) VALUES (1, '2019-12-16 9:00', 'SA1A1', 1);
INSERT INTO diary.exam (`id_exam`, date, location, `id_subject`) VALUES (2, '2019-12-17 10:00', 'AJAX13', 2);
INSERT INTO diary.exam (`id_exam`, date, location, `id_subject`) VALUES (3, '2019-12-18 9:20', '40', 3);
INSERT INTO diary.exam (`id_exam`, date, location, `id_subject`) VALUES (4, '2019-12-17 10:15', 'SJ1P01', 7);
INSERT INTO diary.exam (`id_exam`, date, location, `id_subject`) VALUES (5, '2019-12-19 14:20', 'SA1A1', 7);
INSERT INTO diary.exam (`id_exam`, date, location, `id_subject`) VALUES (6, '2019-12-18 11:00', 'SJ1P02', 2);
INSERT INTO diary.exam (`id_exam`, date, location, `id_subject`) VALUES (7, '2020-01-19 8:00', 'SJSP01', 10);
INSERT INTO diary.exam (`id_exam`, date, location, `id_subject`) VALUES (8, '2020-02-10 9:00', 'SJ1P03', 11);
INSERT INTO diary.exam (`id_exam`, date, location, `id_subject`) VALUES (9, '2020-02-29 13:00', 'SA1A1', 15);
INSERT INTO diary.exam (`id_exam`, date, location, `id_subject`) VALUES (10, '2020-01-30 8:30', 'SJ1P04',4);

-- Fill homework table

INSERT INTO diary.homework (`id_homework`, deadline, description, `id_subject`) VALUES (1, '2019-12-25 23:00', 'description of homework',2);
INSERT INTO diary.homework (`id_homework`, deadline, description, `id_subject`) VALUES (2, '2019-12-23 17:05', 'second one', 2);
INSERT INTO diary.homework (`id_homework`, deadline, description, `id_subject`) VALUES (3, '2019-12-14 21:00', 'hw', 1);
INSERT INTO diary.homework (`id_homework`, deadline, description, `id_subject`) VALUES (4, '2019-12-6 00:00', 'pazpazpaz1c', 4);
INSERT INTO diary.homework (`id_homework`, deadline, description, `id_subject`) VALUES (5, '2020-1-11 23:59', 'some description', 3);
INSERT INTO diary.homework (`id_homework`, deadline, description, `id_subject`) VALUES (6, '2020-01-1 23:59', 'strc prst skrz krk', 3);
INSERT INTO diary.homework (`id_homework`, deadline, description, `id_subject`) VALUES (7, '2020-1-2 00:00', 'cosi tak toto je uzasna databaza', 3);
INSERT INTO diary.homework (`id_homework`, deadline, description, `id_subject`) VALUES (8, '2020-2-14 0:15', 'pan boh prosim chcem A', 3);

-- Fill lesson table

INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (1, '2019-12-16 14:00', 'SJSP1', '240', 'lecture', 1);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (2, '2019-12-16 7:00', 'SKUPJS', '120', 'practice', 1);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (3, '2019-12-16 8:00', 'UAONPU', '45', 'practice', 2);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (4, '2019-12-16 9:00', 'RUMFTI', '180', 'lecture', 2);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (5, '2019-12-16 10:00', 'MSKMGU', '180', 'lecture', 3);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (6, '2019-12-16 11:00', 'MSMK', '100', 'practice', 3);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (7, '2019-12-17 12:00', 'KMSMK', '300', 'lecture', 4);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (8, '2019-12-17 13:00', 'SKRF', '60', 'practice', 4);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (9, '2019-12-17 14:00', 'AAAA', '60', 'lecture', 4);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (10, '2019-12-17 15:00', 'BBBB', '60', 'practice', 3);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (11, '2019-12-17 16:00', 'CCCC', '60', 'lecture', 4);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (12, '2019-12-17 17:00', 'DDDD', '60', 'practice', 1);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (13, '2019-12-18 7:30', 'EEEE', '60', 'lecture', 2);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (14, '2019-12-18 8:30', 'FFFF', '65', 'practice', 1);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (15, '2019-12-18 9:30', 'GGGG', '60', 'lecture', 5);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (16, '2019-12-18 10:30', 'HHHH', '60', 'practice', 2);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (17, '2019-12-18 11:30', 'KKKK', '60', 'lecture', 6);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (18, '2019-12-19 12:30', 'LLLL', '60', 'practice', 2);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (19, '2019-12-19 13:30', 'MMMM', '60', 'lecture', 7);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (20, '2019-12-19 14:30', 'NNNN', '60', 'practice', 11);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (21, '2019-12-19 15:30', 'OOOO', '60', 'lecture', 12);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (22, '2019-12-19 16:30', 'PPPP', '60', 'practice', 4);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (23, '2019-12-20 17:30', 'RRRR', '60', 'lecture', 3);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (24, '2019-12-20 7:05', 'SSSS', '60', 'practice', 1);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (25, '2019-12-20 8:05', 'TTTT', '60', 'lecture', 15);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (26, '2019-12-20 9:05', 'UUUU', '60', 'practice', 14);
INSERT INTO diary.lesson (`id_lesson`, date, location, duration, type, `id_subject`) VALUES (27, '2019-12-20 10:05', 'HHHH', '60', 'lecture', 11);

-- ==================
SELECT * FROM `exam`;
SELECT * FROM `homework`;
SELECT * FROM `lesson`;
SELECT * FROM `subject`;

SET sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));SELECT * FROM lesson WHERE id_subject = 2 GROUP BY DAYOFWEEK(date) ORDER BY date;