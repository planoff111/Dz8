package org.example;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        createFoldersAndFiles();
        traverseFoldersAndPrintContent();

    }
    public static void createFoldersAndFiles() {
        String resourcesPath = "src/main/resources/";
        String[] folderNames = {"folder1", "folder2", "folder3", "folder4", "folder5", "folder6", "folder7", "folder8"};

        String previousFolderPath = resourcesPath;
        for (int i = 0; i < folderNames.length; i++) {
            String folderPath = previousFolderPath + folderNames[i] + "/";
            File folder = new File(folderPath);
            folder.mkdirs();

            if (i % 2 == 1) {
                Scanner scanner = new Scanner(System.in);
                String filePath = folderPath + "/file.txt";
                System.out.println("Введіть фразу для збереження в файлі: ");
                String fileContent = scanner.nextLine();
                writeFile(filePath, fileContent);
            }
            previousFolderPath = folderPath;
        }
    }

    public static void writeFile(String filePath, String content) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void traverseFoldersAndPrintContent() {
        String resourcesPath = "src/main/resources/";
        File resourcesFolder = new File(resourcesPath);
        traverseFolder(resourcesFolder);
    }

    public static void traverseFolder(File folder) {
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    traverseFolder(file);
                } else if (file.isFile()) {
                    System.out.println("Файл: " + file.getName() + "/" + folder.getName());
                    System.out.println("Вміст:");
                    try {
                        Scanner scanner = new Scanner(file);
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            System.out.println(line);
                        }
                        scanner.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
