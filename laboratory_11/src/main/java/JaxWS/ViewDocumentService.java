package JaxWS;

import Beans.UploadBean;
import Interfaces.UploadInterface;
import Interfaces.UserInterface;
import Services.UploadService;


import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.inject.Inject;
import javax.jws.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebService(serviceName="ViewDocumentService")
@HandlerChain(file="JaxWS.handler-chain.xml")
public class ViewDocumentService {
    @Inject
    private UploadInterface uploadService;
    @WebMethod(operationName="view")
    public String[] operation(@WebParam(name = "id") Long id) {
        try
        {
            List<UploadBean> uploadBeans = uploadService.getUploads();
            ArrayList<String> files = new ArrayList<>();
            for (UploadBean uploadBean:uploadBeans){
                if(id!=null && id != uploadBean.getEntity().getId().longValue())
                    continue;
                files.add(uploadBean.getEntity().getFileName());
            }
            if(files.size() == 0) return null;
            String[] filesArr = new String[files.size()];
            filesArr = files.toArray(filesArr);
            return filesArr;
        }
        catch (Exception ex)
        {
            return null;
        }
    }
}