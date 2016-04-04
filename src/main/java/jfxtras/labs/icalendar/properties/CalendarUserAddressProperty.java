package jfxtras.labs.icalendar.properties;

import java.net.URI;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import jfxtras.labs.icalendar.parameters.CommonName;
import jfxtras.labs.icalendar.parameters.DirectoryEntryReference;
import jfxtras.labs.icalendar.parameters.ParameterEnum;
import jfxtras.labs.icalendar.properties.component.relationship.Attendee;
import jfxtras.labs.icalendar.properties.component.relationship.Organizer;

/**
 * Abstract class for properties with a CAL-ADDRESS value.
 * The value is stored as a URI object
 * 
 * CAL-ADDRESS
 * Calendar User Address
 * RFC 5545, 3.3.3, page 31
 *  
 * This value type is used to identify properties that contain a calendar user address.
 * The email address of a calendar user.
 * 
 * Example: mailto:jane_doe@example.com
 * 
 * @author David Bal
 *
 * @param <T>
 * @param <U>
 * 
 * concrete subclasses
 * @see Organizer
 * @see Attendee
 */
public abstract class CalendarUserAddressProperty<T> extends LanguageProperty<T, URI>
{
    /**
     * CN
     * Common Name
     * RFC 5545, 3.2.2, page 15
     * 
     * To specify the common name to be associated with the calendar user specified by the property.
     * 
     * Example:
     * ORGANIZER;CN="John Smith":mailto:jsmith@example.com
     */
    public CommonName getCommonName() { return (commonName == null) ? null : commonName.get(); }
    public ObjectProperty<CommonName> commonNameProperty()
    {
        if (commonName == null)
        {
            commonName = new SimpleObjectProperty<>(this, ParameterEnum.COMMON_NAME.toString());
        }
        return commonName;
    }
    private ObjectProperty<CommonName> commonName;
    public void setCommonName(CommonName commonName)
    {
        if (commonName != null)
        {
            commonNameProperty().set(commonName);
        }
    }
    public void setCommonName(String value) { setCommonName(new CommonName(value)); }
    public T withCommonName(CommonName commonName) { setCommonName(commonName); return (T) this; }
    public T withCommonName(String content) { ParameterEnum.COMMON_NAME.parse(this, content); return (T) this; }    

    /**
     * DIR
     * Directory Entry Reference
     * RFC 5545, 3.2.6, page 18
     * 
     * To specify reference to a directory entry associated with
     *     the calendar user specified by the property.
     * 
     * Example:
     * ORGANIZER;DIR="ldap://example.com:6666/o=ABC%20Industries,
     *  c=US???(cn=Jim%20Dolittle)":mailto:jimdo@example.com
     */
    public DirectoryEntryReference getDirectoryEntryReference() { return (directoryEntryReference == null) ? null : directoryEntryReference.get(); }
    public ObjectProperty<DirectoryEntryReference> directoryEntryReferenceProperty()
    {
        if (directoryEntryReference == null)
        {
            directoryEntryReference = new SimpleObjectProperty<>(this, ParameterEnum.COMMON_NAME.toString());
        }
        return directoryEntryReference;
    }
    private ObjectProperty<DirectoryEntryReference> directoryEntryReference;
    public void setDirectoryEntryReference(DirectoryEntryReference directoryEntryReference)
    {
        if (directoryEntryReference != null)
        {
            directoryEntryReferenceProperty().set(directoryEntryReference);
        }
    }
    public void setDirectoryEntryReference(String value) { setDirectoryEntryReference(new DirectoryEntryReference(value)); }
    public T withDirectoryEntryReference(DirectoryEntryReference directoryEntryReference) { setDirectoryEntryReference(directoryEntryReference); return (T) this; }
    public T withDirectoryEntryReference(String content) { ParameterEnum.COMMON_NAME.parse(this, content); return (T) this; }    

    
    /*
     * CONSTRUCTORS
     */    
    protected CalendarUserAddressProperty(String propertyString)
    {
        super(propertyString);
    }

    // copy constructor
    public CalendarUserAddressProperty(CalendarUserAddressProperty<T> property)
    {
        super(property);
    }
    
    public CalendarUserAddressProperty(URI value)
    {
        setValue(value);
    }
    
    public CalendarUserAddressProperty() { super(); }
}
