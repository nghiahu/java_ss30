package presentation;

import business.model.Department.Department;
import business.model.Emloyee.Employee;
import business.service.employee.EmployeeService;
import business.service.employee.EmployeeServiceImp;
import validate.ChoiceValidator;
import validate.ValidateEmployee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeUI {
    private final EmployeeServiceImp employeeServiceImp;

    public EmployeeUI() {
        employeeServiceImp = new EmployeeServiceImp();
    }

    public static void displayEmployeeMenu() {
        Scanner scanner = new Scanner(System.in);
        EmployeeUI employeeUI = new EmployeeUI();
        boolean Exit = false;
        do {
            System.out.println("**********************MENU QUẢN LÝ NHÂN VIÊN*********************");
            System.out.println("1. Danh sách nhân viên");
            System.out.println("2. Thêm mới nhân viên");
            System.out.println("3. Cập nhật nhân viên");
            System.out.println("4. Xóa nhân viên");
            System.out.println("5. Tìm kiếm nhân viên");
            System.out.println("6. Sắp xếp nhân viên");
            System.out.println("7. Thoát");
            int choice = ChoiceValidator.validateChoice(scanner);
            switch (choice) {
                case 1:
                    employeeUI.displayEmployeeList(scanner);
                    break;
                case 2:
                    employeeUI.addNewEmployee(scanner);
                    break;
                case 3:
                    employeeUI.updateEmployee(scanner);
                    break;
                case 4:
                    employeeUI.deleteEmployee(scanner);
                    break;
                case 5:
                    employeeUI.searchEmployee(scanner);
                    break;
                case 6:
                    employeeUI.sortEmployee(scanner);
                    break;
                case 7:
                    Exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ vui lòng chọn từ 1 - 6!");
            }
        }while (!Exit);
    }
    public void displayEmployeeList(Scanner scanner) {
        if(employeeServiceImp.numberEmployees() == 0) {
            System.out.println("Chưa có nhân viên nào!");
        }else {
            int currentPage = 1;
            int pageSize = 10;
            int pageCount = employeeServiceImp.numberEmployees()/pageSize;
            if(employeeServiceImp.numberEmployees()%pageSize!=0) {
                pageCount++;
            }
            do {
                System.out.println("Trang: " + currentPage + " / " + pageCount);
                for (Employee employee : employeeServiceImp.displayListEmployees(currentPage, pageSize)){
                    employee.displayData();
                }
                System.out.println("Chọn trang (chọn 0 để thoát):");
                int choice = ChoiceValidator.validateChoice(scanner);
                if (choice == 0) {
                    break;
                }
                if (choice < 1 || choice > pageCount) {
                    System.out.println("Trang không hợp lệ vui lòng chọn lại!");
                }else {
                    currentPage = choice;
                }
            }while (true);
        }
    }
    public void addNewEmployee(Scanner scanner) {
        Employee employee = new Employee();
        employee.inputData(scanner);
        if(employeeServiceImp.save(employee)) {
            System.out.println("Thêm thành công");
        }else {
            System.out.println("Thêm thất bại");
        }
    }
    public void updateEmployee(Scanner scanner) {
        System.out.println("Nhập vào mã nhân viên muốn cập nhật");
        String id = scanner.next();
        Employee employee = employeeServiceImp.getEmployeeById(id);
        if(employee == null) {
            System.out.println("Không tìm thấy nhân viên");
        }else {
            boolean exit = false;
            do {
                System.out.println("********************** MENU UPDATE EMPLOYEE **********************");
                System.out.println("1. Cập nhật tên nhân viên");
                System.out.println("2. Cập nhật email");
                System.out.println("3. Cập nhật số điện thoại");
                System.out.println("4. Cập nhật giới tính");
                System.out.println("5. Cập nhật bậc lương");
                System.out.println("6. Cập nhật lương");
                System.out.println("7. Cập nhật ngày sinh");
                System.out.println("8. Cập nhật địa chỉ");
                System.out.println("9. Cập nhật trạng thái");
                System.out.println("10 Cập nhật phòng ban");
                System.out.println("11. Thoát");

                int choice = ChoiceValidator.validateChoice(scanner);
                switch (choice) {
                    case 1:
                        employee.setEmployee_name(ValidateEmployee.validateName(scanner));
                        break;
                    case 2:
                        employee.setEmail(ValidateEmployee.isValidEmail(scanner));
                        break;
                    case 3:
                        employee.setPhone_number(ValidateEmployee.isValidPhoneNumberVN(scanner));
                        break;
                    case 4:
                        employee.setGender(ValidateEmployee.validateGender(scanner));
                        break;
                    case 5:
                        employee.setSalary_grade(ValidateEmployee.validateSalaryLevel(scanner));
                        break;
                    case 6:
                        employee.setSalary(ValidateEmployee.validateSalary(scanner));
                        break;
                    case 7:
                        employee.setBirthday(ValidateEmployee.validateBirthDate(scanner));
                        break;
                    case 8:
                        employee.setAddress(ValidateEmployee.validateAddress(scanner));
                        break;
                    case 9:
                        employee.setStatus(ValidateEmployee.validateStatus(scanner));
                        break;
                    case 10:
                        employee.setDepartment_id(ValidateEmployee.validateDepartmentId(scanner));
                    case 11:
                        exit = true;
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
                }
                if(employeeServiceImp.update(employee)) {
                    System.out.println("Cập nhật thành công");
                }else {
                    System.out.println("Cập nhật thất bại");
                }
            } while (!exit);
        }
    }
    public void deleteEmployee(Scanner scanner) {
        System.out.print("Nhập vào mã sinh viên muốn xóa: ");
        String id = scanner.next();
        Employee employee = employeeServiceImp.getEmployeeById(id);
        if(employee == null) {
            System.out.println("Không tìm thấy nhân viên");
        }else {
            employeeServiceImp.delete(employee);
        }
    }
    public void searchEmployee(Scanner scanner) {
        boolean exit = false;
        List<Employee> employees = new ArrayList<>();
        do {
            System.out.println("********************** MENU SEARCH EMPLOYEE *********************");
            System.out.println("1. Tìm kiếm theo tên");
            System.out.println("2. Tìm kiếm theo tuổi");
            System.out.println("3. Thoát");
            int choice = ChoiceValidator.validateChoice(scanner);
            switch (choice) {
                case 1:
                    System.out.print("Nhập tên nhân viên muốn tìm");
                    String name = scanner.next();
                    employees = employeeServiceImp.getEmployeesByName(name);
                    if(employees == null) {
                        System.out.println("Không tìm thấy nhân viên nào!");
                    }else {
                        employees.forEach(Employee::displayData);
                    }
                    break;
                case 2:
                    System.out.print("Nhập vào tuổi bắt đầu: ");
                    int ageStart = Integer.parseInt(scanner.next());
                    System.out.println("Nhập vào tuổi kết thúc: ");
                    int ageEnd = Integer.parseInt(scanner.next());
                    employees = employeeServiceImp.getEmployeesByAge(ageStart, ageEnd);
                    if(employees == null) {
                        System.out.println("Không tìm thấy nhân viên nào!");
                    }else {
                        employees.forEach(Employee::displayData);
                    }
                    break;
                case 3:
                    exit = true;
                    break;
            }
        }while (!exit);
    }
    public void sortEmployee(Scanner scanner) {
        boolean exit = false;
        do {
            System.out.println("********************** MENU SORT EMPLOYEE *********************");
            System.out.println("1. Sắp xếp theo lương giảm dần");
            System.out.println("2. Sắp xếp theo tên tăng dần");
            System.out.println("3. Thoát");
            int choice = ChoiceValidator.validateChoice(scanner);
            switch (choice) {
                case 1:
                    employeeServiceImp.sortBySalary();
                    break;
                case 2:
                    employeeServiceImp.sortByName();
                    break;
                case 3:
                    exit = true;
                    break;
            }
        }while (!exit);
    }
}
