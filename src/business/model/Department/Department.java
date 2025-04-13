package business.model.Department;

import business.model.IApp;
import validate.DepartmentValidator;
import validate.Validator;

import java.util.Scanner;

public class Department implements IApp {
    private int department_id;
    private String department_name;
    private String description;
    private boolean status;

    public Department() {
    }

    public Department(int department_id, String department_name, String description, boolean status) {
        this.department_id = department_id;
        this.department_name = department_name;
        this.description = description;
        this.status = status;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.department_name = DepartmentValidator.validateName(scanner);
        this.description = Validator.validateString(scanner,"Nhập vào mô tả phòng ban: ",0,255);
        this.status = Validator.validateBoolean(scanner,"Nhập vào trạng thái phòng ban: ");
    }

    @Override
    public void displayData() {
        System.out.println("Mã phòng ban: " + department_id + " - Tên phòng ban: " + department_name + " - Mô tả: " + description + " - Trạng thái: " + (status? "Hoạt động":"Không hoạt động"));
    }
}
