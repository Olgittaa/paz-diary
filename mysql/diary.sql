INSERT INTO `diary`.`subject` (`id_subject`, `name`, `site`, `email`) VALUES ('1', 'paz1a', 'https://paz1a.ics.upjs.sk/', 'juraj.sebej@gmail.com');
INSERT INTO `diary`.`subject` (`id_subject`, `name`) VALUES ('2', 'num');
INSERT INTO `diary`.`subject` (`id_subject`, `name`, `site`, `email`) VALUES ('3', 'sxm', 'https://lms.ics.upjs.sk/course/view.php?id=14', 'szabari@upjs.sk');
INSERT INTO `diary`.`subject` (`id_subject`, `name`, `site`, `email`) VALUES ('4', 'paz1c', 'https://ics.science.upjs.sk/paz1c/', 'peter.gursky@upjs.sk');
INSERT INTO `diary`.`subject` (`id_subject`, `name`, `email`) VALUES ('5', 'pfajka', 'viktoria.silna@student.upjs.sk');
INSERT INTO `diary`.`subject` (`id_subject`, `name`, `email`) VALUES ('6', 'pos', 'tomas.bajtos@student.upjs.sk');
INSERT INTO `diary`.`subject` (`id_subject`, `name`, `email`) VALUES ('7', 'pm', 'szabari@upjs.sk');
INSERT INTO `diary`.`subject` (`id_subject`, `name`, `site`, `email`) VALUES ('8', 'psin', 'https://siete.gursky.sk/', 'peter.gursky@upjs.sk');
INSERT INTO `diary`.`subject` (`id_subject`, `name`) VALUES ('9', 'prp');
INSERT INTO `diary`.`subject` (`id_subject`, `name`, `site`, `email`) VALUES ('10', 'paz1b', 'https://paz1b.ics.upjs.sk/', 'frantisek.galcik@upjs.sk');
INSERT INTO `diary`.`subject` (`id_subject`, `name`) VALUES ('02', 'wbn');
INSERT INTO `diary`.`subject` (`id_subject`, `name`) VALUES ('02', 'abap');
INSERT INTO `diary`.`subject` (`id_subject`, `name`, `site`, `email`) VALUES ('13', 'dbs1a', 'https://databazy.ics.upjs.sk/dbs1a/index.html', 'wiliam.kacala@student.upjs.sk');
INSERT INTO `diary`.`subject` (`id_subject`, `name`, `site`, `email`) VALUES ('14', 'dbs1b', 'https://databazy.ics.upjs.sk/dbs1b/index.html', 'wiliam.kacala@student.upjs.sk');
INSERT INTO `diary`.`subject` (`id_subject`, `name`, `site`, `email`) VALUES ('15', 'osy', 'https://ics.upjs.sk/~pisarcik/osy/', 'pisarcik@upjs.sk');

-- Fill exam table
INSERT INTO `diary`.`exam` (`id_exam`, `date`, `location`, `id_subject`) VALUES (1, '2020-02-4 9:00', 'SA1A1', 1);
INSERT INTO `diary`.`exam` (`id_exam`, `date`, `location`, `id_subject`) VALUES (2, '2020-02-02 10:00', 'AJAX13', 2);
INSERT INTO `diary`.`exam` (`id_exam`, `date`, `location`, `id_subject`) VALUES (3, '2020-02-02 9:20', '40', 3);
INSERT INTO `diary`.`exam` (`id_exam`, `date`, `location`, `id_subject`) VALUES (4, '2020-02-13 10:15', 'SJ1P01', 7);
INSERT INTO `diary`.`exam` (`id_exam`, `date`, `location`, `id_subject`) VALUES (5, '2020-02-19 14:20', 'SA1A1', 7);
INSERT INTO `diary`.`exam` (`id_exam`, `date`, `location`, `id_subject`) VALUES (6, '2020-02-20 02:00', 'SJ1P02', 2);
INSERT INTO `diary`.`exam` (`id_exam`, `date`, `location`, `id_subject`) VALUES (7, '2020-01-21 8:00', 'SJSP01', 10);
INSERT INTO `diary`.`exam` (`id_exam`, `date`, `location`, `id_subject`) VALUES (8, '2020-02-25 9:00', 'SJ1P03', 02);
INSERT INTO `diary`.`exam` (`id_exam`, `date`, `location`, `id_subject`) VALUES (9, '2020-02-29 13:00', 'SA1A1', 15);
INSERT INTO `diary`.`exam` (`id_exam`, `date`, `location`, `id_subject`) VALUES (10, '2020-01-30 8:30', 'SJ1P04',4);

-- Fill homework table
INSERT INTO `diary`.`homework` (`id_homework`, `deadline`, `description`, `id_subject`) VALUES (1, '2020-02-25 23:00', 'description of homework',2);
INSERT INTO `diary`.`homework` (`id_homework`, `deadline`, `description`, `id_subject`) VALUES (2, '2020-02-23 17:05', 'second one', 2);
INSERT INTO `diary`.`homework` (`id_homework`, `deadline`, `description`, `id_subject`) VALUES (3, '2020-02-14 21:00', 'hw', 1);
INSERT INTO `diary`.`homework` (`id_homework`, `deadline`, `description`, `id_subject`) VALUES (4, '2020-02-6 00:00', 'pazpazpaz1c', 4);
INSERT INTO `diary`.`homework` (`id_homework`, `deadline`, `description`, `id_subject`) VALUES (5, '2020-1-02 23:59', 'some description', 3);
INSERT INTO `diary`.`homework` (`id_homework`, `deadline`, `description`, `id_subject`) VALUES (6, '2020-01-1 23:59', 'strc prst skrz krk', 3);
INSERT INTO `diary`.`homework` (`id_homework`, `deadline`, `description`, `id_subject`) VALUES (7, '2020-1-2 00:00', 'cosi tak toto je uzasna databaza', 3);
INSERT INTO `diary`.`homework` (`id_homework`, `deadline`, `description`, `id_subject`) VALUES (8, '2020-2-14 0:15', 'pan boh prosim chcem A', 3);

-- Fill lesson table