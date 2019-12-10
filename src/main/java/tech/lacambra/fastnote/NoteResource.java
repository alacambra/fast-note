package tech.lacambra.fastnote;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    note.loadFileNote();
    notesStore.add(note);

    return notesStore.getNotes();
  }

  @GET
  @Path("file/{noteId}")
  public Response uploadFile(@PathParam("noteId") String noteId) {

    FileNote fileNote = notesStore.getNote(noteId)
        .map(Note::getFileNote)
        .orElse(null);

    if (fileNote == null) {
      return Response.ok().build();
    }

    return Response.ok(fileNote.getContent())
        .header("Content-Disposition", "attachment; filename=" + fileNote.getName())
        .build();
  }


  @DELETE
  public void clean() {
    notesStore.cleanUp();
  }


}
