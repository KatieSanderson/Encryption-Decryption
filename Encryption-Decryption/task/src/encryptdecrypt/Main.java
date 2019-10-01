package encryptdecrypt;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        TargetOperation targetOperation = TargetOperation.ENCRYPTION;
        String data = "";
        int key = 0;

        boolean readFromFile = false;
        String inFileName = "";
        boolean writeToFile = false;
        String outFileName = "";
        boolean useData = false;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode" :
                    i++;
                    switch (args[i]) {
                        case "enc" :
                            targetOperation = TargetOperation.ENCRYPTION;
                            break;
                        case "dec" :
                            targetOperation = TargetOperation.DECRYPTION;
                            break;
                        default :
                            // default value
                            targetOperation = TargetOperation.ENCRYPTION;
                    }
                    break;
                case "-key" :
                    i++;
                    try {
                        key = Integer.parseInt(args[i]);
                    } catch (NumberFormatException e){
                        // default value
                        key = 0;
                    }
                    break;
                case "-data" :
                    useData = true;
                    i++;
                    data = args[i];
                    break;
                case "-out" :
                    writeToFile = true;
                    i++;
                    outFileName = args[i];
                    break;
                case "-in" :
                    readFromFile = true;
                    i++;
                    inFileName = args[i];
                    break;
                default :
                    break;
            }
        }

        data = inputData(useData, readFromFile, inFileName, data);

        String shifted = performOperation(data, key, targetOperation);

        outputData(writeToFile, outFileName, shifted);
    }

    private static void outputData(boolean writeToFile, String outFileName, String shifted) {
        if (writeToFile) {
            try {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFileName))) {
                    writer.write(shifted);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(shifted);
        }
    }

    private static String inputData(boolean useData, boolean readFromFile, String inFileName, String data) {
        if (useData) {
            return data;
        } else if (readFromFile) {
            try {
                try (BufferedReader reader = new BufferedReader(new FileReader(inFileName))) {
                    return reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    private static String performOperation(String data, int key, TargetOperation targetOperation) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            sb.append((char) (data.charAt(i) + targetOperation.getShiftDirection() * key));
        }
        return sb.toString();
    }
}