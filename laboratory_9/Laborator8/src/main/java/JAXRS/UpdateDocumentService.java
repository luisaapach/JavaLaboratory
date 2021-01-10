package JAXRS;

import Entities.Upload;
import Interfaces.UploadInterface;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/update_document")
public class UpdateDocumentService {
    @Inject
    private UploadInterface uploadService;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUpload(Upload upload)
    {
        try {
            uploadService.updateUpload(upload);
            return Response.ok().entity(upload).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(404).build();
        }
    }

}
