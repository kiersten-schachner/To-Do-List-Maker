import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class FileUtil {
    public static boolean saveToFile(List<Activity> list, File file) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            printWriter.println((i + 1) + ":");
            printWriter.println(list.get(i).toString());
        }
        printWriter.close();
        return true;
    }
}