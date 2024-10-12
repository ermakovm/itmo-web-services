
package ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deleteMovie complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deleteMovie"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="deleteId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteMovie", propOrder = {
        "deleteId"
})
public class DeleteMovie {

    protected Long deleteId;

    /**
     * Gets the value of the deleteId property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDeleteId() {
        return deleteId;
    }

    /**
     * Sets the value of the deleteId property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDeleteId(Long value) {
        this.deleteId = value;
    }

}
