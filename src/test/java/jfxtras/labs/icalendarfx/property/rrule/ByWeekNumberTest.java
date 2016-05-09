package jfxtras.labs.icalendarfx.property.rrule;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Ignore;
import org.junit.Test;

import jfxtras.labs.icalendarfx.properties.component.recurrence.rrule.byxxx.ByWeekNumber;

public class ByWeekNumberTest
{

    @Test
    public void canParseByWeekNumber()
    {
        ByWeekNumber element = new ByWeekNumber(4,5);
        assertEquals(Arrays.asList(4,5), element.getValue());
        assertEquals("BYWEEKNO=4,5", element.toContent());
    }

    /*
    DTSTART:19970512T100000
    RRULE:FREQ=YEARLY;BYWEEKNO=20
     */
    @Test
    public void canStreamByWeekNumber()
    {
        ByWeekNumber element = new ByWeekNumber(20);
        LocalDateTime dateTimeStart = LocalDateTime.of(1997, 5, 12, 10, 0);
        ChronoUnit frequency = ChronoUnit.YEARS;
        TemporalAdjuster adjuster = (temporal) -> temporal.plus(1, frequency);
        Stream<Temporal> inStream = Stream.iterate(dateTimeStart, a -> a.with(adjuster));
        Stream<Temporal> recurrenceStream = element.streamRecurrences(inStream, frequency, dateTimeStart);
        List<LocalDateTime> expectedRecurrences = new ArrayList<>(Arrays.asList(
                LocalDateTime.of(1997, 5, 12, 10, 0)
              , LocalDateTime.of(1997, 5, 13, 10, 0)
              , LocalDateTime.of(1997, 5, 14, 10, 0)
              , LocalDateTime.of(1997, 5, 15, 10, 0)
              , LocalDateTime.of(1997, 5, 16, 10, 0)
              , LocalDateTime.of(1997, 5, 17, 10, 0)
              , LocalDateTime.of(1997, 5, 18, 10, 0)
              , LocalDateTime.of(1998, 5, 11, 10, 0)
              , LocalDateTime.of(1998, 5, 12, 10, 0)
              , LocalDateTime.of(1998, 5, 13, 10, 0)
                ));
        List<Temporal> madeRecurrences = recurrenceStream.limit(10).collect(Collectors.toList());
        assertEquals(expectedRecurrences, madeRecurrences);
//        element.getValue().add(999); // throws exception
    }
    
    @Test (expected = IllegalArgumentException.class)
    @Ignore // JUnit won't recognize exception - exception is thrown in listener is cause
    public void canCatchInvalidByWeekNumber()
    {
        ByWeekNumber element = new ByWeekNumber();
        element.getValue().add(999); // throws exception
    }

}