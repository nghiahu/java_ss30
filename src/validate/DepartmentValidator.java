package validate;

import business.model.Department.Department;
import business.service.department.DepartmentServiceImp;

import java.util.Scanner;

public class DepartmentValidator {
    private final DepartmentServiceImp departmentService;

    public DepartmentValidator() {
        departmentService = new DepartmentServiceImp();
    }

    public static String validateName(Scanner scanner) {
        DepartmentValidator departmentValidator = new DepartmentValidator();
        while (true) {
            System.out.print("Nhập vào tên phòng ban: ");
            String value = scanner.nextLine().trim();
            Department department = departmentValidator.departmentService.searchDepartmentByName(value);
            if(value.isEmpty()) {
                System.out.println("Tên phòng ban không được để trống!");
            }else if(department != null) {
                System.out.println("Tên phòng đã tồn tại, vui lòng thử lại!");
            }else {
                return value;
            }
        }
    }
}
