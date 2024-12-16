
package ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for movie complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="movie"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="director" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="studio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "movie", propOrder = {
        "director",
        "duration",
        "id",
        "studio",
        "title",
        "year"
})
public class Movie {

    protected String director;
    protected Short duration;
    protected Long id;
    protected String studio;
    protected String title;
    protected Short year;

    /**
     * Gets the value of the director property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDirector() {
        return director;
    }

    /**
     * Sets the value of the director property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDirector(String value) {
        this.director = value;
    }

    /**
     * Gets the value of the duration property.
     *
     * @return possible object is
     * {@link Short }
     */
    public Short getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     *
     * @param value allowed object is
     *              {@link Short }
     */
    public void setDuration(Short value) {
        this.duration = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     * {@link Long }
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *              {@link Long }
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the studio property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getStudio() {
        return studio;
    }

    /**
     * Sets the value of the studio property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setStudio(String value) {
        this.studio = value;
    }

    /**
     * Gets the value of the title property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the year property.
     *
     * @return possible object is
     * {@link Short }
     */
    public Short getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     *
     * @param value allowed object is
     *              {@link Short }
     */
    public void setYear(Short value) {
        this.year = value;
    }

}
