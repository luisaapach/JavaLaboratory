package JAXRS;

import Entities.Upload;
import Interfaces.UploadInterface;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Path("/view_document")
@RolesAllowed("admin")
public class ViewDocumentService {
    @Inject
    private UploadInterface uploadService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response getDocument(@PathParam("id") String id) {
        try {
            Upload upload = uploadService.findUpload(new BigDecimal(id));
            if(upload==null){
                throw new Exception();
            }
            return Response.ok().entity(upload).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(404).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response getDocuments() {
        try {
            List<Upload> uploads = uploadService.getUploadsEntities();
            final GenericEntity<List<Upload>> entity
                    = new GenericEntity<List<Upload>>(uploads) {};

            return Response.ok().entity(entity).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
//            throw new WebApplicationException(e,
//                    Response.Status.INTERNAL_SERVER_ERROR);
            return Response.status(404).build();
        }
    }
}
