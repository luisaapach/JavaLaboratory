package JaxWS;

import Entities.Upload;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;
import javax.xml.ws.LogicalMessage;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

public class LogicalHandlerImplementation implements LogicalHandler<LogicalMessageContext> {
    @Override
    public boolean handleMessage(LogicalMessageContext context) {
//        Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
//        if(!outboundProperty.booleanValue()){
//            //delegate request message handling private method
//            return handleRequest(context);
//        }
//        return handleResponse(context);
        Boolean outboundProperty = (Boolean)
                context.get (MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outboundProperty.booleanValue()) {
            System.out.println("\nOutbound message:");
        } else {
            System.out.println("\nInbound message:");
        }
        LogicalMessage message = context.getMessage();
        Source payload = message.getPayload();
        System.out.println(payload);
        return true;
    }

    private boolean handleRequest(LogicalMessageContext messageContext){
        JAXBContext jaxbContext;
        try{
            jaxbContext = JAXBContext.newInstance("Entities.Upload");
            Upload upload = (Upload) messageContext.getMessage().getPayload(jaxbContext);


        } catch (JAXBException e) {
            e.printStackTrace();
            return true;
        }
        return true;
    }

    @Override
    public boolean handleFault(LogicalMessageContext context) {
        return false;
    }

    @Override
    public void close(MessageContext context) {

    }
}
