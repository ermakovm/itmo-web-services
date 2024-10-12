
package ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateMovie complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateMovie"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="updateId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="updateRequest" type="{http://service.lab2.ws.itmo.mermakov.info/}changeRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateMovie", propOrder = {
        "updateId",
        "updateRequest"
})
public class UpdateMovie {

    protected Long updateId;
    protected ChangeRequest updateRequest;

    /**
     * Gets the value of the updateId property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUpdateId() {
        return updateId;
    }

    /**
     * Sets the value of the updateId property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUpdateId(Long value) {
        this.updateId = value;
    }

    /**
     * Gets the value of the updateRequest property.
     *
     * @return
     *     possible object is
     *     {@link ChangeRequest }
     *     
     */
    public ChangeRequest getUpdateRequest() {
        return updateRequest;
    }

    /**
     * Sets the value of the updateRequest property.
     *
     * @param value
     *     allowed object is
     *     {@link ChangeRequest }
     *     
     */
    public void setUpdateRequest(ChangeRequest value) {
        this.updateRequest = value;
    }

}
