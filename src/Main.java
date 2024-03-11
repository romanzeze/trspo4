import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int[] finalMerge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0;
        int j = 0;
        int r = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                result[r] = a[i];
                i++;
                r++;
            } else {
                result[r] = b[j];
                j++;
                r++;
            }
            if (i == a.length) {
                while (j < b.length) {
                    result[r] = b[j];
                    r++;
                    j++;
                }
            }
            if (j == b.length) {
                while (i < a.length) {
                    result[r] = a[i];
                    r++;
                    i++;
                }
            }
        }

        return result;
    }

    static void printArray(int[] arr) {
        int n = arr.length;
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }

    static void writeToFile(int[] arr, String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName);
            for (int j : arr) {
                fw.write(j + " ");
            }
            System.out.println("Запис успішно виконано");
            fw.close();
        } catch (IOException e) {
            System.out.println("Виникла помилк");
            e.printStackTrace();
        }
    }

    static int[] readFromFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner s = new Scanner(file);
            int size = 0;
            while (s.hasNextInt()) {
                size++;
                s.nextInt();
            }

            int[] arr = new int[size];
            Scanner s1 = new Scanner(file);
            for (int i = 0; i < size; i++)
                arr[i] = s1.nextInt();
            return arr;
        } catch (Exception e) {
            System.out.println("Виникла помилка");
            e.printStackTrace();
            return null;
        }
    }

    public static int[] generateInput() {
        int size;
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        System.out.print("Ведіть розмір масиву: \n");
        size = scan.nextInt();

        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1000);
        }
        return arr;
    }

    public static void main(String[] args) throws InterruptedException {
        int[] result;
        Scanner scan = new Scanner(System.in);
        int[] original = new int[0];
        while (true) {
            System.out.println("Виберіть метод: 1 - згенерувати райндомний; 2 - записати в файл; 3 - вихід із програми");
            int choice = scan.nextInt();
            switch (choice) {
                case 1: {
                    original = generateInput();
                    break;
                }
                case 2: {
                    original = readFromFile("input.txt");
                    break;
                }
                case 3: {
                    return;
                }
            }
            System.out.println("1- Показати на екран ; 2 - Записати в файл;  3 - Записати час ");
            int choice2 = scan.nextInt();
            switch (choice2) {
                case 1: {
                    System.out.println("Даний масив:");
                    assert original != null;
                    printArray(original);

                    long startTime = System.currentTimeMillis();
                    int[] subArr1 = new int[original.length / 2];
                    int[] subArr2 = new int[original.length - original.length / 2];
                    System.arraycopy(original, 0, subArr1, 0, original.length / 2);
                    System.arraycopy(original, original.length / 2, subArr2, 0, original.length - original.length / 2);

                    OperationsMerge runner1 = new OperationsMerge(subArr1);
                    OperationsMerge runner2 = new OperationsMerge(subArr2);
                    runner1.start();
                    runner2.start();
                    runner1.join();
                    runner2.join();
                    result = finalMerge(runner1.getInternal(), runner2.getInternal());
                    long stopTime = System.currentTimeMillis();

                    System.out.println("\nПосортаваний масив:");
                    printArray(result);
                    System.out.println("\nЧас виконання " + (stopTime - startTime) + " мілісекунд");
                    break;
                }
                case 2: {
                    long startTime = System.currentTimeMillis();
                    assert original != null;
                    int[] subArr1 = new int[original.length / 2];
                    int[] subArr2 = new int[original.length - original.length / 2];
                    System.arraycopy(original, 0, subArr1, 0, original.length / 2);
                    System.arraycopy(original, original.length / 2, subArr2, 0, original.length - original.length / 2);

                    OperationsMerge runner1 = new OperationsMerge(subArr1);
                    OperationsMerge runner2 = new OperationsMerge(subArr2);
                    runner1.start();
                    runner2.start();
                    runner1.join();
                    runner2.join();
                    result = finalMerge(runner1.getInternal(), runner2.getInternal());
                    long stopTime = System.currentTimeMillis();

                    writeToFile(result, "input.txt");
                    System.out.println("\nЧас виконання " + (stopTime - startTime) + " мілісекунд");
                    break;
                }
                case 3: {
                    long startTime = System.currentTimeMillis();
                    int[] subArr1 = new int[original.length / 2];
                    int[] subArr2 = new int[original.length - original.length / 2];
                    System.arraycopy(original, 0, subArr1, 0, original.length / 2);
                    System.arraycopy(original, original.length / 2, subArr2, 0, original.length - original.length / 2);

                    OperationsMerge runner1 = new OperationsMerge(subArr1);
                    OperationsMerge runner2 = new OperationsMerge(subArr2);
                    runner1.start();
                    runner2.start();
                    runner1.join();
                    runner2.join();
                    result = finalMerge(runner1.getInternal(), runner2.getInternal());
                    long stopTime = System.currentTimeMillis();

                    System.out.println("\nЧас виконання " + (stopTime - startTime) + " мілісекунд");
                    break;
                }
            }
        }
    }
}