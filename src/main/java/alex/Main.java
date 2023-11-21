package alex;

import alex.music.MusicFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

  public static void main(String[] args) {

    String currentPath = "D:\\Обработка записей\\В обработке";
    String savedPath = "D:\\Обработка записей\\Готовые";

    System.out.print("Converting using the standard path?[0/1]: ");
    Scanner in = new Scanner(System.in);
    int flag = in.nextInt();

    if (flag == 0) {
      in.nextLine();
      System.out.print("CurrentPath:");
      currentPath = in.nextLine();

      System.out.print("SavedPath: ");
      savedPath = in.nextLine();

    }
    in.close();

    String finalSavedPath = savedPath;
    String finalCurrentPath = currentPath + "\\";
      try (Stream<Path> paths = Files.walk(Paths.get(finalCurrentPath))) {
        paths.filter(Files::isRegularFile)
            .map(path -> path.toString().replace(finalCurrentPath, ""))
            .forEach(x -> {
              StringBuilder savePath = new StringBuilder(finalSavedPath);
              StringBuilder filePath = new StringBuilder(finalCurrentPath);
              while (x.contains("\\")) {
                savePath.append("\\").append(x, 0, x.indexOf("\\"));
                filePath.append("\\").append(x, 0, x.indexOf("\\"));
                x = x.substring(x.indexOf("\\") + 1);
              }
              MusicFile.createMusicFile(x, filePath.toString(), savePath.toString());
            });
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
//    Thread.sleep(10000);
  }

}
