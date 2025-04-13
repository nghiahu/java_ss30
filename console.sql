create database ManagerInformation;
use ManagerInformation;

create table Users(
    user_id int primary key,
    username varchar(100) unique not null,
    password varchar(100) not null,
    status bit
);

create table Department(
    department_id int primary key auto_increment,
    department_name varchar(100) unique not null,
    description varchar(255),
    status bit
);

create table Employee(
    emp_id char(5) primary key,
    emp_name varchar(150) check (length(emp_name) > 15) not null,
    email varchar(255) not null,
    phone_number varchar(10) not null,
    gender enum('MALE','FEMALE','OTHER'),
    salary_grade int check ( salary_grade > 0 ) not null,
    salary decimal(15,2) check ( salary > 0 ) not null,
    birthday date,
    address varchar(255) not null,
    status enum('ACTIVE','INACTIVE','ONLEAVE','POLICYLEAVE'),
    department_id int not null,
    foreign key (department_id) references Department(department_id)
 );

DELIMITER //
create procedure login(
    username_in varchar(100),
    password_in varchar(100),
    out return_code int
)
begin
    declare user_status boolean;
    if exists (select * from users where username = username_in and password = password_in) then
        select status into user_status from users where username = username_in and password = password_in;
        if user_status = 0 then
            set return_code = 2;
        else
            set return_code = 1;
            select * from users where username = username_in and password = password_in;
        end if;
    else
        set return_code = 0;
    end if;
end //
DELIMITER ;

DELIMITER //
create procedure displayListDepartment(
    page_in int,
    limit_in int
)
begin
    declare skip int;
    set skip = (page_in - 1) * limit_in;
    select * from Department
    limit limit_in
        offset skip;
end //
DELIMITER ;

DELIMITER //
create procedure displayListEmloyee(
    page_in int,
    limit_in int
)
begin
    declare skip int;
    set skip = (page_in - 1) * limit_in;
    select * from Employee
    limit limit_in
    offset skip;
end //
DELIMITER ;

DELIMITER //
create procedure numDepartment(
    out numberDepartment int
)
begin
    set numberDepartment = (select count(department_id) from Department);
end //
DELIMITER ;

DELIMITER //
create procedure searchDepByName(
    name_in varchar(100)
)
begin
    select * from Department where department_name = name_in;
end //
DELIMITER ;

DELIMITER //
create procedure addDepartment(
    dep_name varchar(100),
    dep_description  varchar(255),
    dep_status bit
)
begin
    insert into Department(department_name, description, status)
    values(dep_name,dep_description ,dep_status);
end //
DELIMITER ;

DELIMITER //
create procedure searchDepById(id_in int)
begin
    select * from Department where department_id = id_in;
end //
DELIMITER ;

DELIMITER //
create procedure updateDepartment(
    id_in int,
    dep_name varchar(100),
    dep_description varchar(255),
    dep_status bit
)
begin
    update Department
        set department_name = dep_name,
            description = dep_description,
            status = dep_status
    where department_id = id_in;
end //
DELIMITER ;

DELIMITER //
create procedure searchEmpByDep(id_in int)
begin
    select * from Employee where department_id = id_in;
end //
DELIMITER ;

DELIMITER //
create procedure isEmployee(id_in int)
begin
    select count(emp_id) from Employee where department_id = id_in;
end //
DELIMITER ;

DELIMITER //
create procedure delDepartment(id_in int)
begin
    delete from Department where department_id = id_in;
end //
DELIMITER ;
-- Employee-------------------------------------
DELIMITER //
create procedure displayListEmployee(
    page_in int,
    limit_in int
)
begin
    declare skip int;
    set skip = (page_in - 1) * limit_in;
    select * from Employee
    limit limit_in
        offset skip;
end //
DELIMITER ;

DELIMITER //
create procedure numEmployee()
begin
    select count(emp_id) from Employee;
end //
DELIMITER ;

DELIMITER //
create procedure addEmployee(
    emp_id_in char(5),
    emp_name_in varchar(100),
    email_in varchar(100),
    phone_number_in varchar(15),
    gender_in varchar(10),
    salary_grade_in int,
    salary_in decimal,
    birthday_in date,
    address_in varchar(255),
    status_in varchar(20),
    department_id_in int
)
begin
    insert into employee(
        emp_id, emp_name, email, phone_number, gender,
        salary_grade, salary, birthday, address, status, department_id
    ) values (
         emp_id_in, emp_name_in, email_in, phone_number_in, gender_in,
         salary_grade_in, salary_in, birthday_in, address_in, status_in, department_id_in);
end //
DELIMITER ;

DELIMITER //
create procedure searchEmpById(id_in char(5))
begin
    select * from Employee where emp_id = id_in;
end //
DELIMITER ;

DELIMITER //
create procedure updateEmployee(
    emp_id_in char(5),
    emp_name_in varchar(100),
    email_in varchar(100),
    phone_number_in varchar(15),
    gender_in varchar(10),
    salary_grade_in int,
    salary_in decimal,
    birthday_in date,
    address_in varchar(255),
    status_in varchar(20),
    department_id_in int
)
begin
    update Employee
        set emp_name = emp_name_in,
            email = email_in,
            phone_number = phone_number_in,
            gender = gender_in,
            salary_grade = salary_grade_in,
            salary = salary_in,
            birthday = birthday_in,
            address = address_in,
            status = status_in,
            department_id = department_id_in
    where emp_id = emp_id_in;

end //
DELIMITER ;

DELIMITER //
create procedure delEmployee(id_in char(5))
begin
    update Employee
        set status = 'INACTIVE'
    where emp_id = id_in;
end //
DELIMITER ;

DELIMITER //
create procedure searchEmpByName(name_in varchar(100))
begin
    select * from Employee where emp_name = name_in;
end //
DELIMITER ;

DELIMITER //
create procedure searchEmpByAge(
    ageStart_in int,
    ageEnd_in int
)
begin
    select * from Employee where timestampdiff(year, birthday, curdate()) between ageStart_in and ageEnd_in;
end //
DELIMITER ;

DELIMITER //
create procedure sortBySalary()
begin
    select * from Employee
    order by salary desc;
end //
DELIMITER ;

DELIMITER //
create procedure sortByName()
begin
    select * from Employee
    order by emp_name asc;
end //
DELIMITER ;
-- Dashboard
DELIMITER //
create procedure employeeInDep()
begin
    select
        d.department_id,
        d.department_name,
        count(e.emp_id) as total_employees
    from
        department d
            left join
        employee e on d.department_id = e.department_id
    group by
        d.department_id, d.department_name
    order by
        total_employees desc;
end //
DELIMITER ;

DELIMITER //
create procedure totalEmploy()
begin
    select count(*)  from Employee;
end //
DELIMITER ;

DELIMITER //

create procedure topDepartmetn()
begin
    select
        d.department_id,
        d.department_name,
        count(e.emp_id) AS total_employees
    from department d
    left join employee e ON d.department_id = e.department_id
    group by  d.department_id, d.department_name
    order by total_employees desc
    limit 1;
end //
DELIMITER ;

DELIMITER //
create procedure topSalary()
begin
    select  d.department_id, d.department_name, sum(e.salary) AS total_salary
    from department d
    join Employee e on d.department_id = e.department_id
    group by  d.department_id, d.department_name
    order by  total_salary desc
    limit 1;
end //
DELIMITER ;
