-- liquibase formatted sql

-- changeset dzimin:1
CREATE INDEX student_name_index ON student(name);

-- changeset dzimin:2
CREATE INDEX faculty_nc_index ON faculty(name, color);
