INSERT INTO Course(name,fees,duration,level) VALUES ('Java',200000,2020-12-01,'Basic');
INSERT INTO Course(name,fees,duration,level) VALUES ('PHP',500000,2020-12-01,'Intermediate');

INSERT INTO Classes(name,start_date,course_id) VALUES ('Java EE','2020-02-12',1);

INSERT INTO Student(name,email,phno) VALUES ('Myat Min Thant','myatminthant@gmail.com','097986565');

INSERT INTO Registration(class_id,student_id,paidAmount,registrationDate) VALUES (1,1,200000,'2020-11-01');