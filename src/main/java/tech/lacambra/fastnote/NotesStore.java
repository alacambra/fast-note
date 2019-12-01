package tech.lacambra.fastnote;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class NotesStore {

  private static final Set<Note> NOTES;
  private static final Timer TIMER;


  static {
    NOTES = ConcurrentHashMap.newKeySet();
    TIMER = new Timer();

    TIMER.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        NOTES.stream().filter(nh -> LocalDateTime.now().isAfter(nh.getCreatedOnAsTime().plusSeconds(5))).forEach(NOTES::remove);
      }
    }, 1000L, 24 * 3600 * 1000);
  }

  public void add(Note note) {
    NOTES.add(note);
  }

  public List<Note> getNotes() {
    return NOTES.stream().sorted(Note::isBefore).collect(Collectors.toList());
  }

  public void cleanUp() {
    NOTES.clear();
  }


  @Override
  public String toString() {
    return super.toString() + " totalNotes=" + NOTES.size();
  }
}
