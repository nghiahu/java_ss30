package presentation;

import business.model.Department.Department;
import business.service.department.DepartmentServiceImp;
import validate.ChoiceValidator;
import validate.DepartmentValidator;
import validate.Validator;

import java.util.Scanner;

public class DepartmentUI {
    private final DepartmentServiceImp departmentService;

    public DepartmentUI() {
        departmentService = new DepartmentServiceImp();
    }
    public static void displayDepartmentMenu() {
        DepartmentUI departmentUI = new DepartmentUI();
        Scanner scanner = new Scanner(System.in);
        boolean Exit = false;
        do {
            System.out.println("**********************MENU QUẢN LÝ PHÒNG BAN*********************");
            System.out.println("1. Danh sách phòng ban");
            System.out.println("2. Thêm mới phòng ban");
            System.out.println("3. Cập nhật phòng ban");
            System.out.println("4. Xóa phòng ban");
            System.out.println("5. Tìm kiếm phòng ban theo tên");
            System.out.println("6. Thoát");
            int choice = ChoiceValidator.validateChoice(scanner);
            switch (choice) {
                case 1:
                    departmentUI.displayDepartmentList(scanner);
                    break;
                case 2:
                    departmentUI.addNewDepartment(scanner);
                    break;
                case 3:
                    departmentUI.updateDepartment(scanner);
                    break;
                case 4:
                    departmentUI.deleteDepartment(scanner);
                    break;
                case 5:
                    departmentUI.searchDepartment(scanner);
                    break;
                case 6:
                    Exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ vui lòng chọn từ 1 - 6!");
            }
        }while (!Exit);
    }
    public void displayDepartmentList(Scanner scanner) {
        if(departmentService.numberDepartments() == 0){
            System.out.println("Chưa có phòng ban nào!");
        }else {
            int currentPage = 1;
            int pageSize = 5;
            int pageCount = departmentService.numberDepartments()/pageSize;
            if(departmentService.numberDepartments()%pageSize!=0) {
                pageCount++;
            }
            do {
                System.out.println("Trang: " + currentPage + " / " + pageCount);
                for (Department department : departmentService.displayPagination(currentPage, pageSize)){
                    department.displayData();
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
    public void addNewDepartment(Scanner scanner) {
        Department newDepartment = new Department();
        newDepartment.inputData(scanner);
        if (departmentService.save(newDepartment)) {
            System.out.println("Thêm phòng ban thành công");
        }else {
            System.out.println("Thêm phòng ban thất bại");
        }
    }

    public void updateDepartment(Scanner scanner) {
        System.out.print("Nhập vào mã phòng ban muốn cập nhật: ");
        int Id = Integer.parseInt(scanner.nextLine());
        Department department = departmentService.selectDepartmentById(Id);
        if(department == null) {
            System.out.println("Phòng ban không tồn tại");
        }else {
            boolean Exit = false;
            do {
                System.out.println("**********************MENU UPDATE DEPARTMENT*********************");
                System.out.println("1. Cập nhật tên phòng ban");
                System.out.println("2. Cập nhật mô tả phòng ban");
                System.out.println("3. Cập nhật trạng thái phòng ban");
                System.out.println("4. Thoát");
                int choice = ChoiceValidator.validateChoice(scanner);
                switch (choice) {
                    case 1:
                        department.setDepartment_name(DepartmentValidator.validateName(scanner));
                        break;
                    case 2:
                        department.setDescription(Validator.validateString(scanner,"Nhập vào mô tả phòng ban: ",0,255));
                        break;
                    case 3:
                        department.setStatus(Validator.validateBoolean(scanner,"Nhập vào trạng thái phòng ban: "));
                        break;
                    case 4:
                        Exit = true;
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ vui lòng nhập lại!");
                }
            }while (!Exit);
            if (departmentService.update(department)) {
                System.out.println("Cập nhật thành công");
            }else {
                System.out.println("Cập nhật thất bại");
            }
        }
    }
    public void deleteDepartment(Scanner scanner) {
        System.out.println("Nhập vào mã phòng ban muốn xóa: ");
        int Id = Integer.parseInt(scanner.nextLine());
        if(departmentService.isEmployeeInDepartment(Id) != 0){
            System.out.println("Phòng ban đã có nhân viên không thể xóa");
        }else {
            Department department = new Department();
            department.setDepartment_id(Id);
            if (departmentService.delete(department)) {
                System.out.println("Xóa phòng ban thành công");
            }else {
                System.out.println("Xóa phòng ban thất bại");
            }
        }
    }
    public void searchDepartment(Scanner scanner) {
        System.out.print("Nhập vào tên phòng ban muốn tìm: ");
        String search = scanner.nextLine();
        Department department = departmentService.searchDepartmentByName(search);
        if (department == null) {
            System.out.println("Không tìm thấy phòng ban!");
        }else {
            department.displayData();
        }
    }
}
