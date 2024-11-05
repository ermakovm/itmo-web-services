
package ws;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for searchKey.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="searchKey"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="TITLE"/&gt;
 *     &lt;enumeration value="STUDIO"/&gt;
 *     &lt;enumeration value="DIRECTOR"/&gt;
 *     &lt;enumeration value="YEAR"/&gt;
 *     &lt;enumeration value="DURATION"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "searchKey")
@XmlEnum
public enum SearchKey {

    TITLE,
    STUDIO,
    DIRECTOR,
    YEAR,
    DURATION;

    public String value() {
        return name();
    }

    public static SearchKey fromValue(String v) {
        return valueOf(v);
    }

}
