import Artefacts.ViewDocumentService;
import Artefacts.ViewDocumentService_Service;

import java.util.List;

public class ClientJaxWS {
    public static void main(String[] args) {
        try {
            ClientJaxWS client = new ClientJaxWS();
            client.doTest(args);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void doTest(String[] args) {
        try {
            ViewDocumentService_Service service = new ViewDocumentService_Service();
            ViewDocumentService port = service.getViewDocumentService();
            System.out.println("Invoking the view operation on the port.");

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(System.in));
            String id_s = reader.readLine();
            Long id;
            try{
                id = new Long(id_s);
            }catch(Exception e){
                id=null;
            }

            List<String> response = port.view(id);
            System.out.println(response);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
