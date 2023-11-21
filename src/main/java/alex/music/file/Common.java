package alex.music.file;

import alex.music.MusicFile;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.IOException;
import java.time.LocalDate;

public class Common extends MusicFile {

  @Override
  protected String getRegex() {
    return "()(.+)(?:\\.mp3)$";
  }

  @Override
  protected void setAllTheAttributes(String... args) {
    this.setTitle(args[1]);
    this.setCreationDate(LocalDate.now());
  }

  @Override
  protected Mp3File createMp3FileWithAllTheAttributes(String sourcePath, String nameFile)
      throws InvalidDataException, UnsupportedTagException, IOException {
    return new Mp3File(sourcePath + "//" + nameFile);
  }

  @Override
  protected String determineTheNameOfTheMusicFile(MusicFile musicFile) {
    return musicFile.getTitle();
  }

  @Override
  protected String defineASpecialPath(String savePath, MusicFile musicFile) {
    return savePath + "//Непонятные файлы//";
  }

  @Override
  protected String[] getThePathsToSave(String savePath, MusicFile musicFile) {
    return new String[]{defineASpecialPath(savePath, musicFile)};
  }
}
