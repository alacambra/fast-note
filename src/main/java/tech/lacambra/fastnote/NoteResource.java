package tech.lacambra.fastnote;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@Path("note")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NoteResource {

  private NotesStore notesStore;

  public NoteResource() {
    this.notesStore = new NotesStore();
  }

  @Path("ping")
  @GET
  public List<String> ping() {
    return Arrays.asList("pong");
  }

  @GET
  public List<Note> getNotes() {
    return notesStore.getNotes();
  }

  @POST
  public List<Note> saveNote(Note note) {

    notesStore.add(note);

    return notesStore.getNotes();
  }


  @DELETE
  public void clean() {
    notesStore.cleanUp();
  }


}
