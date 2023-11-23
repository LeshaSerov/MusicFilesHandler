package alex.music;

import alex.music.file.Action;
import alex.music.file.Common;
import alex.music.file.Prophecy;
import alex.music.file.Song;
import alex.music.file.Speech;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public final class MusicFileIdentifier {

  public static MusicFile determineType(String nameFile) {
    List<MusicFile> list = List.of(new Verse(), new Song(), new Common());
    Optional<MusicFile> optionalMusicFile = list.stream()
        .filter(x -> Pattern.matches(x.getRegex(), nameFile))
        .findFirst();
    if (optionalMusicFile.isPresent()) {
      MusicFile musicFile = optionalMusicFile.get();
      Matcher matcher = Pattern.compile(musicFile.getRegex()).matcher(nameFile);
      if (matcher.find()) {
        String[] attributes = IntStream
            .rangeClosed(1, matcher.groupCount())
            .mapToObj(matcher::group)
            .toArray(String[]::new);
        musicFile.setAllTheAttributes(attributes);
        return musicFile;
      } else {
        throw new RuntimeException("Could not determine file type attributes: " + nameFile);
      }
    } else {
      throw new RuntimeException("Could not determine file type: " + nameFile);
    }
  }
}
