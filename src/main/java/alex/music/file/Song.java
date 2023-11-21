package alex.music.file;

import alex.music.MusicFile;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.IOException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Song extends MusicFile {

  String composition;

  @Override
  public String getRegex() {
    return MusicFile.getStartRegex() + " (.+) - (.+)\\.mp3$";
  }

  @Override
  protected void setAllTheAttributes(String... args) {
    super.setAttributesByDefault(args);
    this.setComposition(args[4]);
    this.setTitle(args[5]);
  }

  @Override
  protected Mp3File createMp3FileWithAllTheAttributes(String sourcePath, String nameFile)
      throws InvalidDataException, UnsupportedTagException, IOException {
    Mp3File mp3File = super.createBaseMp3File(sourcePath, nameFile);
    ID3v2 id3v2 = mp3File.getId3v2Tag();
    mp3File.setId3v2Tag(id3v2);
    id3v2.setTitle(this.getTitle() + " - " + this.getComposition());
    id3v2.setAlbum("Песни (" + this.getCreationDate().getYear() + ")");
    id3v2.setArtist(this.getTitle());
    return mp3File;
  }

  @Override
  protected String determineTheNameOfTheMusicFile(MusicFile musicFile) {
    return MusicFile.toAlphabetic(this.getCreationDate().getMonthValue())
        + this.getCreationDate().getDayOfMonth()
        + " "
        + (this.isEvening() ? "e " : "")
        + "s "
        + this.getTitle()
        + " - "
        + this.getComposition()
        + this.toStringCreationDate();
  }

  @Override
  protected String defineASpecialPath(String savePath, MusicFile musicFile) {
    return savePath + "//Песни//" + this.getTitle() + "//";
  }

}


