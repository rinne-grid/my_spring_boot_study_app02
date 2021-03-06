DROP SCHEMA IF EXISTS rngd_ss_todo;
CREATE DATABASE rngd_ss_todo CHARACTER SET utf8mb4;
create user 'springuser'@'%' identified by 'springuser';
grant all on rngd_ss_todo.* to 'springuser'@'%';
