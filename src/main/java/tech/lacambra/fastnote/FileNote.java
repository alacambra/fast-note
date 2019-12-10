package tech.lacambra.fastnote;

import java.util.Base64;

public class FileNote {

  private String name;
  private String type;
  private String encoding;
  private byte[] content;

  private FileNote(String type, String encoding, byte[] content, String name) {
    this.type = type;
    this.encoding = encoding;
    this.content = content;

    if (name == null) {
      name = "no-name";
    }

    this.name = name;
  }

  public static FileNote createFileNoteIfValid(String payload, String name) {

    if (payload == null) {
      return null;
    }

    String[] result = FileNote.getParts(payload);

    if (result.length == 4) {
      try {
        return new FileNote(result[1], result[2], Base64.getDecoder().decode(result[3]), name);
      } catch (IllegalArgumentException e) {
        return null;
      }
    }

    return null;
  }

  public String getType() {
    return type;
  }

  public String getEncoding() {
    return encoding;
  }

  public byte[] getContent() {
    return content;
  }

  static String[] getParts(String payload) {
    return payload.split("data:|;|,");
  }

  public String getName() {
    return name;
  }
}
