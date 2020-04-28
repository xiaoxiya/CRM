package chapter01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xiaoxiya
 * @date 2019/12/15 22:58
 * @describe 编写程序使它读入一些include语句修饰的文件并输出这个文件
 */
public class Item014 {

    private static Set<String> readFiles = new HashSet<>();
    private static final String INCLUDE = "#include";

    private static String readFile(String filename) {
        filename = filename.trim();
        if (readFiles.contains(filename)) {
            return "#######Error to read "+ filename + "#\n";
        }

        BufferedReader reader = null;

        try {
            readFiles.add(filename);
            reader = new BufferedReader(new FileReader(filename));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                int includeIndex = line.indexOf(INCLUDE);
                if (includeIndex >= 0) {
                    String file = line.substring(includeIndex + INCLUDE.length());
                    //补全绝对路径
                    StringBuilder file1 = new StringBuilder();
                    String path = System.getProperty("user.dir");
                    file1.append(path + "\\DataStructuresAndAlgorithmAnalysis-InJava\\src\\chapter01\\extrafile\\");
                    file1.append(file.trim());
                    line = readFile(file1.toString());
                    sb.append(line);
                } else {
                    sb.append(line + "\n");
                }
            }
            readFiles.remove(filename);
            return sb.toString();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String filename = "D:\\IdeaWorkSpace\\Java-base-learn\\DataStructuresAndAlgorithmAnalysis-InJava\\src\\chapter01\\extrafile\\a.txt";
        System.out.println("Read a.txt-------->");
        String content = readFile(filename);
        System.out.println(content);

        filename = "D:\\IdeaWorkSpace\\Java-base-learn\\DataStructuresAndAlgorithmAnalysis-InJava\\src\\chapter01\\extrafile\\d.txt";
        System.out.println("Read d.txt-------->");
        content = readFile(filename);
        System.out.println(content);
    }
}
