package tech.lacambra.fastnote;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RegisterForReflection
public class Note {
  private LocalDateTime createdOn;
  private String text;

  public Note() {
    createdOn = LocalDateTime.now();
  }

  public Note(String text) {
    this();
    this.text = text;
  }

  public String getCreatedOn() {
    return createdOn.format(DateTimeFormatter.ISO_DATE) + "";
  }

  public LocalDateTime getCreatedOnAsTime() {
    return createdOn;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  int isBefore(Note note) {

    return createdOn.isBefore(note.createdOn) ? -1 : 1;

  }

  @Override
  public String toString() {
    return "Note{" +
        "createdOn=" + createdOn +
        ", text='" + text + '\'' +
        '}';
  }
}
