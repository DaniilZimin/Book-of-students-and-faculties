SELECT *
FROM student
WHERE age > 22
  AND age < 26;

SELECT name
FROM student;

SELECT *
FROM student
WHERE name LIKE '%и%';

SELECT *
FROM student AS st
WHERE st.age < st.id;

SELECT *
FROM student
ORDER BY age;