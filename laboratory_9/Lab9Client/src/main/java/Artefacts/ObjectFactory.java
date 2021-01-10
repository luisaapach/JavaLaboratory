
package Artefacts;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the Artefacts package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ViewResponse_QNAME = new QName("http://JaxWS/", "viewResponse");
    private final static QName _View_QNAME = new QName("http://JaxWS/", "view");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: Artefacts
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link View }
     * 
     */
    public View createView() {
        return new View();
    }

    /**
     * Create an instance of {@link ViewResponse }
     * 
     */
    public ViewResponse createViewResponse() {
        return new ViewResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://JaxWS/", name = "viewResponse")
    public JAXBElement<ViewResponse> createViewResponse(ViewResponse value) {
        return new JAXBElement<ViewResponse>(_ViewResponse_QNAME, ViewResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link View }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://JaxWS/", name = "view")
    public JAXBElement<View> createView(View value) {
        return new JAXBElement<View>(_View_QNAME, View.class, null, value);
    }

}
