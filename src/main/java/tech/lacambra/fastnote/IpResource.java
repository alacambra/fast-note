package tech.lacambra.fastnote;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
