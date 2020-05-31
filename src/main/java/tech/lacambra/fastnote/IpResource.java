package tech.lacambra.fastnote;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Path("ip")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IpResource {

  private static final Logger LOGGER = Logger.getLogger(IpResource.class.getName());

  @POST
  public Response saveIp(String received) {

    LOGGER.info("[saveIp] Received: " + received);


    return Response.noContent().build();
  }
}
