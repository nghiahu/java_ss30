package presentation;

import business.model.User.User;
import business.service.user.UserServiceImp;
import validate.ChoiceValidator;
import validate.Validator;

import java.util.Scanner;

public class UserUI {
    private final UserServiceImp userServiceImp;

    public UserUI() {
        userServiceImp = new UserServiceImp();
    }
    private static User currentUser = new User();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ỨNG DỤNG QUẢN LÝ PHÒNG BAN VÀ NHÂN SỰ");
        UserUI userUI = new UserUI();
        do{
            System.out.println("****************MENU ĐĂNG NHẬP**********************");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Thoát chương trình");
            int choice = ChoiceValidator.validateChoice(scanner);
            switch (choice) {
                case 1:
                    currentUser = userUI.loginUser(scanner);
                    if(currentUser == null){
                        System.out.println("Đăng nhập thất bại!");
                    }else {
                        System.out.println("Đăng nhập thành công");
                        userUI.displayMenuChoice(scanner);
                    }
                    break;
                case 2:
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 1 - 2!");
            }
        }while (true);
    }

    public User loginUser(Scanner scanner) {
        String username = Validator.validateString(scanner,"Nhập vào tên đăng nhập: ",0,100);
        String password = Validator.validateString(scanner, "Nhập vào mật khẩu: ",0,100);
        return userServiceImp.userLogin(username, password);
    }
    public void displayMenuChoice(Scanner scanner) {
        boolean Exit = false;
        do {
            System.out.println("***************************MENU CHỨC NĂNG QUẢN LÝ*************************");
            System.out.println("- Người dùng: " + currentUser.getUsername());
            System.out.println("1. Quản lý phòng ban");
            System.out.println("2. Quản lý nhân sự");
            System.out.println("3. Thống kê");
            System.out.println("4. Đăng xuất");
            int choice = ChoiceValidator.validateChoice(scanner);
            switch (choice) {
                case 1:
                    DepartmentUI.displayDepartmentMenu();
                    break;
                case 2:
                    EmployeeUI.displayEmployeeMenu();
                    break;
                case 3:
                    DashboardUI.displayDashboardMenu();
                    break;
                case 4:
                    while (true) {
                        System.out.print("Bạn có chắc muốn đăng xuất(y/n): ");
                        String choice2 = scanner.nextLine();
                        if (choice2.equals("y")) {
                            Exit = true;
                            currentUser = null;
                            break;
                        } else if (choice2.equals("n")) {
                            break;
                        }else {
                            System.out.println("Vui lòng chọn y hoặc n!");
                        }
                    }
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ vui lòng chọn 1 - 3!");
            }
        }while (!Exit);
    }
}
