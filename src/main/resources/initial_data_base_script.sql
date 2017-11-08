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
  short_name VARCHAR(100) NOT NULL ,
  subject_id INT NOT NULL ,

  FOREIGN KEY (subject_id) REFERENCES subjects(id)
)
  ENGINE = InnoDB;

-- Table tasks
CREATE TABLE IF NOT EXISTS tasks(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  date DATE NOT NULL ,
  description VARCHAR(100),
  priority SMALLINT,
  short_name VARCHAR(150),
  status SMALLINT,
  subject_id INT NOT NULL ,

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
  day_of_week SMALLINT,
  start_time INT,
  end_time INT,
  parity BOOL DEFAULT NULL ,
  place VARCHAR(30),
  type VARCHAR(30),
  subject_id INT NOT NULL ,
  teacher_id INT,

  FOREIGN KEY (subject_id) REFERENCES subjects(id),
  FOREIGN KEY (teacher_id) REFERENCES teacher(id)

)
  ENGINE = InnoDB;


-- End Core Data Replication


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

SET FOREIGN_KEY_CHECKS = 1;