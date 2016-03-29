package jfxtras.labs.icalendar.parameters;

import jfxtras.labs.icalendar.parameters.Range.RecurrenceIdentifier;

/**
 * RANGE
 * Recurrence Identifier Range
 * RFC 5545, 3.2.13, page 23
 * 
 * To specify the effective range of recurrence instances from
 *  the instance specified by the recurrence identifier specified by
 *  the property.
 * 
 * Example:
 * RECURRENCE-ID;RANGE=THISANDFUTURE:19980401T133000Z
 * 
 * @author David Bal
 *
 */
public class Range extends ParameterBase<Range, RecurrenceIdentifier>
{
    public Range()
    {
        super();
    }
  
    public Range(String content)
    {
        super(content);
    }

    public Range(Range source)
    {
        super(source);
    }
    
    public enum RecurrenceIdentifier
    {
        THIS_AND_FUTURE ("THISANDFUTURE"),
        THIS_AND_PRIOR ("THISANDPRIOR"); // "THISANDPRIOR" is deprecated by this revision of iCalendar and MUST NOT be generated by applications.
        
        private String name;
        @Override public String toString() { return name; }
        RecurrenceIdentifier(String name)
        {
            this.name = name;
        }
    }
}