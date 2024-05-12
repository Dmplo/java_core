package HomeWork.Lesson_5.Task_1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Backup {

    private static final String SEPARATOR = System.getProperty("file.separator");

    public static void main(String[] args) throws IOException {
        String backupPath = ".." + SEPARATOR + "backup";
        checkAndMakeDirectory(backupPath);
        makeBackup(new File("."), backupPath);
    }

    static void makeBackup(File file, String path) throws IOException {
        if (file.listFiles() != null) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    checkAndMakeDirectory(path + SEPARATOR + files[i].getName());
                    makeBackup(files[i], path + SEPARATOR + files[i].getName());
                }
                if (files[i].isFile()) {
                    makeBackup(files[i], path);
                }
            }
        } else {
            copyFile(file, path);
        }
    }

    static void checkAndMakeDirectory(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    private static void copyFile(File file, String path) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(path + SEPARATOR + file.getName())) {
            int c;
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                while ((c = fileInputStream.read()) != -1) {
                    fileOutputStream.write(c);
                }
            }
        }
    }

}
