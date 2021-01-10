package JAXRS;

import Entities.Upload;
import Interfaces.UploadInterface;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

@Path("/delete_document")
public class DeleteDocumentService {
    @Inject
    private UploadInterface uploadService;
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDocument(@PathParam("id") BigDecimal pid) {
        try {
            Upload upload = uploadService.findUpload(pid);
            if (upload == null) {
                throw new Exception();
            }
            uploadService.deleteUpload(upload);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(404).type(MediaType.APPLICATION_JSON).build();
        }
    }
}
