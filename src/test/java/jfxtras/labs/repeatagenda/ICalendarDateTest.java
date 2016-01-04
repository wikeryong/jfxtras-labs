package jfxtras.labs.repeatagenda;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import jfxtras.labs.repeatagenda.scene.control.repeatagenda.ICalendarAgenda;
import jfxtras.labs.repeatagenda.scene.control.repeatagenda.VEventImpl;

public class ICalendarDateTest extends ICalendarTestAbstract
{
    @Test // LocalDateTime
    public void canSetupDateTimeStartFirst()
    {
        // setup
        VEventImpl v = new VEventImpl(ICalendarAgenda.DEFAULT_APPOINTMENT_GROUPS);
        v.setDateTimeStart(LocalDateTime.of(2015, 11, 9, 8, 0));
        v.setDateTimeEnd(LocalDateTime.of(2015, 11, 9, 10, 0));
        
        // verify initial conditions
        assertEquals(LocalDateTime.of(2015, 11, 9, 8, 0), v.getDateTimeStart());
        assertEquals(LocalDateTime.of(2015, 11, 9, 10, 0), v.getDateTimeEnd());
        Long expectedDuration = 7200L * NANOS_IN_SECOND;
        assertEquals(expectedDuration, v.getDurationInNanos());        
    }
    
    @Test // LocalDate
    public void canSetupDateTimeStartFirst2()
    {
        // setup
        VEventImpl v = new VEventImpl(ICalendarAgenda.DEFAULT_APPOINTMENT_GROUPS);
        v.setDateTimeStart(LocalDate.of(2015, 11, 9));
        v.setDateTimeEnd(LocalDate.of(2015, 11, 10));
        
        // verify initial conditions
        assertEquals(LocalDate.of(2015, 11, 9), v.getDateTimeStart());
        assertEquals(LocalDate.of(2015, 11, 10), v.getDateTimeEnd());
        Long expectedDuration = 24L * 3600L * NANOS_IN_SECOND;
        assertEquals(expectedDuration, v.getDurationInNanos());        
    }

    @Test // LocalDateTime
    public void canSetupDateTimeEndFirst()
    {
        // setup
        VEventImpl v = new VEventImpl(ICalendarAgenda.DEFAULT_APPOINTMENT_GROUPS);
        v.setDateTimeEnd(LocalDateTime.of(2015, 11, 9, 10, 0));
        v.setDateTimeStart(LocalDateTime.of(2015, 11, 9, 8, 0));
        
        // verify initial conditions
        assertEquals(LocalDateTime.of(2015, 11, 9, 8, 0), v.getDateTimeStart());
        assertEquals(LocalDateTime.of(2015, 11, 9, 10, 0), v.getDateTimeEnd());
        Long expectedDuration = 7200L * NANOS_IN_SECOND;
        assertEquals(expectedDuration, v.getDurationInNanos());        
    }
    
    @Test // LocalDate
    public void canSetupDateTimeEndFirst2()
    {
        // setup
        VEventImpl v = new VEventImpl(ICalendarAgenda.DEFAULT_APPOINTMENT_GROUPS);
        v.setDateTimeEnd(LocalDate.of(2015, 11, 10));
        v.setDateTimeStart(LocalDate.of(2015, 11, 9));
        
        // verify initial conditions
        assertEquals(LocalDate.of(2015, 11, 9), v.getDateTimeStart());
        assertEquals(LocalDate.of(2015, 11, 10), v.getDateTimeEnd());
        Long expectedDuration = 24L * 3600L * NANOS_IN_SECOND;
        assertEquals(expectedDuration, v.getDurationInNanos());        
    }
    
    @Test // LocalDateTime
    public void canSetupDurationFirst()
    {
        // setup
        VEventImpl v = new VEventImpl(ICalendarAgenda.DEFAULT_APPOINTMENT_GROUPS);
        v.setDurationInNanos(7200L * NANOS_IN_SECOND);
        v.setDateTimeStart(LocalDateTime.of(2015, 11, 9, 8, 0));
        
        // verify initial conditions
        assertEquals(LocalDateTime.of(2015, 11, 9, 8, 0), v.getDateTimeStart());
        assertEquals(LocalDateTime.of(2015, 11, 9, 10, 0), v.getDateTimeEnd());
        Long expectedDuration = 7200L * NANOS_IN_SECOND;
        assertEquals(expectedDuration, v.getDurationInNanos());        
    }
    
    @Test // LocalDate
    public void canSetupDurationFirst2()
    {
        // setup
        VEventImpl v = new VEventImpl(ICalendarAgenda.DEFAULT_APPOINTMENT_GROUPS);
        v.setDurationInNanos(24L * 3600L * NANOS_IN_SECOND);
        v.setDateTimeStart(LocalDate.of(2015, 11, 9));
        
        // verify initial conditions
        assertEquals(LocalDate.of(2015, 11, 9), v.getDateTimeStart());
        assertEquals(LocalDate.of(2015, 11, 10), v.getDateTimeEnd());
        Long expectedDuration = 24L * 3600L * NANOS_IN_SECOND;
        assertEquals(expectedDuration, v.getDurationInNanos());          
    }
    
    /** Tests daily stream with FREQ=YEARLY */
    @Test
    public void yearlyStreamTest1()
    {
        VEventImpl e = getYearly1();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .limit(5)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 9, 10, 0)
              , LocalDateTime.of(2016, 11, 9, 10, 0)
              , LocalDateTime.of(2017, 11, 9, 10, 0)
              , LocalDateTime.of(2018, 11, 9, 10, 0)
              , LocalDateTime.of(2019, 11, 9, 10, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }
    
    /** Tests daily stream with FREQ=YEARLY;BYDAY=SU */
    @Test
    public void yearlyStreamTest2()
    {
        VEventImpl e = getYearly2();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .limit(5)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 6, 10, 0)
              , LocalDateTime.of(2015, 11, 13, 10, 0)
              , LocalDateTime.of(2015, 11, 20, 10, 0)
              , LocalDateTime.of(2015, 11, 27, 10, 0)
              , LocalDateTime.of(2015, 12, 4, 10, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }
    
    /** FREQ=YEARLY;BYDAY=TH;BYMONTH=6,7,8 */
    @Test
    public void yearlyStreamTest3()
    {
        VEventImpl e = getYearly3();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .limit(20)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(1997, 6, 5, 9, 0)
              , LocalDateTime.of(1997, 6, 12, 9, 0)
              , LocalDateTime.of(1997, 6, 19, 9, 0)
              , LocalDateTime.of(1997, 6, 26, 9, 0)
              , LocalDateTime.of(1997, 7, 3, 9, 0)
              , LocalDateTime.of(1997, 7, 10, 9, 0)
              , LocalDateTime.of(1997, 7, 17, 9, 0)
              , LocalDateTime.of(1997, 7, 24, 9, 0)
              , LocalDateTime.of(1997, 7, 31, 9, 0)
              , LocalDateTime.of(1997, 8, 7, 9, 0)
              , LocalDateTime.of(1997, 8, 14, 9, 0)
              , LocalDateTime.of(1997, 8, 21, 9, 0)
              , LocalDateTime.of(1997, 8, 28, 9, 0)
              , LocalDateTime.of(1998, 6, 4, 9, 0)
              , LocalDateTime.of(1998, 6, 11, 9, 0)
              , LocalDateTime.of(1998, 6, 18, 9, 0)
              , LocalDateTime.of(1998, 6, 25, 9, 0)
              , LocalDateTime.of(1998, 7, 2, 9, 0)
              , LocalDateTime.of(1998, 7, 9, 9, 0)
              , LocalDateTime.of(1998, 7, 16, 9, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }
    
    /** FREQ=YEARLY;BYMONTH=1,2 */
    @Test
    public void yearlyStreamTest4()
    {
        VEventImpl e = getYearly4();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .limit(5)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 1, 6, 10, 0)
              , LocalDateTime.of(2015, 2, 6, 10, 0)
              , LocalDateTime.of(2016, 1, 6, 10, 0)
              , LocalDateTime.of(2016, 2, 6, 10, 0)
              , LocalDateTime.of(2017, 1, 6, 10, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }
    
    /** FREQ=YEARLY;BYMONTH=11;BYMONTHDAY=10 */
    @Test
    public void yearlyStreamTest5()
    {
        VEventImpl e = getYearly5();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .limit(5)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 10, 0, 0)
              , LocalDateTime.of(2016, 11, 10, 0, 0)
              , LocalDateTime.of(2017, 11, 10, 0, 0)
              , LocalDateTime.of(2018, 11, 10, 0, 0)
              , LocalDateTime.of(2019, 11, 10, 0, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }
    
    /** FREQ=YEARLY;INTERVAL=4;BYMONTH=11;BYDAY=TU;BYMONTHDAY=2,3,4,5,6,7,8
     * (U.S. Presidential Election day) */
    @Test
    public void yearlyStreamTest6()
    {
        VEventImpl e = getYearly6();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .limit(6)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(1996, 11, 5, 0, 0)
              , LocalDateTime.of(2000, 11, 7, 0, 0)
              , LocalDateTime.of(2004, 11, 2, 0, 0)
              , LocalDateTime.of(2008, 11, 4, 0, 0)
              , LocalDateTime.of(2012, 11, 6, 0, 0)
              , LocalDateTime.of(2016, 11, 8, 0, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }
    
    /** FREQ=YEARLY;BYDAY=20MO */
    @Test
    public void yearlyStreamTest7()
    {
        VEventImpl e = getYearly7();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .limit(3)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(1997, 5, 19, 10, 0)
              , LocalDateTime.of(1998, 5, 18, 10, 0)
              , LocalDateTime.of(1999, 5, 17, 10, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }
    
    /** FREQ=YEARLY;BYWEEKNO=20;BYDAY=MO */
    @Test
    public void yearlyStreamTest8()
    {
//        Locale oldLocale = Locale.getDefault();
//        Locale.setDefault(Locale.FRANCE); // has Monday as first day of week system.  US is Sunday which causes an error.
        VEventImpl e = getYearly8();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .limit(5)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(1997, 5, 12, 10, 0)
              , LocalDateTime.of(1998, 5, 11, 10, 0)
              , LocalDateTime.of(1999, 5, 17, 10, 0)
              , LocalDateTime.of(2000, 5, 15, 10, 0)
              , LocalDateTime.of(2001, 5, 14, 10, 0)
                ));
        assertEquals(expectedDates, madeDates);
//        Locale.setDefault(oldLocale);
    }
    
    /** FREQ=YEARLY;BYMONTH=11;BYMONTHDAY=10 - start before first valid date */
    @Test
    public void yearlyStreamTest9()
    {
        VEventImpl e = getYearly9();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .limit(5)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 10, 0, 0)
              , LocalDateTime.of(2016, 11, 10, 0, 0)
              , LocalDateTime.of(2017, 11, 10, 0, 0)
              , LocalDateTime.of(2018, 11, 10, 0, 0)
              , LocalDateTime.of(2019, 11, 10, 0, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }
    
    /** Tests daily stream with FREQ=MONTHLY */
    @Test
    public void monthlyStreamTest()
    {
        VEventImpl e = getMonthly1();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .limit(5)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 9, 10, 0)
              , LocalDateTime.of(2015, 12, 9, 10, 0)
              , LocalDateTime.of(2016, 1, 9, 10, 0)
              , LocalDateTime.of(2016, 2, 9, 10, 0)
              , LocalDateTime.of(2016, 3, 9, 10, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }

    /** Tests daily stream with FREQ=MONTHLY;BYMONTHDAY=-2 */
    @Test
    public void monthlyStreamTest2()
    {
        VEventImpl e = getMonthly2();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .limit(5)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 29, 10, 0)
              , LocalDateTime.of(2015, 12, 30, 10, 0)
              , LocalDateTime.of(2016, 1, 30, 10, 0)
              , LocalDateTime.of(2016, 2, 28, 10, 0)
              , LocalDateTime.of(2016, 3, 30, 10, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }

    /** Tests daily stream with FREQ=MONTHLY;BYDAY=TU,WE,FR */
    @Test
    public void monthlyStreamTest3()
    {
        VEventImpl e = getMonthly3();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .limit(10)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 10, 10, 0)
              , LocalDateTime.of(2015, 11, 11, 10, 0)
              , LocalDateTime.of(2015, 11, 13, 10, 0)
              , LocalDateTime.of(2015, 11, 17, 10, 0)
              , LocalDateTime.of(2015, 11, 18, 10, 0)
              , LocalDateTime.of(2015, 11, 20, 10, 0)
              , LocalDateTime.of(2015, 11, 24, 10, 0)
              , LocalDateTime.of(2015, 11, 25, 10, 0)
              , LocalDateTime.of(2015, 11, 27, 10, 0)
              , LocalDateTime.of(2015, 12, 1, 10, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }

    /** Tests daily stream with FREQ=MONTHLY;BYDAY=-1SA */
    @Test
    public void monthlyStreamTest4()
    {
        VEventImpl e = getMonthly4();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .limit(5)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 28, 10, 0)
              , LocalDateTime.of(2015, 12, 26, 10, 0)
              , LocalDateTime.of(2016, 1, 30, 10, 0)
              , LocalDateTime.of(2016, 2, 27, 10, 0)
              , LocalDateTime.of(2016, 3, 26, 10, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }

    /** FREQ=MONTHLY;BYDAY=FR;BYMONTHDAY=13 Every Friday the 13th, forever: */
    @Test
    public void monthlyStreamTest5()
    {
        VEventImpl e = getMonthly5();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .limit(6)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(1997, 6, 13, 10, 0)
              , LocalDateTime.of(1998, 2, 13, 10, 0)
              , LocalDateTime.of(1998, 3, 13, 10, 0)
              , LocalDateTime.of(1998, 11, 13, 10, 0)
              , LocalDateTime.of(1999, 8, 13, 10, 0)
              , LocalDateTime.of(2000, 10, 13, 10, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }
    
    /** Tests daily stream with FREQ=MONTHLY;BYDAY=TU,WE,FR - start before first valid date */
    @Test
    public void monthlyStreamTest6()
    {
        VEventImpl e = getMonthly6();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .limit(13)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 3, 10, 0)
              , LocalDateTime.of(2015, 11, 4, 10, 0)
              , LocalDateTime.of(2015, 11, 6, 10, 0)
              , LocalDateTime.of(2015, 11, 10, 10, 0)
              , LocalDateTime.of(2015, 11, 11, 10, 0)
              , LocalDateTime.of(2015, 11, 13, 10, 0)
              , LocalDateTime.of(2015, 11, 17, 10, 0)
              , LocalDateTime.of(2015, 11, 18, 10, 0)
              , LocalDateTime.of(2015, 11, 20, 10, 0)
              , LocalDateTime.of(2015, 11, 24, 10, 0)
              , LocalDateTime.of(2015, 11, 25, 10, 0)
              , LocalDateTime.of(2015, 11, 27, 10, 0)
              , LocalDateTime.of(2015, 12, 1, 10, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }
    
    /** FREQ=WEEKLY */
    @Test
    public void weeklyStreamTest1()
    {
        VEventImpl e = getWeekly1();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .limit(5)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 9, 10, 0)
              , LocalDateTime.of(2015, 11, 16, 10, 0)
              , LocalDateTime.of(2015, 11, 23, 10, 0)
              , LocalDateTime.of(2015, 11, 30, 10, 0)
              , LocalDateTime.of(2015, 12, 7, 10, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }
    
    /** FREQ=WEEKLY;INTERVAL=2;BYDAY=MO,WE,FR */
    @Test
    public void weeklyStreamTest2()
    {
        VEventImpl e = getWeekly2();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .limit(10)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 11, 10, 0)
              , LocalDateTime.of(2015, 11, 13, 10, 0)
              , LocalDateTime.of(2015, 11, 23, 10, 0)
              , LocalDateTime.of(2015, 11, 25, 10, 0)
              , LocalDateTime.of(2015, 11, 27, 10, 0)
              , LocalDateTime.of(2015, 12, 7, 10, 0)
              , LocalDateTime.of(2015, 12, 9, 10, 0)
              , LocalDateTime.of(2015, 12, 11, 10, 0)
              , LocalDateTime.of(2015, 12, 21, 10, 0)
              , LocalDateTime.of(2015, 12, 23, 10, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }
    
    /** FREQ=WEEKLY;BYDAY=MO,WE,FR */
    @Test
    public void weeklyStreamTest3()
    {
        VEventImpl e = getWeekly3();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .limit(5)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 9, 10, 0)
              , LocalDateTime.of(2015, 11, 11, 10, 0)
              , LocalDateTime.of(2015, 11, 13, 10, 0)
              , LocalDateTime.of(2015, 11, 16, 10, 0)
              , LocalDateTime.of(2015, 11, 18, 10, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }
    
    /** FREQ=WEEKLY;INTERVAL=2;COUNT=11;BYDAY=MO,WE,FR */
    @Test
    public void canStreamWeekly4()
    {
        VEventImpl e = getWeekly4();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 11, 10, 0)
              , LocalDateTime.of(2015, 11, 13, 10, 0)
              , LocalDateTime.of(2015, 11, 23, 10, 0)
              , LocalDateTime.of(2015, 11, 25, 10, 0)
              , LocalDateTime.of(2015, 11, 27, 10, 0)
              , LocalDateTime.of(2015, 12, 7, 10, 0)
              , LocalDateTime.of(2015, 12, 9, 10, 0)
              , LocalDateTime.of(2015, 12, 11, 10, 0)
              , LocalDateTime.of(2015, 12, 21, 10, 0)
              , LocalDateTime.of(2015, 12, 23, 10, 0)
              , LocalDateTime.of(2015, 12, 25, 10, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }
    
    @Test // tests starting on Sunday (1st day of week) with other day of the week
    public void canStreamWeekly5()
    {
        VEventImpl e = getWeekly5();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .limit(10)
                .peek(System.out::println)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2016, 1, 3, 5, 0)
              , LocalDateTime.of(2016, 1, 6, 5, 0)
              , LocalDateTime.of(2016, 1, 10, 5, 0)
              , LocalDateTime.of(2016, 1, 13, 5, 0)
              , LocalDateTime.of(2016, 1, 17, 5, 0)
              , LocalDateTime.of(2016, 1, 20, 5, 0)
              , LocalDateTime.of(2016, 1, 24, 5, 0)
              , LocalDateTime.of(2016, 1, 27, 5, 0)
              , LocalDateTime.of(2016, 1, 31, 5, 0)
              , LocalDateTime.of(2016, 2, 3, 5, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }
   
    /** Tests daily stream with FREQ=DAILY */
    @Test
    public void dailyStreamTest1()
    {
        VEventImpl e = getDaily1();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .limit(5)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 9, 10, 0)
              , LocalDateTime.of(2015, 11, 10, 10, 0)
              , LocalDateTime.of(2015, 11, 11, 10, 0)
              , LocalDateTime.of(2015, 11, 12, 10, 0)
              , LocalDateTime.of(2015, 11, 13, 10, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }

    /** Tests daily stream with FREQ=DAILY;INVERVAL=3;COUNT=6 */
    @Test
    public void dailyStreamTest2()
    {
        VEventImpl e = getDaily2();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 9, 10, 0)
              , LocalDateTime.of(2015, 11, 12, 10, 0)
              , LocalDateTime.of(2015, 11, 15, 10, 0)
              , LocalDateTime.of(2015, 11, 18, 10, 0)
              , LocalDateTime.of(2015, 11, 21, 10, 0)
              , LocalDateTime.of(2015, 11, 24, 10, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }

    /** Tests daily stream with FREQ=DAILY;INVERVAL=2;BYMONTHDAY=9,10,11,12,13,14 */
    @Test
    public void dailyStreamTest3()
    {
        VEventImpl e = getDaily3();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .limit(10)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 9, 10, 0)
              , LocalDateTime.of(2015, 11, 12, 10, 0)
              , LocalDateTime.of(2015, 12, 9, 10, 0)
              , LocalDateTime.of(2015, 12, 12, 10, 0)
              , LocalDateTime.of(2016, 1, 11, 10, 0)
              , LocalDateTime.of(2016, 1, 14, 10, 0)
              , LocalDateTime.of(2016, 2, 10, 10, 0)
              , LocalDateTime.of(2016, 2, 13, 10, 0)
              , LocalDateTime.of(2016, 3, 11, 10, 0)
              , LocalDateTime.of(2016, 3, 14, 10, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }
    
    /** Tests daily stream with FREQ=DAILY;INVERVAL=2;BYMONTHDAY=9 */
    @Test
    public void dailyStreamTest4()
    {
        VEventImpl e = getDaily4();
        List<Temporal> madeDates = e.getRRule()
                .stream(e.getDateTimeStart())
                .limit(6)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 9, 10, 0)
              , LocalDateTime.of(2015, 12, 9, 10, 0)
              , LocalDateTime.of(2016, 2, 9, 10, 0)
              , LocalDateTime.of(2016, 4, 9, 10, 0)
              , LocalDateTime.of(2016, 5, 9, 10, 0)
              , LocalDateTime.of(2016, 8, 9, 10, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }
    
    /** Tests daily stream with FREQ=DAILY;INVERVAL=2;BYDAY=FR*/
    @Test
    public void dailyStreamTest5()
    {
        VEventImpl e = getDaily5();
        List<Temporal> madeDates = e
                .stream(e.getDateTimeStart())
                .limit(6)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 13, 10, 0)
              , LocalDateTime.of(2015, 11, 27, 10, 0)
              , LocalDateTime.of(2015, 12, 11, 10, 0)
              , LocalDateTime.of(2015, 12, 25, 10, 0)
              , LocalDateTime.of(2016, 1, 8, 10, 0)
              , LocalDateTime.of(2016, 1, 22, 10, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }
    
    /** Tests daily stream with FREQ=DAILY;INVERVAL=2;UNTIL=20151201T000000 */
    @Test
    public void dailyStreamTest6()
    {
        VEventImpl e = getDaily6();
        List<Temporal> madeDates = e
                .stream(e.getDateTimeStart())
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 9, 10, 0)
              , LocalDateTime.of(2015, 11, 11, 10, 0)
              , LocalDateTime.of(2015, 11, 13, 10, 0)
              , LocalDateTime.of(2015, 11, 15, 10, 0)
              , LocalDateTime.of(2015, 11, 17, 10, 0)
              , LocalDateTime.of(2015, 11, 19, 10, 0)
              , LocalDateTime.of(2015, 11, 21, 10, 0)
              , LocalDateTime.of(2015, 11, 23, 10, 0)
              , LocalDateTime.of(2015, 11, 25, 10, 0)
              , LocalDateTime.of(2015, 11, 27, 10, 0)
              , LocalDateTime.of(2015, 11, 29, 10, 0)
              ));
        assertEquals(expectedDates, madeDates);
    }
    
    /** Tests individual VEvent */
    @Test
    public void individualTest1()
    {
        VEventImpl e = getIndividual1();
        List<Temporal> madeDates = e
                .stream(e.getDateTimeStart())
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 11, 10, 30)
                ));
        assertEquals(expectedDates, madeDates);
    }

    /** Tests VEvent with RRule and exception VEvent */
    @Test
    public void exceptionTest1()
    {
        VEventImpl e = getDailyWithException1();
        List<Temporal> madeDates = e
                .stream(e.getDateTimeStart())
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 9, 10, 0)
              , LocalDateTime.of(2015, 11, 18, 10, 0)
              , LocalDateTime.of(2015, 11, 21, 10, 0)
              , LocalDateTime.of(2015, 11, 24, 10, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }

    /** Tests VEvent with RRule and exception VEvent */
    @Test
    public void recurrenceTest1()
    {
        VEventImpl e = getRecurrence1();
        List<Temporal> madeDates = e
                .stream(e.getDateTimeStart())
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 9, 10, 0)
              , LocalDateTime.of(2015, 11, 12, 10, 0)
              , LocalDateTime.of(2015, 11, 14, 12, 0)
                ));
        assertEquals(expectedDates, madeDates);
    }
    
    @Test
    public void getWeekly2ChangeRange()
    {
        VEventImpl vevent = getWeekly2();
        LocalDateTime start = LocalDateTime.of(2015, 11, 8, 0, 0);
        List<Temporal> madeDates = vevent
                .stream(start)
                .limit(2)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 11, 10, 0)
              , LocalDateTime.of(2015, 11, 13, 10, 0)
                ));
        assertEquals(expectedDates, madeDates);

        LocalDateTime start2 = LocalDateTime.of(2015, 12, 6, 0, 0);
        List<Temporal> madeDates2 = vevent
                .stream(start2)
                .limit(3)
                .collect(Collectors.toList());
        List<LocalDateTime> expectedDates2 = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 12, 7, 10, 0)
              , LocalDateTime.of(2015, 12, 9, 10, 0)
              , LocalDateTime.of(2015, 12, 11, 10, 0)
                ));
        assertEquals(expectedDates2, madeDates2);
    }
    
    // ten years in future
    @Test
    public void getWeekly2FarFuture()
    {
        VEventImpl vevent = getWeekly2();
        
        LocalDateTime start = LocalDateTime.of(2025, 11, 10, 0, 0);
        long t1 = System.nanoTime();
        List<Temporal> madeDates = vevent
                .stream(start)
                .limit(3)
                .collect(Collectors.toList());
        long t2 = System.nanoTime();
        System.out.println("time:" + (t2-t1));
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2025, 11, 10, 10, 0)
              , LocalDateTime.of(2025, 11, 12, 10, 0)
              , LocalDateTime.of(2025, 11, 14, 10, 0)
                ));
        assertEquals(expectedDates, madeDates);
        
        LocalDateTime start2 = LocalDateTime.of(2015, 11, 11, 0, 0);
        t1 = System.nanoTime();
        List<Temporal> madeDates2 = vevent
                .stream(start2)
                .limit(2)
                .collect(Collectors.toList());
        t2 = System.nanoTime();
        System.out.println("time:" + (t2-t1));
        List<LocalDateTime> expectedDates2 = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 11, 10, 0)
              , LocalDateTime.of(2015, 11, 13, 10, 0)
                ));
        assertEquals(expectedDates2, madeDates2);
        
        LocalDateTime start3 = LocalDateTime.of(2025, 11, 12, 0, 0);
        t1 = System.nanoTime();
        List<Temporal> madeDates3 = vevent
                .stream(start3)
                .limit(3)
                .collect(Collectors.toList());
        t2 = System.nanoTime();
        System.out.println("time:" + (t2-t1));
        List<LocalDateTime> expectedDates3 = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2025, 11, 12, 10, 0)
              , LocalDateTime.of(2025, 11, 14, 10, 0)
              , LocalDateTime.of(2025, 11, 24, 10, 0)
                ));
        assertEquals(expectedDates3, madeDates3);

        LocalDateTime start4 = LocalDateTime.of(2025, 11, 17, 0, 0);
        t1 = System.nanoTime();
        List<Temporal> madeDates4 = vevent
                .stream(start4)
                .limit(3)
                .collect(Collectors.toList());
        t2 = System.nanoTime();
        System.out.println("time:" + (t2-t1));
        List<LocalDateTime> expectedDates4 = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2025, 11, 24, 10, 0)
              , LocalDateTime.of(2025, 11, 26, 10, 0)
              , LocalDateTime.of(2025, 11, 28, 10, 0)
                ));
        assertEquals(expectedDates4, madeDates4);
    }

    
    // Whole day tests
    
    @Test
    public void makeDatesWholeDayDaily2()
    {
        VEventImpl e = getWholeDayDaily2();
        List<Temporal> madeDates = e               
                .stream(e.getDateTimeStart())
                .collect(Collectors.toList());
        List<LocalDate> expectedDates = new ArrayList<>(Arrays.asList(
                LocalDate.of(2015, 11, 9)
              , LocalDate.of(2015, 11, 12)
              , LocalDate.of(2015, 11, 15)
              , LocalDate.of(2015, 11, 18)
              , LocalDate.of(2015, 11, 21)
              , LocalDate.of(2015, 11, 24)
                ));
        assertEquals(expectedDates, madeDates);
    }

    @Test
    public void makeDatesWholeDayDaily3()
    {
        VEventImpl e = getWholeDayDaily3();
        List<Temporal> madeDates = e                
                .stream(e.getDateTimeStart())
                .collect(Collectors.toList());
        List<LocalDate> expectedDates = new ArrayList<>(Arrays.asList(
                LocalDate.of(2015, 11, 9)
              , LocalDate.of(2015, 11, 12)
              , LocalDate.of(2015, 11, 15)
              , LocalDate.of(2015, 11, 18)
              , LocalDate.of(2015, 11, 21)
              , LocalDate.of(2015, 11, 24)
                ));
        assertEquals(expectedDates, madeDates);
    }
}
