package validate;

import java.util.Locale;
import java.util.Scanner;

public class Validator {
    public static String validateString(Scanner scanner, String message, int min, int max) {
        while (true){
            System.out.print(message);
            String value = scanner.nextLine().trim();
            if(value.isEmpty()){
                System.out.println("Dữ liệu không được để trống!");
            } else if (value.length() < min || value.length() > max) {
                System.out.println("Dữ liệu không phù hợp!");
            }else {
                return value;
            }
        }
    }
    public static boolean validateBoolean(Scanner scanner,String message) {
        while (true){
            System.out.print(message);
            String value = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
            if(value.isEmpty()){
                System.out.println("Dữ liệu không được để trống");
            } else if (value.equals("true") || value.equals("false")) {
                return Boolean.parseBoolean(value);
            }else {
                System.out.println("Dữ liệu không hợp lệ, vui lòng thử lại!");
            }
        }
    }
}
