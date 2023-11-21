package alex.music;

import alex.music.file.Action;
import alex.music.file.Prophecy;
import alex.music.file.Song;
import alex.music.file.Speech;
import org.junit.jupiter.api.Test;

class musicFileIdentifierTest {

  @Test
  void detectType() {
    assert(MusicFileIdentifier.determineType("15-10-23 z Кто где будет.mp3") instanceof Action);
    assert(MusicFileIdentifier.determineType("13-08-23 x Пророчество 1.mp3") instanceof Prophecy);
    assert(MusicFileIdentifier.determineType("13-08-23 Благодарю тебя Спаситель - Нечитайло.mp3") instanceof Song);
    assert(MusicFileIdentifier.determineType("27-08-23-в 01 Петр Врлм.mp3") instanceof Speech);
  }
}