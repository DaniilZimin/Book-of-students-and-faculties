SELECT student.name, student.age, f.name
FROM student
         LEFT JOIN faculty f ON student.faculty_id = f.id;


SELECT s.name, s.age
FROM student s
         INNER JOIN avatar a ON s.id = a.student_id;