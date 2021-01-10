package JAXRS;

import Beans.UploadBean;
import Entities.Upload;
import Interfaces.UploadInterface;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/add_document") // --> Resource Identifier
public class AddDocumentService {
    @Inject
    private UploadInterface uploadService;

    @POST
    @Consumes (MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPerson(Upload upload) {
        try {
            boolean response = uploadService.addUpload(upload);
            if(!response){
                return Response.status(404).entity(upload).type(MediaType.APPLICATION_JSON).build();
            }
            return Response.ok().entity(upload).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(404).build();
//            throw new WebApplicationException
//                    (e, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
