package jfxtras.labs.icalendarfx.components;

import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfxtras.labs.icalendarfx.properties.PropertyEnum;
import jfxtras.labs.icalendarfx.properties.component.descriptive.Description;

/**
 * VJOURNAL
 * Journal Component
 * RFC 5545, 3.6.3, page 57
 * 
 *    A "VJOURNAL" calendar component is a grouping of
      component properties that represent one or more descriptive text
      notes associated with a particular calendar date.  The "DTSTART"
      property is used to specify the calendar date with which the
      journal entry is associated.  Generally, it will have a DATE value
      data type, but it can also be used to specify a DATE-TIME value
      data type.  Examples of a journal entry include a daily record of
      a legislative body or a journal entry of individual telephone
      contacts for the day or an ordered list of accomplishments for the
      day.  The "VJOURNAL" calendar component can also be used to
      associate a document with a calendar date.

      The "VJOURNAL" calendar component does not take up time on a
      calendar.  Hence, it does not play a role in free or busy time
      searches -- it is as though it has a time transparency value of
      TRANSPARENT.  It is transparent to any such searches.

      The "VJOURNAL" calendar component cannot be nested within another
      calendar component.  However, "VJOURNAL" calendar components can
      be related to each other or to a "VEVENT" or to a "VTODO" calendar
      component, with the "RELATED-TO" property.

   Example:  The following is an example of the "VJOURNAL" calendar
      component:

       BEGIN:VJOURNAL
       UID:19970901T130000Z-123405@example.com
       DTSTAMP:19970901T130000Z
       DTSTART;VALUE=DATE:19970317
       SUMMARY:Staff meeting minutes
       DESCRIPTION:1. Staff meeting: Participants include Joe\,
         Lisa\, and Bob. Aurora project plans were reviewed.
         There is currently no budget reserves for this project.
         Lisa will escalate to management. Next meeting on Tuesday.\n
        2. Telephone Conference: ABC Corp. sales representative
         called to discuss new printer. Promised to get us a demo by
         Friday.\n3. Henry Miller (Handsoff Insurance): Car was
         totaled by tree. Is looking into a loaner car. 555-2323
         (tel).
       END:VJOURNAL
 *
 * @author David Bal
 *
 */
public class VJournal extends VComponentDisplayableBase<VJournal>
{
    @Override
    public VComponentEnum componentType()
    {
        return VComponentEnum.VJOURNAL;
    }
    
    /*
     * CONSTRUCTORS
     */
    public VJournal() { super(); }
    
    public VJournal(String contentLines)
    {
        super(contentLines);
    }
    
    /** Copy constructor */
    public VJournal(VJournal source)
    {
        super(source);
    }

    /**
     * DESCRIPTION:
     * RFC 5545 iCalendar 3.8.1.12. page 84
     * This property provides a more complete description of the
     * calendar component than that provided by the "SUMMARY" property.
     * Example:
     * DESCRIPTION:Meeting to provide technical review for "Phoenix"
     *  design.\nHappy Face Conference Room. Phoenix design team
     *  MUST attend this meeting.\nRSVP to team leader.
     *  
     *  VJournal can have multiple description properties.
     */
    public ObservableList<Description> getDescriptions() { return descriptions; }
    private ObservableList<Description> descriptions;
    public void setDescriptions(ObservableList<Description> descriptions) { this.descriptions = descriptions; }
    public VJournal withDescriptions(ObservableList<Description> descriptions) { setDescriptions(descriptions); return this; }
    public VJournal withDescriptions(String...descriptions)
    {
        Arrays.stream(descriptions).forEach(c -> PropertyEnum.DESCRIPTION.parse(this, c));
        return this;
    }
    public VJournal withDescriptions(Description...descriptions)
    {
        if (getDescriptions() == null)
        {
            setDescriptions(FXCollections.observableArrayList(descriptions));
        } else
        {
            getDescriptions().addAll(descriptions);
        }
        return this;
    }
    
    /** Parse content lines into calendar component object */
    public static VJournal parse(String contentLines)
    {
        VJournal component = new VJournal();
        component.parseContent(contentLines);
        return component;
    }
}
