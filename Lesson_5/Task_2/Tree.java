package HomeWork.Lesson_5.Task_2;

import java.io.File;

public class Tree {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        print(new File("."), "", true);
    }

    static void print(File file, String indent, boolean isLast) {
        System.out.print(indent);
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());

        if (file.listFiles() != null) {

            File[] files = file.listFiles();

            int subFilesTotal = 0;
            int subDirTotal = 0;
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    subDirTotal++;
                }
                if (files[i].isFile()) {
                    subFilesTotal++;
                }
            }

            int subDirCounter = 0;
            int subFilesCounter = 0;
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    print(files[i], indent, subDirTotal == ++subDirCounter && i == files.length - 1);
                }
                if (files[i].isFile()) {
                    print(files[i], indent, subFilesTotal == ++subFilesCounter && i == files.length - 1);
                }
            }
        }
    }

}
