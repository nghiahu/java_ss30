package validate;


import business.model.Emloyee.Emp_Status;
import business.model.Emloyee.Employee;
import business.model.Emloyee.Gender;
import business.service.department.DepartmentServiceImp;
import business.service.employee.EmployeeServiceImp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class ValidateEmployee {
    private static final String PHONE_VIETTEL_PREFIXES = "086|096|097|098|039|038|037|036|035|034|033|032";
    private static final String PHONE_VINAPHONE_PREFIXES = "091|094|088|083|084|085|081|082";
    private static final String PHONE_MOBIFONE_PREFIXES = "070|079|077|076|078|089|090|093";
    private EmployeeServiceImp employeeService = new EmployeeServiceImp();
    private DepartmentServiceImp departmentService = new DepartmentServiceImp();
    public ValidateEmployee() {
        employeeService = new EmployeeServiceImp();
    }

    public static String isValidEmail(Scanner scanner) {
        while (true){
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            System.out.print("Nhập vào email: ");
            String email = scanner.nextLine().trim();
            if(email.isEmpty()){
                System.out.println("Email không được để trống");
            } else if (!email.matches(emailRegex)) {
                System.out.println("Email không đúng định dạng");
            }else {
                return email;
            }
        }
    }

    public static String isValidPhoneNumberVN(Scanner scanner) {
        String phoneRegex = "(" + PHONE_VIETTEL_PREFIXES + "|" + PHONE_VINAPHONE_PREFIXES + "|" + PHONE_MOBIFONE_PREFIXES + ")\\d{7}";
        while (true){
            System.out.print("Nhập vào số điện thoại: ");
            String phoneNumber = scanner.nextLine().trim();
            if(phoneNumber.isEmpty()){
                System.out.println("Số điện thoại không được để trống");
            } else if (!phoneNumber.matches(phoneRegex)) {
                System.out.println("Số điện thoại không đúng định dạng số Việt Nam");
            }else {
                return phoneNumber;
            }
        }
    }

    public static String validateId(Scanner scanner){
        ValidateEmployee validateEmployee = new ValidateEmployee();
        while(true){
            System.out.print("Nhập vào mã nhân viên: ");
            String id = scanner.nextLine().trim();
            String regex = "^E\\d{4}$";
            Employee employee = validateEmployee.employeeService.getEmployeeById(id);
            if(id.isEmpty()){
                System.out.println("Mã nhân viên không được để trống!");
            } else if (!id.matches(regex)) {
                System.out.println("Mã nhân viên không đúng định dạng");
            } else if (employee != null) {
                System.out.println("Mã nhân viên đã tồn tại: ");
            }else {
                return id;
            }
        }
    }
    public static String validateName(Scanner scanner) {
        while (true) {
            System.out.print("Nhập tên nhân viên (15-150 ký tự): ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Tên không được để trống");
            } else if (name.length() < 15 || name.length() > 150) {
                System.out.println("Tên phải có từ 15 đến 150 ký tự");
            } else {
                return name;
            }
        }
    }

    public static Gender validateGender(Scanner scanner) {
        while (true) {
            System.out.print("Nhập giới tính (MALE/FEMALE/OTHER): ");
            String gender = scanner.nextLine().trim().toUpperCase();
            if (gender.equals("MALE") || gender.equals("FEMALE") || gender.equals("OTHER")) {
                return Gender.valueOf(gender);
            } else {
                System.out.println("Giới tính không hợp lệ!");
            }
        }
    }

    public static int validateSalaryLevel(Scanner scanner) {
        while (true) {
            System.out.print("Nhập bậc lương: ");
            String input = scanner.nextLine().trim();
            try {
                int level = Integer.parseInt(input);
                if (level > 0) {
                    return level;
                } else {
                    System.out.println("Bậc lương phải lớn hơn 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("Bậc lương phải là số nguyên");
            }
        }
    }

    public static double validateSalary(Scanner scanner) {
        while (true) {
            System.out.print("Nhập lương: ");
            String input = scanner.nextLine().trim();
            try {
                double salary = Double.parseDouble(input);
                if (salary > 0) {
                    return salary;
                } else {
                    System.out.println("Lương phải lớn hơn 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lương phải là số thực");
            }
        }
    }

    public static LocalDate validateBirthDate(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Ngày sinh không đúng định dạng");
            }
        }
    }

    public static String validateAddress(Scanner scanner) {
        while (true) {
            System.out.print("Nhập địa chỉ: ");
            String address = scanner.nextLine().trim();
            if (address.isEmpty()) {
                System.out.println("Địa chỉ không được để trống");
            } else {
                return address;
            }
        }
    }

    public static Emp_Status validateStatus(Scanner scanner) {
        while (true) {
            System.out.print("Nhập trạng thái (ACTIVE/INACTIVE/ONLEAVE/POLICYLEAVE): ");
            String status = scanner.nextLine().trim().toUpperCase();
            if (status.equals("ACTIVE") || status.equals("INACTIVE") || status.equals("ONLEAVE") || status.equals("POLICYLEAVE")) {
                return Emp_Status.valueOf(status);
            } else {
                System.out.println("Trạng thái không hợp lệ.");
            }
        }
    }
    public static int validateDepartmentId(Scanner scanner) {
        ValidateEmployee validateEmployee = new ValidateEmployee();
        while (true) {
            System.out.print("Nhập vào mã phòng ban: ");
            String input = scanner.nextLine().trim();
            try {
                int depId = Integer.parseInt(input);
                if(validateEmployee.departmentService.selectDepartmentById(depId) != null) {
                    return depId;
                }else {
                    System.out.println("Phòng ban không tồn tại, vui lòng nhập lại!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Mã phòng là một số nguyên: ");
            }
        }
    }
}
