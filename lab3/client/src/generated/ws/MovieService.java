package ws;

import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceFeature;

import javax.xml.namespace.QName;
import java.net.URL;

/**
 * This class was generated by Apache CXF 4.0.5
 * Generated source version: 4.0.5
 *
 */
@WebServiceClient(name = "MovieService",
        wsdlLocation = "service.wsdl",
                  targetNamespace = "http://service.lab3.ws.itmo.mermakov.info/")
public class MovieService extends Service {

    public static final URL WSDL_LOCATION;

    public static final QName SERVICE = new QName("http://service.lab3.ws.itmo.mermakov.info/", "MovieService");
    public static final QName MovieWebServicePort = new QName("http://service.lab3.ws.itmo.mermakov.info/", "MovieWebServicePort");
    static {
        URL url = MovieService.class.getResource("service.wsdl");
        if (url == null) {
            url = MovieService.class.getClassLoader().getResource("service.wsdl");
        }
        if (url == null) {
            java.util.logging.Logger.getLogger(MovieService.class.getName())
                .log(java.util.logging.Level.INFO,
                        "Can not initialize the default wsdl from {0}", "service.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public MovieService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public MovieService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MovieService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public MovieService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public MovieService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public MovieService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns MovieWebService
     */
    @WebEndpoint(name = "MovieWebServicePort")
    public MovieWebService getMovieWebServicePort() {
        return super.getPort(MovieWebServicePort, MovieWebService.class);
    }

    /**
     *
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MovieWebService
     */
    @WebEndpoint(name = "MovieWebServicePort")
    public MovieWebService getMovieWebServicePort(WebServiceFeature... features) {
        return super.getPort(MovieWebServicePort, MovieWebService.class, features);
    }

}
