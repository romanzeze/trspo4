import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Stream {
    void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; ++i) L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    void sort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            sort(arr, l, m);
            sort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    static void printArray(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) System.out.print(arr[i] + " ");
        System.out.println();
    }

    static void writeToFile(int[] arr, String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName);
            for (int i = 0; i < arr.length; i++) {
                fw.write(arr[i] + " ");
            }
            System.out.println("Запис успішно виконано");
            fw.close();
        } catch (IOException e) {
            System.out.println("Виникла помилка");
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
            for (int i = 0; i < size; i++) arr[i] = s1.nextInt();
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

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] arr = new int[0];
        while (true) {
            System.out.println("Виберіть метод:\n 1 - згенерувати райндомний; \n 2 - записати в файл;\n 3 - вихід із програми");
            int choice = scan.nextInt();
            switch (choice) {
                case 1: {
                    arr = generateInput();
                    break;
                }
                case 2: {
                    arr = readFromFile("input.txt");
                    break;
                }
                case 3: {
                    return;
                }
            }
            System.out.println(" 1 - Показати на екран ;\n 2 - Записати в файл;\n 3 - Записати час; ");
            int choice2 = scan.nextInt();
            switch (choice2) {
                case 1: {
                    System.out.println("Даний масив:");
                    printArray(arr);
                    Stream ob = new Stream();
                    long startTime = System.currentTimeMillis();
                    ob.sort(arr, 0, arr.length - 1);
                    long endTime = System.currentTimeMillis();
                    System.out.println("\nПосортаваний масив:");
                    printArray(arr);
                    System.out.println("\nЧас виконання " + (endTime - startTime) + " мілісекунд");
                    break;
                }
                case 2: {
                    Stream ob = new Stream();
                    long startTime = System.currentTimeMillis();
                    ob.sort(arr, 0, arr.length - 1);
                    long endTime = System.currentTimeMillis();
                    writeToFile(arr, "output.txt");
                    System.out.println("\nЧас виконання " + (endTime - startTime) + " мілісекунд");
                    break;
                }
                case 3: {
                    Stream ob = new Stream();
                    long startTime = System.currentTimeMillis();
                    ob.sort(arr, 0, arr.length - 1);
                    long endTime = System.currentTimeMillis();
                    System.out.println("\nЧас виконання " + (endTime - startTime) + " мілісекунд");
                    break;
                }

            }

        }
    }
}



