
package ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addMovie complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addMovie"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="createRequest" type="{http://service.lab2.ws.itmo.mermakov.info/}changeRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addMovie", propOrder = {
        "createRequest"
})
public class AddMovie {

    protected ChangeRequest createRequest;

    /**
     * Gets the value of the createRequest property.
     *
     * @return
     *     possible object is
     *     {@link ChangeRequest }
     *     
     */
    public ChangeRequest getCreateRequest() {
        return createRequest;
    }

    /**
     * Sets the value of the createRequest property.
     *
     * @param value
     *     allowed object is
     *     {@link ChangeRequest }
     *     
     */
    public void setCreateRequest(ChangeRequest value) {
        this.createRequest = value;
    }

}
