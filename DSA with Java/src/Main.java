import java.io.*;
import java.util.LinkedList;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        SourceFile();
        SearchFile();
        LinkedList<Integer> linkedList = readFile();
        for (Integer value : linkedList) {
            System.out.println(value);
        }
    }

    private static void SourceFile() throws IOException {
        int count = 10000;
        Random random = new Random();
        File file = new File("Source.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fWriter = new FileWriter("Source.txt");
        for (int i = 0; i < count; i++) {
            int r = random.nextInt(0, 300);
            fWriter.write(Integer.toString(r));
            if (i < count - 1) {
                fWriter.write(",");
            }
        }
        fWriter.close();
    }

    private static void SearchFile() throws IOException {
        int count = 10000;
        Random random = new Random();
        File file = new File("Search.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fWriter = new FileWriter("Search.txt");
        for (int i = 0; i < count; i++) {
            int r = random.nextInt(0, 300);
            fWriter.write(Integer.toString(r));
            if (i < count - 1) {
                fWriter.write(",");
            }
        }
        fWriter.close();
    }

    private static LinkedList<Integer> readFile() throws IOException {
        LinkedList<Integer> linkedList = new LinkedList<>();
        readSourceFile(linkedList);
        readSearchFile(linkedList);
        searchAndChange(linkedList);
        return linkedList;
    }

    private static void readSourceFile(LinkedList<Integer> linkedList) throws IOException {
        BufferedReader sourceReader = new BufferedReader(new FileReader("Source.txt"));
        String sourceline;

        while ((sourceline = sourceReader.readLine()) != null) {
            String[] values = sourceline.split(",");
            for (String value : values) {
                int intValue = Integer.parseInt(value);
                if (!linkedList.contains(intValue)) {
                    linkedList.addLast(intValue);
                }
            }
        }
    }

    private static void readSearchFile(LinkedList<Integer> linkedList) throws IOException {
        BufferedReader searchReader = new BufferedReader(new FileReader("Search.txt"));
        String searchline;

        while ((searchline = searchReader.readLine()) != null) {
            double totalAccess = 0;
            double averageAccess = 0;
            double number = 0;
            String[] values = searchline.split(",");
            for (String value : values) {
                int intValue = Integer.parseInt(value);
                if (linkedList.contains(intValue)) {
                    totalAccess += (linkedList.indexOf(intValue) + 1);
                    number++;
                    averageAccess = totalAccess / number;
                }
            }
            System.out.println("TOTAL MEMORY ACCESSİNG  :" + totalAccess);
            System.out.println("AVERAGE MEMORY ACCESSİNG  :" + averageAccess);
        }
    }
    private static void searchAndChange(LinkedList<Integer> linkedList) throws IOException{
        BufferedReader searchReader = new BufferedReader(new FileReader("Search.txt"));
        String searchline;

        while ((searchline = searchReader.readLine()) != null) {
            double totalAccess1 = 0;
            double averageAccess1 = 0;
            double number1 = 0;
            String[] values = searchline.split(",");
            for (String value : values) {
                int intValue = Integer.parseInt(value);
                if (linkedList.contains(intValue)) {
                    totalAccess1 += (linkedList.indexOf(intValue) + 1);
                    moveToHead(linkedList, intValue);
                    number1++;
                    averageAccess1 = totalAccess1 / number1;

                }
            }
            System.out.println("TOTAL MEMORY ACCESSİNG AFTER CHANGE :" + totalAccess1);
            System.out.println("AVERAGE MEMORY ACCESSİNG AFTER CHANGE  :" + averageAccess1);
        }
    }
    private static void moveToHead(LinkedList<Integer> linkedList, int value) {
        linkedList.remove((Integer) value);
        linkedList.addFirst(value);
    }
}