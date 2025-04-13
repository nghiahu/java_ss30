package validate;

import java.util.Scanner;

public class ChoiceValidator {
    public static int validateChoice(Scanner scanner) {
        System.out.print("Lựa chọn: ");
        while (true){
            String choice = scanner.nextLine();
            try {
                return Integer.parseInt(choice);
            }catch (NumberFormatException e){
                System.out.print("Lựa chọn không hợp lệ vui lòng nhập lại: ");
            }
        }
    }
}
