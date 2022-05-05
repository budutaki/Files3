import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    public static void main(String[] args) {

        openZip("/Users/Mi/IdeaProjects/Files/Games/savedgames/saved.zip", "/Users/Mi/IdeaProjects/Files/Games/savedgames/saved");
        System.out.println(openProgress("/Users/Mi/IdeaProjects/Files/Games/savedgames/saved/saved1.txt"));
    }


    public static boolean openZip(String filePath, String dirPath) {
        File dir = new File(dirPath);
        dir.mkdir();

        try (FileInputStream fis = new FileInputStream(filePath);
             ZipInputStream zis = new ZipInputStream(fis)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File file = new File(dirPath, entry.getName());
                FileOutputStream fout = new FileOutputStream(file);
                for (int c = zis.read(); c != -1; c = zis.read()) {
                    fout.write(c);
                }
                fout.flush();
                zis.closeEntry();
                fout.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static GameProgress openProgress(String path){
        GameProgress GP = null;
        try
            (FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
                    Object o = ois.readObject();
                    GP = (GameProgress) o;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return GP;
    }
}