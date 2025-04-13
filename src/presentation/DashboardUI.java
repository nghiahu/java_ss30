package presentation;

import business.service.dashboard.DashboardServiceImp;
import validate.ChoiceValidator;

import java.util.Map;
import java.util.Scanner;

public class DashboardUI {
    private static DashboardServiceImp dashboardServiceImp = new DashboardServiceImp();
    public DashboardUI() {
        dashboardServiceImp = new DashboardServiceImp();
    }
    public static void displayDashboardMenu() {
        DashboardUI dashboardUI = new DashboardUI();
        Scanner scanner = new Scanner(System.in);
        boolean Exit = false;
        do {
            System.out.println("***********************MENU THỐNG KÊ***********************");
            System.out.println("1. Số lượng nhân viên theo từng phòng ban");
            System.out.println("2. Tổng số nhân viên của toàn bộ hệ thống");
            System.out.println("3. Phòng ban có nhiều nhân viên nhất");
            System.out.println("4. Phòng ban có lương cao nhất");
            System.out.println("5. Thoát");
            int choice = ChoiceValidator.validateChoice(scanner);
            switch (choice) {
                case 1:
                    dashboardUI.displayEmployeeCount();
                    break;
                case 2:
                    dashboardUI.totalEmployeeCount();
                    break;
                case 3:
                    dashboardUI.departmentWithMostEmployees();
                    break;
                case 4:
                    dashboardUI.departmentWithHighestSalary();
                    break;
                case 5:
                    Exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ vui lòng chọn 1 - 5!");
            }
        }while (!Exit);
    }
    public void displayEmployeeCount() {
        Map<String,Integer> listMap = dashboardServiceImp.employeeInDep();
        if (listMap == null) {
            System.out.println("Không có thông tin!");
        }else {
            for (Map.Entry<String, Integer> entry : listMap.entrySet()) {
                System.out.println("Phòng ban: " +entry.getKey() + " : " + entry.getValue()+ " nhân viên");
            }
        }
    }
    public void totalEmployeeCount() {
        System.out.println("Tổng số nhân viên của toàn công ty: " + dashboardServiceImp.getTotalEmployees());
    }
    public void departmentWithHighestSalary() {
        dashboardServiceImp.departmentWithHighestSalary();
    }
    public void departmentWithMostEmployees(){
        dashboardServiceImp.departmentWithMostEmployees();
    }
}
