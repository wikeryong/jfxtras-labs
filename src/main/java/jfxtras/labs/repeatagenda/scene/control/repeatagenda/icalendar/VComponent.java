package jfxtras.labs.repeatagenda.scene.control.repeatagenda.icalendar;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;

import javafx.beans.property.ObjectProperty;
import javafx.util.Callback;
import jfxtras.labs.repeatagenda.scene.control.repeatagenda.ICalendarUtilities.ChangeDialogOptions;
import jfxtras.labs.repeatagenda.scene.control.repeatagenda.ICalendarUtilities.WindowCloseType;
import jfxtras.labs.repeatagenda.scene.control.repeatagenda.icalendar.rrule.RRule;

/** Interface for VEVENT, VTODO, VJOURNAL calendar components. 
 * @param <T> - type of recurrence instance, such as an appointment implementation
 * @see VComponentAbstract
 * @see VEvent
 * */
public interface VComponent<T>
{
    static final long NANOS_IN_DAY = Duration.ofDays(1).toNanos();
    final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd" + "'T'" + "HHmmss");
    final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    final static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmmss");
    
    /**
     * CATEGORIES: RFC 5545 iCalendar 3.8.1.12. page 81
     * This property defines the categories for a calendar component.
     * Example:
     * CATEGORIES:APPOINTMENT,EDUCATION
     * CATEGORIES:MEETING
     */
    String getCategories();
    void setCategories(String value);
    
    /**
     *  COMMENT: RFC 5545 iCalendar 3.8.1.12. page 83
     * This property specifies non-processing information intended
      to provide a comment to the calendar user.
     * Example:
     * COMMENT:The meeting really needs to include both ourselves
         and the customer. We can't hold this meeting without them.
         As a matter of fact\, the venue for the meeting ought to be at
         their site. - - John
     * */
    String getComment();
    void setComment(String value);
    
    /**
     * CREATED: Date-Time Created, from RFC 5545 iCalendar 3.8.7.1 page 136
     * This property specifies the date and time that the calendar information was created.
     * This is analogous to the creation date and time for a file in the file system.
     */
    LocalDateTime getDateTimeCreated();
    void setDateTimeCreated(LocalDateTime dtCreated);
    
    /**
     * DTSTAMP: Date-Time Stamp, from RFC 5545 iCalendar 3.8.7.2 page 137
     * This property specifies the date and time that the instance of the
     * iCalendar object was created
     */
    LocalDateTime getDateTimeStamp();
    void setDateTimeStamp(LocalDateTime dtStamp);
    
    /**
     * DTSTART: Date-Time Start, from RFC 5545 iCalendar 3.8.2.4 page 97
     * Start date/time of repeat rule.  Used as a starting point for making the Stream<LocalDateTime> of valid
     * start date/times of the repeating events.  Can be either type LocalDate or LocalDateTime
     */
    Temporal getDateTimeStart();
    /**
     * DTSTART: Date-Time Start, from RFC 5545 iCalendar 3.8.2.4 page 97
     * Start date/time of repeat rule.  Used as a starting point for making the Stream<LocalDateTime> of valid
     * start date/times of the repeating events.  Can be either type LocalDate or LocalDateTime
     */
    ObjectProperty<Temporal> dateTimeStartProperty(); // TODO - SHOULD I HAVE PROPERTIES HERE OR JUST IN ABSTRACT?
    default void setDateTimeStart(Temporal dtStart)
    {
        boolean correctType = (dtStart instanceof LocalDate) || (dtStart instanceof LocalDateTime) || (dtStart == null);
        if (! correctType) throw new IllegalArgumentException("DTStart type must be LocalDate or LocalDateTime, not: " + dtStart.getClass().getSimpleName());
    };
        
    /**
     * EXDATE: Set of date/times exceptions for recurring events, to-dos, journal entries.
     * 3.8.5.1, RFC 5545 iCalendar
     * Is rarely used, so employs lazy initialization.
     */
    EXDate getExDate();
    void setExDate(EXDate exDate);

    /**
     * LAST-MODIFIED: Date-Time Last Modified, from RFC 5545 iCalendar 3.8.7.3 page 138
     * This property specifies the date and time that the information associated with
     * the calendar component was last revised.
     */
    LocalDateTime getDateTimeLastModified();
    void setDateTimeLastModified(LocalDateTime dtLastModified);
    
    /**
     * LOCATION: RFC 5545 iCalendar 3.8.1.12. page 87
     * This property defines the intended venue for the activity
     * defined by a calendar component.
     * Example:
     * LOCATION:Conference Room - F123\, Bldg. 002
     */
    String getLocation();
    void setLocation(String value);

    /**
     * RDATE: Set of date/times for recurring events, to-dos, journal entries.
     * 3.8.5.2, RFC 5545 iCalendar
     */
    public RDate getRDate();
    public void setRDate(RDate rDate);

    /**
     * RECURRENCE-ID: date or date-time recurrence, from RFC 5545 iCalendar 3.8.4.4 page 112
     * The property value is the original value of the "DTSTART" property of the 
     * recurrence instance.
     */
    // TODO - VERIFY THIS WORKS - I DON'T THINK IT DOES
    Temporal getDateTimeRecurrence();
    public void setDateTimeRecurrence(Temporal dtRecurrence);
    
    /**
     * RRULE, Recurrence Rule as defined in RFC 5545 iCalendar 3.8.5.3, page 122.
     * This property defines a rule or repeating pattern for recurring events, 
     * to-dos, journal entries, or time zone definitions
     * If component is not repeating the value is null.
     */
    RRule getRRule();
    void setRRule(RRule rRule);

    /**
     *  SUMMARY: RFC 5545 iCalendar 3.8.1.12. page 83
     * This property defines a short summary or subject for the calendar component 
     * Example:
     * SUMMARY:Department Party
     * */
    String getSummary();
    void setSummary(String value);
    
    /**
     * UID, Unique identifier, as defined by RFC 5545, iCalendar 3.8.4.7 page 117
     * A globally unique identifier for the calendar component.
     * Included is an example UID generator.  Other UID generators can be provided by
     * setting the UID callback.
     */
    String getUniqueIdentifier();
    void setUniqueIdentifier(String s);
    
    /** Callback for creating unique uid values  */
    Callback<Void, String> getUidGeneratorCallback();
    void setUidGeneratorCallback(Callback<Void, String> uidCallback);
    
    /**
     * Checks to see if VComponent is has all required properties filled.  Also checks
     * to ensure all properties contain valid values.
     * 
     * @return true for valid VComponent, false for invalid one
     */
    boolean isValid();
    
    /** Stream of dates or date/times that indicate the start of the event(s).
     * For a VEvent without RRULE the stream will contain only one date/time element.
     * A VEvent with a RRULE the stream contains more than one date/time element.  It will be infinite 
     * if COUNT or UNTIL is not present.  The stream has an end when COUNT or UNTIL condition is met.
     * Starts on startDateTime, which must be a valid event date/time, not necessarily the
     * first date/time (DTSTART) in the sequence.
     * 
     * Start date/times are only produced between the ranges set by setDateTimeRanges
     * 
     * @param startTemporal - start dates or date/times produced after this date.  If not on an occurrence,
     * it will be adjusted to be the next occurrence
     * @return - stream of start dates or date/times for the recurrence set
     */
    Stream<Temporal> stream(Temporal startTemporal);

    /**
     * Start of range for which recurrence instances are generated.
     * Should match the start date displayed on the calendar.
     * This is not a part of an iCalendar VComponent.
     */
    Temporal getStartRange();
    /**
     * Start of range for which recurrence instances are generated.
     * Should match the start date displayed on the calendar.
     * This is not a part of an iCalendar VComponent.
     */
    void setStartRange(Temporal start);
    /**
     * End of range for which recurrence instances are generated.
     * Should match the end date displayed on the calendar.
     * This is not a part of an iCalendar VComponent.
     */
    Temporal getEndRange();
    /**
     * End of range for which recurrence instances are generated.
     * Should match the end date displayed on the calendar.
     * This is not a part of an iCalendar VComponent.
     */
    void setEndRange(Temporal end);

    /**
     * Returns the collection of recurrence instances of calendar component of type T that exists
     * between dateTimeRangeStart and dateTimeRangeEnd based on VComponent.
     * Recurrence set is defined in RFC 5545 iCalendar page 121 as follows 
     * "The recurrence set is the complete set of recurrence instances for a calendar component.  
     * The recurrence set is generated by considering the initial "DTSTART" property along with
     * the "RRULE", "RDATE", and "EXDATE" properties contained within the recurring component."
     *  
     * @param start - beginning of time frame to make instances
     * @param end - end of time frame to make instances
     * @return
     */
    Collection<T> makeInstances(Temporal start, Temporal end);
    /**
     * Returns the collection of recurrence instances of calendar component of type T that exists
     * between dateTimeRangeStart and dateTimeRangeEnd based on VComponent.
     * Recurrence set is defined in RFC 5545 iCalendar page 121 as follows 
     * "The recurrence set is the complete set of recurrence instances for a calendar component.  
     * The recurrence set is generated by considering the initial "DTSTART" property along with
     * the "RRULE", "RDATE", and "EXDATE" properties contained within the recurring component."
     * 
     * Uses start and end values from a previous call to makeInstances(Temporal start, Temporal end)
     * If there are no start and end values an exception is thrown.
     *  
     * @return
     */
    Collection<T> makeInstances();
    /**
     * Returns existing instances in the Recurrence Set (defined in RFC 5545 iCalendar page 121)
     * made by the last call of makeRecurrenceSet
     * @param <T> type of recurrence instance, such as an appointment implementation
     * 
     * @return - current instances of the Recurrence Set
     * @see makeRecurrenceSet
     */
    Collection<T> instances();
    
    /**
     * Copies this object into destination object
     * 
     * @param destination
     */
    void copyTo(VComponent<T> destination);
    
    /**
     * Handle editing a VComponent.
     * 
     * @param dateTimeStartInstanceOld
     * @param instance
     * @param vComponentOld
     * @param instances
     * @param vEvents
     * @param changeDialogCallback
     * @param vEventWriteCallback
     * @return
     */
    WindowCloseType edit(
            LocalDateTime dateTimeStartInstanceOld
          , T instance
          , VComponent<T> vComponentOld
          , Collection<T> instances
          , Collection<VComponent<T>> vEvents
          , Callback<ChangeDialogOptions[], ChangeDialogOptions> changeDialogCallback
          , Callback<Collection<VComponent<T>>, Void> vEventWriteCallback);
    
    /**
     * Handle deleting a VComponent from vComponents collection and its accompanying
     * recurrence instances 
     * 
     * @param instance
     * @param instances
     * @param vComponents
     * @param changeDialogCallback
     * @param confirmDeleteCallback
     * @param vEventWriteCallback
     * @return
     */
    WindowCloseType delete(
            Temporal dateOrDateTime
//          , Collection<T> instances
          , Collection<VComponent<T>> vComponents
          , Callback<ChangeDialogOptions[], ChangeDialogOptions> changeDialogCallback
          , Callback<String, Boolean> confirmDeleteCallback
          , Callback<Collection<VComponent<T>>, Void> vEventWriteCallback);

    /*
     * UTILITY METHODS
     * 
     * Below methods are used to handle dateTimeEnd and dateTimeStart as Temporal objects.  The allowed
     * types are either LocalDate or LocalDateTime.  Using LocalDate indicates a whole day component.
     */
    
    final static Comparator<Temporal> TEMPORAL_COMPARATOR = (t1, t2) -> 
    {
        if ((t1 instanceof LocalDateTime) && (t2 instanceof LocalDateTime))
        {
            return ((LocalDateTime) t1).compareTo((LocalDateTime) t2);
        } else if ((t1 instanceof LocalDate) && (t2 instanceof LocalDate))
        {
            return ((LocalDate) t1).compareTo((LocalDate) t2);
        } else throw new DateTimeException("DTSTART and DTEND must have same Temporal type("
                + t1.getClass().getSimpleName() + ", " + t2.getClass().getSimpleName() +")");
    };
    
    /** Parse iCalendar date or date/time string into LocalDate or LocalDateTime Temporal object */
    static Temporal parseTemporal(String dateTimeString)
    {
        if (dateTimeString.matches("^(VALUE=DATE-TIME:)?[0-9]{8}T?([0-9]{6})?Z?"))
        {
            String extractedDateTimeString = dateTimeString.substring(dateTimeString.lastIndexOf(":") + 1).trim();
            LocalDateTime dateTime = LocalDateTime.parse(extractedDateTimeString, DATE_TIME_FORMATTER);
            return dateTime;
        } else if (dateTimeString.matches("^VALUE=DATE:[0-9]{8}"))
        {
            String extractedDateString = dateTimeString.substring(dateTimeString.lastIndexOf(":") + 1).trim();
            LocalDate date = LocalDate.parse(extractedDateString, DATE_FORMATTER);
            return date;            
        } else
        {
            throw new IllegalArgumentException("String does not match DATE or DATE-TIME pattern: " + dateTimeString);
        }
    }
    
    /**
     * Convert temporal, either LocalDate or LocalDateTime to appropriate iCalendar string
     * 
     * @param temporal LocalDate or LocalDateTime
     * @return iCalendar date or date/time string
     */
    static String temporalToString(Temporal temporal)
    {
        if (temporal == null) return null;
        if (temporal instanceof LocalDate)
        {
            return DATE_FORMATTER.format(temporal);
        } else if (temporal instanceof LocalDateTime)
        {
            return DATE_TIME_FORMATTER.format(temporal);
        } else throw new DateTimeException("Illegal Temporal type.  Only LocalDate and LocalDateTime are supported (" +
                temporal + " of type " + temporal.getClass().getSimpleName() + ")");
    }

    /**
     * Returns LocalDateTime from TemperalAccessor that is an instance of either LocalDate or LocalDateTime
     * If the parameter is type LocalDate the returned LocalDateTime is atStartofDay.
     * 
     * @param temporal - either LocalDate or LocalDateTime type
     * @return LocalDateTime
     */
    static LocalDateTime localDateTimeFromTemporal(TemporalAccessor temporal)
    {
        if (temporal == null) return null;
        if (temporal instanceof LocalDate)
        {
            return ((LocalDate) temporal).atStartOfDay();
        } else if (temporal instanceof LocalDateTime)
        {
            return (LocalDateTime) temporal;
        } else throw new DateTimeException("Unable to obtain LocalDateTime from TemporalAccessor: " +
                temporal + " of type " + temporal.getClass().getSimpleName());
    }
    
    /** Determines if Temporal is before t2
     * Works for LocalDate or LocalDateTime
     * 
     * @param t1 first Temporal
     * @param t2 second Temporal (to compare with t1)
     * @return true if t1 is before t2, false otherwise
     */
    static boolean isBefore(Temporal t1, Temporal t2)
    {
        if (t1.getClass().equals(t2.getClass()))
        {
            LocalDateTime d1 = localDateTimeFromTemporal(t1);
            LocalDateTime d2 = localDateTimeFromTemporal(t2);
            return d1.isBefore(d2);
        } throw new DateTimeException("For comparision, Temporal classes must be equal (" + t1.getClass().getSimpleName() + ", " + t2.getClass().getSimpleName() + ")");
    }

    /** Determines if Temporal is after t2
     * Works for LocalDate or LocalDateTime
     * 
     * @param t1 first Temporal
     * @param t2 second Temporal (to compare with t1)
     * @return true if t1 is after t2, false otherwise
     */
    static boolean isAfter(Temporal t1, Temporal t2)
    {
        if (t1.getClass().equals(t2.getClass()))
        {
            LocalDateTime d1 = localDateTimeFromTemporal(t1);
            LocalDateTime d2 = localDateTimeFromTemporal(t2);
            return d1.isAfter(d2);
        } throw new DateTimeException("For comparision, Temporal classes must be equal (" + t1.getClass().getSimpleName() + ", " + t2.getClass().getSimpleName() + ")");
    }
    
    /**
     * Add value in chronoUnit to temporal
     * Automatically converts nanos to days if temporal is LocalDate.
     * 
     * @param dateTimeStart
     * @param startShiftInNanos
     * @param days
     */
    static Temporal plusNanos(Temporal temporal, long nanos)
    {
        if (temporal instanceof LocalDate)
        {
            return temporal.plus(nanos/NANOS_IN_DAY, ChronoUnit.DAYS);
//            return temporal.plus(nanos/NANOS_IN_DAY, ChronoUnit.DAYS);
        } else if (temporal instanceof LocalDateTime)
        {
            return temporal.plus(nanos, ChronoUnit.NANOS);
        } else throw new DateTimeException("Illegal Temporal type.  Only LocalDate and LocalDateTime are supported (" +
                temporal + " of type " + temporal.getClass().getSimpleName() + ")");        
    }
    
    /**
     * Converts value into Temporal of type clazz
     * value must be LocalDate or LocalDateTime
     * clazz must be LocalDate or LocalDateTime
     * 
     * @param value
     * @param clazz
     * @return
     */
    // TODO - CONSIDER REMOVING THIS METHOD - IS IT USEFUL?
    static Temporal ofTemporal(Temporal value, Class<? extends Temporal> clazz)
    {
        if (value instanceof LocalDate)
        {
            if (clazz.equals(LocalDate.class))
            {
                return value;
            } else if (clazz.equals(LocalDateTime.class))
            {
                return ((LocalDate) value).atStartOfDay();
            } else throw new DateTimeException("Illegal Temporal type.  Only LocalDate and LocalDateTime are supported)");
        } else if (value instanceof LocalDateTime)
        {
            if (clazz.equals(LocalDate.class))
            {
                return ((LocalDateTime) value).toLocalDate();
            } else if (clazz.equals(LocalDateTime.class))
            {
                return value;
            } else throw new DateTimeException("Illegal Temporal type.  Only LocalDate and LocalDateTime are supported)");
        } else throw new DateTimeException("Illegal Temporal type.  Only LocalDate and LocalDateTime are supported)");
    }

}