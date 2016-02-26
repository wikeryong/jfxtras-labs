package jfxtras.labs.repeatagenda;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseButton;
import jfxtras.labs.repeatagenda.scene.control.repeatagenda.ICalendarAgendaUtilities.ChangeDialogOption;
import jfxtras.labs.repeatagenda.scene.control.repeatagenda.VEventImpl;
import jfxtras.labs.repeatagenda.scene.control.repeatagenda.icalendar.VComponent;
import jfxtras.labs.repeatagenda.scene.control.repeatagenda.icalendar.rrule.RRule;
import jfxtras.test.TestUtil;

public class AgendaChangeTest extends AgendaTestAbstract
{
    @Override
    public Parent getRootNode()
    {
        return super.getRootNode();
    }
    
    /* Moves appointment to new time */
    @Test
    public void canDragAndDropAppointment()
    {
        TestUtil.runThenWaitForPaintPulse( () -> agenda.vComponents().add(ICalendarTestAbstract.getDaily1()));
        
        // move one appointment
        assertFind("#AppointmentRegularBodyPane2015-11-11/0");
        move("#hourLine11");
        press(MouseButton.PRIMARY);
        move("#hourLine15");
        release(MouseButton.PRIMARY);

        // change dialog
        ComboBox<ChangeDialogOption> c = find("#changeDialogComboBox");
        TestUtil.runThenWaitForPaintPulse( () -> c.getSelectionModel().select(ChangeDialogOption.ONE));
        click("#changeDialogOkButton");

        // check return to original state
        assertEquals(2, agenda.vComponents().size());
        assertEquals(6, agenda.appointments().size());
        Collections.sort(agenda.vComponents(), VComponent.VCOMPONENT_COMPARATOR);
        VEventImpl v0 = (VEventImpl) agenda.vComponents().get(0);
        VEventImpl v1 = (VEventImpl) agenda.vComponents().get(1);
        RRule r = ICalendarTestAbstract.getDaily1().getRRule().withRecurrences(v1);
        VEventImpl expectedV0 = ICalendarTestAbstract.getDaily1()
                .withRRule(r);
        assertTrue(ICalendarTestAbstract.vEventIsEqualTo(expectedV0, v0));
        
        VEventImpl expectedV1 = ICalendarTestAbstract.getDaily1()
                .withRRule(null)
                .withDateTimeRecurrence(LocalDateTime.of(2015, 11, 11, 10, 0))
                .withDateTimeStart(LocalDateTime.of(2015, 11, 11, 14, 0))
                .withDateTimeEnd(LocalDateTime.of(2015, 11, 11, 15, 0))
                .withDateTimeStamp(v1.getDateTimeStamp())
                .withSequence(1);
        assertTrue(ICalendarTestAbstract.vEventIsEqualTo(expectedV1, v1));
        
        // check appointment dates
        List<LocalDateTime> expectedDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 9, 10, 0)
              , LocalDateTime.of(2015, 11, 10, 10, 0)
              , LocalDateTime.of(2015, 11, 11, 14, 0)
              , LocalDateTime.of(2015, 11, 12, 10, 0)
              , LocalDateTime.of(2015, 11, 13, 10, 0)
              , LocalDateTime.of(2015, 11, 14, 10, 0)
              ));
        List<LocalDateTime> startDates = agenda.appointments()
                .stream()
                .map(a -> a.getStartLocalDateTime())
                .sorted()
                .collect(Collectors.toList());
        assertEquals(expectedDates, startDates);
        
        List<LocalDateTime> expectedEndDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 9, 11, 0)
              , LocalDateTime.of(2015, 11, 10, 11, 0)
              , LocalDateTime.of(2015, 11, 11, 15, 0)
              , LocalDateTime.of(2015, 11, 12, 11, 0)
              , LocalDateTime.of(2015, 11, 13, 11, 0)
              , LocalDateTime.of(2015, 11, 14, 11, 0)
              ));
        List<LocalDateTime> endDates = agenda.appointments()
                .stream()
                .map(a -> a.getEndLocalDateTime())
                .sorted()
                .collect(Collectors.toList());
        assertEquals(expectedEndDates, endDates);
    }
    
    /* Moves appointment to new time - ZonedDateTime */
    @Test
    public void canDragAndDropZonedAppointment()
    {
        TestUtil.runThenWaitForPaintPulse( () -> agenda.vComponents().add(ICalendarTestAbstract.getWeeklyZoned()));
        
        // move one appointment
        assertFind("#AppointmentRegularBodyPane2015-11-11/0");
        move("#hourLine10");
        press(MouseButton.PRIMARY);
        move("#hourLine15");
        release(MouseButton.PRIMARY);

        // change dialog
        ComboBox<ChangeDialogOption> c = find("#changeDialogComboBox");
        TestUtil.runThenWaitForPaintPulse( () -> c.getSelectionModel().select(ChangeDialogOption.ONE));
        click("#changeDialogOkButton");

        // check return to original state
        assertEquals(2, agenda.vComponents().size());
        assertEquals(3, agenda.appointments().size());
        Collections.sort(agenda.vComponents(), VComponent.VCOMPONENT_COMPARATOR);
        VEventImpl v0 = (VEventImpl) agenda.vComponents().get(0);
        VEventImpl v1 = (VEventImpl) agenda.vComponents().get(1);
        RRule r = ICalendarTestAbstract.getWeeklyZoned().getRRule().withRecurrences(v1);
        VEventImpl expectedV0 = ICalendarTestAbstract.getWeeklyZoned()
                .withRRule(r);
        assertTrue(ICalendarTestAbstract.vEventIsEqualTo(expectedV0, v0));
        
        VEventImpl expectedV1 = ICalendarTestAbstract.getWeeklyZoned()
                .withRRule(null)
                .withDateTimeRecurrence(ZonedDateTime.of(LocalDateTime.of(2015, 11, 11, 10, 00), ZoneId.of("America/Los_Angeles")))
                .withDateTimeStart(ZonedDateTime.of(LocalDateTime.of(2015, 11, 11, 15, 0), ZoneId.of("America/Los_Angeles")))
                .withDateTimeEnd(ZonedDateTime.of(LocalDateTime.of(2015, 11, 11, 15, 45), ZoneId.of("America/Los_Angeles")))
                .withDateTimeStamp(v1.getDateTimeStamp())
                .withSequence(1);
        assertTrue(ICalendarTestAbstract.vEventIsEqualTo(expectedV1, v1));
        
        // check appointment dates
        List<Temporal> expectedDates = new ArrayList<>(Arrays.asList(
                ZonedDateTime.of(LocalDateTime.of(2015, 11, 9, 10, 0), ZoneId.of("America/Los_Angeles"))
              , ZonedDateTime.of(LocalDateTime.of(2015, 11, 11, 15, 0), ZoneId.of("America/Los_Angeles"))
              , ZonedDateTime.of(LocalDateTime.of(2015, 11, 13, 10, 0), ZoneId.of("America/Los_Angeles"))
              ));
        List<Temporal> startDates = agenda.appointments()
                .stream()
                .map(a -> a.getStartTemporal())
                .sorted()
                .collect(Collectors.toList());
        assertEquals(expectedDates, startDates);
        
        List<Temporal> expectedEndDates = new ArrayList<>(Arrays.asList(
                ZonedDateTime.of(LocalDateTime.of(2015, 11, 9, 10, 45), ZoneId.of("America/Los_Angeles"))
              , ZonedDateTime.of(LocalDateTime.of(2015, 11, 11, 15, 45), ZoneId.of("America/Los_Angeles"))
              , ZonedDateTime.of(LocalDateTime.of(2015, 11, 13, 10, 45), ZoneId.of("America/Los_Angeles"))
              ));
        List<Temporal> endDates = agenda.appointments()
                .stream()
                .map(a -> a.getEndTemporal())
                .sorted()
                .collect(Collectors.toList());
        assertEquals(expectedEndDates, endDates);
    }
    
    @Test
    public void canExpandAppoindment()
    {
        TestUtil.runThenWaitForPaintPulse( () -> agenda.vComponents().add(ICalendarTestAbstract.getDaily1()));
        
        // expand time
        move("#AppointmentRegularBodyPane2015-11-11/0 .DurationDragger"); 
        press(MouseButton.PRIMARY);
        move("#hourLine15");
        release(MouseButton.PRIMARY);
        
        // delete VComponent
        ComboBox<ChangeDialogOption> c = find("#changeDialogComboBox");
        TestUtil.runThenWaitForPaintPulse( () -> c.getSelectionModel().select(ChangeDialogOption.ALL));
        click("#changeDialogOkButton");

        // check return to original state
        assertEquals(1, agenda.vComponents().size());
        agenda.appointments().stream().forEach(a -> System.out.println(a.getStartLocalDateTime()));
        assertEquals(6, agenda.appointments().size());
        VEventImpl v = (VEventImpl) agenda.vComponents().get(0);
        VEventImpl expectedV = ICalendarTestAbstract.getDaily1()
                .withDateTimeStart(LocalDateTime.of(2015, 11, 9, 10, 0))
                .withDateTimeEnd(LocalDateTime.of(2015, 11, 9, 15, 0))
                .withSequence(1);
        assertTrue(ICalendarTestAbstract.vEventIsEqualTo(expectedV, v));
        
        // check appointment dates
        List<LocalDateTime> expectedStartDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 9, 10, 0)
              , LocalDateTime.of(2015, 11, 10, 10, 0)
              , LocalDateTime.of(2015, 11, 11, 10, 0)
              , LocalDateTime.of(2015, 11, 12, 10, 0)
              , LocalDateTime.of(2015, 11, 13, 10, 0)
              , LocalDateTime.of(2015, 11, 14, 10, 0)
              ));
        List<LocalDateTime> startDates = agenda.appointments()
                .stream()
                .map(a -> a.getStartLocalDateTime())
                .sorted()
                .collect(Collectors.toList());
        assertEquals(expectedStartDates, startDates);

        List<LocalDateTime> expectedEndDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 9, 15, 0)
              , LocalDateTime.of(2015, 11, 10, 15, 0)
              , LocalDateTime.of(2015, 11, 11, 15, 0)
              , LocalDateTime.of(2015, 11, 12, 15, 0)
              , LocalDateTime.of(2015, 11, 13, 15, 0)
              , LocalDateTime.of(2015, 11, 14, 15, 0)
              ));
        List<LocalDateTime> endDates = agenda.appointments()
                .stream()
                .map(a -> a.getEndLocalDateTime())
                .sorted()
                .collect(Collectors.toList());
        assertEquals(expectedEndDates, endDates);
    }
}
