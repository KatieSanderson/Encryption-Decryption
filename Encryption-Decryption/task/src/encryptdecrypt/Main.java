package encryptdecrypt;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TargetOperation targetOperation = scanner.nextLine().equals("enc") ? TargetOperation.ENCRYPTION : TargetOperation.DECRYPTION;
        String message = scanner.nextLine();
        int offset = Integer.parseInt(scanner.nextLine());

        String shifted = performOperation(message, offset, targetOperation);
        System.out.println(shifted);
    }

    private static String performOperation(String message, int offset, TargetOperation targetOperation) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            sb.append((char) (message.charAt(i) + targetOperation.getShiftDirection() * offset));
        }
        return sb.toString();
    }
}