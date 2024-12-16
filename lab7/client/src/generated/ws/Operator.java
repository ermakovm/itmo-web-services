
package ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for operator.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="operator"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="LIKE"/&gt;
 *     &lt;enumeration value="EQ"/&gt;
 *     &lt;enumeration value="GTE"/&gt;
 *     &lt;enumeration value="LTE"/&gt;
 *     &lt;enumeration value="GT"/&gt;
 *     &lt;enumeration value="LT"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "operator")
@XmlEnum
public enum Operator {

    LIKE,
    EQ,
    GTE,
    LTE,
    GT,
    LT;

    public String value() {
        return name();
    }

    public static Operator fromValue(String v) {
        return valueOf(v);
    }

}
