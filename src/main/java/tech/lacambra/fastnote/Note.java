package tech.lacambra.fastnote;

import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.json.bind.annotation.JsonbTransient;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RegisterForReflection
public class Note {
  private LocalDateTime createdOn;
  private String id;
  private String text;
  private String content;
  private boolean isAttachment;

  @JsonbTransient
  private FileNote fileNote;

  public Note() {
    createdOn = LocalDateTime.now();
    id = UUID.randomUUID().toString();
  }

  public Note(String text) {
    this();
    this.text = text;
  }

  public String getContent() {
    return content;
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
    return createdOn.isBefore(note.createdOn) ? 1 : -1;
  }

  public String getId() {
    return id;
  }

  public void loadFileNote() {
    this.fileNote = FileNote.createFileNoteIfValid(content, text);
    if (fileNote != null) {
      content = "true";
    } else {
      content = "false";
    }
  }

  public boolean hasId(String id) {
    return this.id.equals(id);
  }

  public void setContent(String content) {
    this.content = content;
  }

  public FileNote getFileNote() {
    return fileNote;
  }

  @Override
  public String toString() {
    return "Note{" +
        "createdOn=" + createdOn +
        ", text='" + text + '\'' +
        '}';
  }
}
