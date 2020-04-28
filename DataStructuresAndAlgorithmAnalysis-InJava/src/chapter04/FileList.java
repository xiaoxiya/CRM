package chapter04;

import java.io.File;

/**
 * @author xiaoxiya
 * @date 2020/4/18 16:03
 * @describe 第三版第四章第十题，列出一个目录中所有的文件和他们的大小
 */
public class FileList {
    public void list(File file) {
        list(file, 0);
    }

    private void list(File file, int depth) {
        printName(file, depth);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                list(file1, depth +1);
            }
        }
    }

    private void printName(File file, int depth) {
        String name = file.getName();
        for (int i = 0; i < depth; i++) {
            System.out.print(" ");
        }
        if (file.isDirectory()) {
            System.out.println("Dir: " + name);
        } else {
            System.out.println(file.getName() + " " + file.length());
        }
    }

    public static void main(String[] args) {
        FileList list = new FileList();
        File f = new File("C:\\iNode");
        list.list(f);
    }
}
