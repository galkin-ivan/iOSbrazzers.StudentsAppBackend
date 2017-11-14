-- Table: users
CREATE TABLE IF NOT EXISTS users (
  id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
)
  ENGINE = InnoDB;

-- Table: roles
CREATE TABLE IF NOT EXISTS roles (
  id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
)
  ENGINE = InnoDB;

-- Table for mapping user and roles: user_roles
CREATE TABLE IF NOT EXISTS user_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),

  UNIQUE (user_id, role_id)
)
  ENGINE = InnoDB;

-- Table: universities
CREATE TABLE IF NOT EXISTS universities (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL ,
  description VARCHAR(200) DEFAULT NULL
)
  ENGINE = InnoDB;

-- Table: faculties
CREATE TABLE IF NOT EXISTS faculties (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR(150) NOT NULL ,
  description VARCHAR(200) DEFAULT NULL
)
  ENGINE = InnoDB;

-- Table mapping universities and faculties: universities_faculties
CREATE TABLE IF NOT EXISTS universities_faculties (
  university_id INT NOT NULL ,
  faculty_id INT NOT NULL ,

  FOREIGN KEY (university_id) REFERENCES universities(id),
  FOREIGN KEY (faculty_id) REFERENCES faculties(id),

  UNIQUE (university_id, faculty_id)
)
  ENGINE = InnoDB;

-- Table: groups
CREATE TABLE IF NOT EXISTS groups(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR(50) NOT NULL
)
  ENGINE = InnoDB;

-- Table mapping faculties and groups: faculties_groups
CREATE TABLE IF NOT EXISTS faculties_groups(
  faculty_id INT NOT NULL ,
  group_id INT NOT NULL ,

  FOREIGN KEY (faculty_id) REFERENCES faculties(id),
  FOREIGN KEY (group_id) REFERENCES groups(id),

  UNIQUE (faculty_id, group_id)
)
  ENGINE = InnoDB;

-- Core Data replication

-- Table Subjects
CREATE TABLE IF NOT EXISTS subjects(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR(100) NOT NULL
)
  ENGINE = InnoDB;

-- Table Activities
CREATE TABLE IF NOT EXISTS activities(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  date DATE,
  short_name VARCHAR(100) NOT NULL ,
  subject_id INT NOT NULL ,
  group_id INT NOT NULL ,

  FOREIGN KEY (group_id) REFERENCES groups(id),
  FOREIGN KEY (subject_id) REFERENCES subjects(id)
)
  ENGINE = InnoDB;

-- Table tasks
CREATE TABLE IF NOT EXISTS tasks(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  date DATE NOT NULL ,
  description VARCHAR(400),
  priority SMALLINT,
  short_name VARCHAR(150),
  status SMALLINT,
  subject_id INT NOT NULL ,
  group_id INT NOT NULL ,

  FOREIGN KEY (group_id) REFERENCES groups(id),
  FOREIGN KEY (subject_id) REFERENCES subjects(id)

)
  ENGINE = InnoDB;

-- Table Teacher
CREATE TABLE IF NOT EXISTS teacher(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR(50),
  family_name VARCHAR(50),
  father_name VARCHAR(50)
)
  ENGINE = InnoDB;

-- Table TimeTable
CREATE TABLE IF NOT EXISTS time_table(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  begin_date DATE DEFAULT NULL ,
  end_date DATE DEFAULT NULL ,
  date DATE DEFAULT NULL ,
  day_of_week SMALLINT DEFAULT NULL ,
  start_time INT,
  end_time INT,
  parity BOOL DEFAULT NULL ,
  place VARCHAR(30),
  type VARCHAR(30),
  subject_id INT NOT NULL ,
  teacher_id INT DEFAULT NULL ,
  group_id INT NOT NULL ,

  FOREIGN KEY (group_id) REFERENCES groups(id),
  FOREIGN KEY (subject_id) REFERENCES subjects(id),
  FOREIGN KEY (teacher_id) REFERENCES teacher(id)

)
  ENGINE = InnoDB;


-- End Core Data Replication

-- Refer groups to core data replication tables

-- Table mapping subjects to groups: groups_subjects
CREATE TABLE IF NOT EXISTS groups_subjects(
  group_id INT NOT NULL ,
  subject_id INT NOT NULL ,

  FOREIGN KEY (group_id) REFERENCES groups(id),
  FOREIGN KEY (subject_id) REFERENCES subjects(id),

  UNIQUE (group_id,subject_id)
)
  ENGINE = InnoDB;

-- Table mapping teachers to groups: groups_teachers
CREATE TABLE IF NOT EXISTS groups_teachers(
  group_id INT NOT NULL ,
  teacher_id INT NOT NULL ,

  FOREIGN KEY (group_id) REFERENCES groups(id),
  FOREIGN KEY (teacher_id) REFERENCES teacher(id),

  UNIQUE (group_id,teacher_id)
)
  ENGINE = InnoDB;

-- End Refer groups to core data replication tables

-- Insert data
-- SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO universities (id, name, description)
VALUES
  (1, 'МГТУ им. Н.Э.Баумана', 'Московский государственный технический'),
  (2, 'Московский университет им. С.Ю. Витте', NULL ),
  (3, 'Московский экономический институт', NULL ),
  (4, 'Институт международных экономических связей', NULL ),
  (5, 'Высшая школа экономики', NULL ),
  (6, 'Российский государственный университет туризма и сервиса', NULL ),
  (7, 'Московский институт психоанализа', NULL );

INSERT INTO faculties(id, name, description) VALUES
  (1, 'ИУ', 'Информационные системы и сети'),
  (2, 'СМ', 'Специальное машиностроение'),
  (3, 'ИБМ', NULL ),
  (4, 'БМ', 'Бизнес информатика'),
  (5, 'Менеджмент', NULL ),
  (6, 'Туризм', NULL )
  ;

INSERT INTO universities_faculties (university_id, faculty_id)  VALUES
  (1, 1),
  (1, 2),
  (1, 3),
  (2, 4),
  (2, 5),
  (2, 6)
  ;

INSERT INTO groups(id, name) VALUES
  (1, 'ИУ6-71'),
  (2, 'ИУ6-72'),
  (3, 'ИУ6-73')
  ;

INSERT INTO faculties_groups(faculty_id, group_id) VALUES
  (1, 1),
  (1, 2),
  (1, 3)
  ;



INSERT INTO users VALUES (1, 'galkinivan', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG');

 INSERT INTO roles VALUES (1, 'ROLE_USER');
 INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

 INSERT INTO user_roles VALUES (1, 2);


INSERT INTO subjects(id, name) VALUES
  (1, 'Экономика'),
  (2, 'Организация ЭВМ и систем'),
  (3, 'Курсовая работа'),
  (4, 'Математическая логика и теория алгоритмов'),
  (5, 'Разработка приложений на IOS'),
  (6, 'Экономика'),
  (7, 'Теория систем и системный анализ'),
  (8, 'Введение в предпренимательство'),
  (9, 'Разработка приложений на языке C#'),
  (10, 'Прийти к губарю'),
  (11, 'НИР')
  ;

INSERT INTO activities(id, date, short_name, subject_id, group_id) VALUES
  (1, '2017-10-15', 'Рубежный контроль №1', 1, 2),
  (2, '2017-10-27', 'Рубежный контроль №2', 1, 2),
  (3, '2017-11-18', 'Рубежный контроль №2', 2, 2),
  (4, '2017-10-27', 'Рубежный контроль №1', 4, 2),
  (5, '2017-09-08', 'Рубежный контроль №1', 2, 2),
  (6, '2017-01-25', 'Экзамен', 7, 2),
  (7, '2017-10-28', 'Рубежный контроль №1', 8, 2),
  (8, '2017-10-28', 'Рубежный контроль №3', 6, 2)
  ;

INSERT INTO teacher(id, name, family_name, father_name) VALUES
  (1, 'Павлов В.А.', '', ''),
  (2, 'Попов А.Ю.', '', ''),
  (3, 'Хартов В.Я.', '', ''),
  (4, 'Гуренко В.В.', '', ''),
  (5, 'Скворцова Д.А.', '', ''),
  (6, 'Булдакова Т.И.', '', ''),
  (7, 'Абрамов Н.И.', '', ''),
  (8, 'Банников С.Н.', '', ''),
  (9, 'Губарь А.М.', '', '')
  ;

INSERT INTO tasks(id, date, description, priority, short_name, status, subject_id, group_id) VALUES
  (1, '2017-11-25', 'Парам-пам-пам Попов порам парам парам иос прогаем чтобы работало это описание задания это описание задания это описание задания', 2, 'Сделать дз2 по Попову', 0, 2, 2),
  (2, '2017-10-15', 'ЗАПОЛНИ ЗАДАНИЯ ДЛЯ СЕБЯ, МАКЕТ Я СДЕЛАЛ, ТОЛЬКО ПОЛЯ МЕНЯЙ ОККУРАТНО, ЭТО ЛЕГКО СЛОМАТЬ', 2, 'Сделать дз1 по Попову', 0, 2, 2),
  (3, '2017-10-17', '', 1, 'Подготовить фс Хартову', 0, 11, 2),
  (4, '2017-10-28', '', 0, 'Сделать ЛР3 по шарпам', 0, 9, 2),
  (5, '2017-10-17', '', 0, 'Поготовить презентацию по ТСиСА', 0, 7, 2),
  (6, '2017-11-05', '', 1, 'Сделать дз2 по предпренимателю', 1, 8, 2),
  (7, '2017-12-12', '', 2, 'Сделать дз2 по Гуренко', 0, 4, 2),
  (8, '2017-09-24', '', 0, 'Сделать лр1 по шарпам', 0, 9, 2),
  (9, '2017-11-05', '', 0, 'Сделать дз2 по экономике', 0, 6, 2),
  (10, '2017-10-07', '', 0, 'Выбрать научрука', 0, 3, 2),
  (11, '2017-10-15', '', 1, 'Сделать дз1 для Гуренко', 0, 4, 2),
  (12, '2017-12-25', '', 2, 'Закончить разработку приложения', 0, 9, 2),
  (13, '2017-11-05', '', 1, 'Сделать отчет к лр3 по Попову', 0, 2, 2),
  (14, '2017-10-17', '', 0, 'Создать проект', 0, 9, 2),
  (15, '2017-10-28', '', 2, 'Подготовить что-нибудь для Хартова', 0, 3, 2)
  ;




INSERT INTO time_table(id, begin_date, end_date, date, day_of_week, start_time, end_time, parity, place, type, subject_id, teacher_id, group_id) VALUES
  (1, '2017-09-01', '2017-12-31', NULL , 2, 1015, 1150, FALSE , '515ю', 'Лекция', 1, 1, 2),
  (2, '2017-09-01', '2017-12-31', NULL , 2, 1200, 1335, NULL , '319', 'Лекция', 2, 2, 2),
  (3, '2017-09-01', '2017-12-31', NULL , 2, 1540, 1715, NULL , 'Каф', 'Консультация', 3, 3, 2),
  (4, '2017-09-01', '2017-12-31', NULL , 3, 1200, 1335, NULL , '306ю', 'Лекция', 4, 4, 2),
  (5, '2017-09-01', '2017-12-31', NULL , 3, 1350, 1525, NULL , '319', 'Лекция', 2, 2, 2),
  (6, '2017-09-01', '2017-12-31', NULL , 3, 1800, 2100, NULL , '502ю', 'Лекция', 5, NULL, 2),
  (7, '2017-09-01', '2017-12-31', NULL , 4, 1015, 1150, NULL , '306ю', 'Семинар', 4, 4, 2),
  (8, '2017-09-01', '2017-12-31', NULL , 4, 830, 1005, TRUE , '392', 'Семинар', 6, 5, 2),
  (9, '2017-09-01', '2017-12-31', NULL , 4, 1200, 1335, TRUE , '319', 'Лекция', 7, 6, 2),
  (10, '2017-09-01', '2017-12-31', NULL , 5, 1350, 1525, NULL , '319', 'Лекция', 8, 7, 2),
  (11, '2017-09-01', '2017-12-31', NULL , 5, 1540, 1715, NULL , '533', 'Лекция', 7, 6, 2),
  (12, '2017-09-01', '2017-12-31', NULL , 5, 1725, 1900, NULL , '430', 'Лекция', 9, 8, 2),
  (13, '2017-09-01', '2017-12-31', NULL , 5, 1200, 1335, TRUE , '319', 'Лекция', 8, 7, 2),
  (14, '2017-09-01', '2017-12-31', NULL , 5, 1900, 2100, FALSE , '430', 'Лекция', 9, 8, 2),
  (15, NULL , NULL , '2017-10-25', NULL, 1900, 2100, NULL , 'каф', 'Консультация', 10, 9, 2)
  ;

-- Insert into Refer groups to core data replication tables
INSERT INTO groups_subjects(group_id, subject_id) VALUES
  (2,1),
  (2,2),
  (2,3),
  (2,4),
  (2,5),
  (2,6),
  (2,7),
  (2,8),
  (2,9),
  (2,10),
  (2,11)
  ;

INSERT INTO groups_teachers(group_id, teacher_id) VALUES
  (2,1),
  (2,2),
  (2,3),
  (2,4),
  (2,5),
  (2,6),
  (2,7),
  (2,8),
  (2,9)
;

SET FOREIGN_KEY_CHECKS = 1;