CREATE DATABASE psa_db;CREATE OR REPLACE USER psaUser IDENTIFIED BY 'p5aus3R!';GRANT ALL ON psa_db.* TO 'psaUser'@'%';FLUSH PRIVILEGES;