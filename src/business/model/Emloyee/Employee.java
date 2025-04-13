package business.model.Emloyee;

import business.model.IApp;
import validate.ValidateEmployee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Employee implements IApp {
    private String employee_id;
    private String employee_name;
    private String email;
    private String phone_number;
    private Gender gender;
    private int salary_grade;
    private double salary;
    private LocalDate birthday;
    private String address;
    private Emp_Status status;
    private int department_id;

    public Employee() {
    }

    public Employee(String employee_id, String employee_name, String email, String phone_number, Gender gender, int salary_grade, double salary, LocalDate birthday, String address, Emp_Status status, int department_id) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.email = email;
        this.phone_number = phone_number;
        this.gender = gender;
        this.salary_grade = salary_grade;
        this.salary = salary;
        this.birthday = birthday;
        this.address = address;
        this.status = status;
        this.department_id = department_id;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getSalary_grade() {
        return salary_grade;
    }

    public void setSalary_grade(int salary_grade) {
        this.salary_grade = salary_grade;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Emp_Status getStatus() {
        return status;
    }

    public void setStatus(Emp_Status status) {
        this.status = status;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.employee_id = ValidateEmployee.validateId(scanner);
        this.employee_name = ValidateEmployee.validateName(scanner);
        this.email = ValidateEmployee.isValidEmail(scanner);
        this.phone_number = ValidateEmployee.isValidPhoneNumberVN(scanner);
        this.gender = ValidateEmployee.validateGender(scanner);
        this.salary_grade = ValidateEmployee.validateSalaryLevel(scanner);
        this.salary = ValidateEmployee.validateSalary(scanner);
        this.birthday = ValidateEmployee.validateBirthDate(scanner);
        this.address = ValidateEmployee.validateAddress(scanner);
        this.status = ValidateEmployee.validateStatus(scanner);
        this.department_id = ValidateEmployee.validateDepartmentId(scanner);
    }

    @Override
    public void displayData() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.println("Mã nhân viên: " + employee_id + " - Tên nhân viên: " + employee_name + " - email:" + email + " - SDT:" + phone_number + " - Giới tính: " + gender);
            System.out.println("Bậc lương: " + salary_grade + " - lương: " + salary);
            String formattedBirthday = birthday != null ? birthday.format(formatter) : "N/A";
            System.out.println("Ngày sinh: " + formattedBirthday + " - Địa chỉ: " + address + " - Trạng thái: " + status + " - Mã phòng ban: " + department_id);
            System.out.println("-------------------------------------------------------------------------------------------------");
        }
}
